package ru.job4j.xml;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXTest {

    @Test
    public void whenParseXMLFile() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXPars saxp = new SAXPars();

        parser.parse(new File("result.xml"), saxp);

        System.out.println(saxp.getSum());
    }
}
