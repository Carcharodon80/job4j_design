package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.*;

/**
 * 4. JAXB. Преобразование XML в POJO. [#315063]
 */
@XmlRootElement(name = "dataBook")
public class Databook {
    @XmlAttribute
    private String country;
    @XmlAttribute
    private int year;
    @XmlAttribute
    private String publisher;

    public Databook() {
    }

    public Databook(String country, int year, String publisher) {
        this.country = country;
        this.year = year;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Databook{"
                + "country='" + country + '\''
                + ", year=" + year
                + ", publisher='" + publisher + '\''
                + '}';
    }
}
