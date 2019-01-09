package ru.job4j.xml;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Entries
 *
 * @author maksimspiridonov
 */
@XmlRootElement
public class Entries {
    public List<Entry> entry = new ArrayList<>();
}
