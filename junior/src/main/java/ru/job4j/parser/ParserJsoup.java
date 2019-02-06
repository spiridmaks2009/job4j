package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.time.*;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Класс для получения данных из HTML
 *
 * @author maksimspiridonov
 */
public class ParserJsoup{

    private String url;
    private LinkedList<Vacancy> list = new LinkedList<>();
    private final int PAGES = 10;

    public ParserJsoup(String url) {
        this.url = url;
    }

    public LinkedList<Vacancy> parse() throws IOException, ParseException {
        for (int i = 0; i < PAGES; i++) {
            Document doc = Jsoup.connect(url + "\\" + i).get();
            Elements elements = doc.getElementsByAttributeValue("class", "forumTable").get(0).getElementsByTag("tr");
            for (Element elem : elements) {
                String title = elem.text();
                if (title.contains("java") || title.contains("Java") || title.contains("JAVA")
                        && !title.contains("javas") && !title.contains("javaS") && !title.contains("Javas") && !title.contains("JavaS")) {
                    String vacurl = elem.getElementsByAttributeValue("class", "postslisttopic").get(0).child(0).attr("href");
                    Document vacancyDoc = Jsoup.connect(vacurl).get();
                    Elements message = vacancyDoc.getElementsByAttributeValue("class", "msgTable");
                    Elements firstmsg = message.first().getElementsByTag("tr");

                    String sDate = firstmsg.last().text().split(",")[0];
                    DateFormat format = new SimpleDateFormat("d MMM yy", Locale.getDefault());
                    Date date;
                    if(sDate.equals("сегодня")) {
                        date = format.parse((new Date()).toString());
                    } else if (sDate.equals("вчера")){
                        date = format.parse((LocalDate.now().minusDays(1)).toString());
                    } else {
                        date = format.parse(sDate);
                    }
                    String text = firstmsg.get(1).getElementsByAttributeValue("class", "msgBody").last().text();
                    list.add(new Vacancy(title, text, vacurl, date));
                }
            }
        }
            return list;
    }
}
