package ru.job4j.xml;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreXMLTest {
    @Test
    public void name() throws Exception {
        StoreSQL store = new StoreSQL();
        store.createNewDatabase("store.db");

        try {
            store.createNewTable();
            store.generate(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Entry> entry = store.selectData();
        StoreXML storeXML = new StoreXML(new File("storexml.xml"));
        storeXML.save(entry);
        store.close();

        Entries result = new Entries();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            result = (Entries) jaxbUnmarshaller.unmarshal(new File("storexml.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        List<Entry> expected = Stream.iterate(0, n -> n + 1).limit(10).map(Entry::new).collect(Collectors.toList());
        assertThat(result.entry, is(expected));
    }
}