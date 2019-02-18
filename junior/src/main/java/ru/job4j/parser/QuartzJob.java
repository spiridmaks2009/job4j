package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * Класс, описывающий задание планировщика
 *
 * @author maksimspiridonov
 */
public class QuartzJob implements Job {

    private static final Logger LOG = LogManager.getLogger(QuartzJob.class.getName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Config config = new Config();
        config.init();
        Database db = new Database();
        db.init();
        db.createNewTable();

        Date lastDate = db.getLastDate();
        if(lastDate == null){
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_YEAR, 1);
            lastDate = cal.getTime();
        }
        String url = config.get("jsoup.url");
        ParserJsoup parser = new ParserJsoup(url);
        LinkedList<Vacancy> list = null;
        try {
            list = parser.parse(lastDate);
        } catch (IOException e) {
            LOG.error(e);
        } catch (ParseException e) {
            LOG.error(e);
        }

        for (Vacancy vac:list) {
            if (vac.getDate().after(lastDate)) {
                db.addVacancyInDb(vac);
            }
        }
        db.setLastDate(new Date());
    }
}
