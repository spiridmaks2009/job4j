package ru.job4j.xml;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;

/**
 * StoreXML
 *
 * @author maksimspiridonov
 */
public class StoreXML {

    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        Entries ent = new Entries();
        ent.entry = list;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class, Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(ent, target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
