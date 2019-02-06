package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ParserJsoupTest {

    private static final Logger LOG = LogManager.getLogger(ParserJsoupTest.class.getName());

    @Test
    public void parse() throws IOException, SchedulerException {
        /*Config config = new Config();
        config.init();
        String url = config.get("jsoup.url");
        ParserJsoup parser = new ParserJsoup(url);
        LinkedList<Vacancy> list = parser.parse();

        Database db = new Database();
        db.init();
        db.createNewTable();

        for (Vacancy vac:list) {
            *//*System.out.println(vac.getName());
            System.out.println(vac.getLink());*//*
            db.addVacancyInDb(vac);
        }*/

        QuartzTriggerRunner triggerRunner = new QuartzTriggerRunner();
        triggerRunner.task();
        try {
            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            LOG.error("Exception",e);
        }
    }

}