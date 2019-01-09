package ru.job4j.xml;

import org.junit.Test;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;

public class StoreXSLTest {
    @Test
    public void Stylizer() throws TransformerException {

    File xsl = new File("storexsl.xsl");
    File xml = new File("storexml.xml");
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xsl));
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new StreamSource(xml), new StreamResult(new File("result.xml")));

    }
}
