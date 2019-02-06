package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.text.ParseException;
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
        String url = config.get("jsoup.url");
        ParserJsoup parser = new ParserJsoup(url);
        LinkedList<Vacancy> list = null;
        try {
            list = parser.parse();
        } catch (IOException e) {
            LOG.error(e);
        } catch (ParseException e) {
            LOG.error(e);
        }

        Database db = new Database();
        db.init();
        db.createNewTable();
        Date lastDate = db.getLastDate();

        for (Vacancy vac:list) {
            if (vac.getDate().after(lastDate)) {
                db.addVacancyInDb(vac);
            }
        }
        db.setLastDate(new Date());
    }
}
