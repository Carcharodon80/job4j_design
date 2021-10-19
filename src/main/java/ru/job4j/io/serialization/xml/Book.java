package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * 4. JAXB. Преобразование XML в POJO. [#315063]
 */
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @XmlAttribute
    private boolean isChildBook;
    @XmlAttribute
    private int pages;
    @XmlAttribute
    private String title;
    private Databook dataBook;
    @XmlElementWrapper(name = "authors")
    @XmlElement(name = "author")
    private String[] authors;

    public Book() {
    }

    public Book(boolean isChildBook, int pages, String title, Databook dataBook, String... authors) {
        this.isChildBook = isChildBook;
        this.pages = pages;
        this.title = title;
        this.dataBook = dataBook;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{"
                + "isChildBook=" + isChildBook
                + ", pages=" + pages
                + ", title='" + title + '\''
                + ", dataBook=" + dataBook
                + ", authors=" + Arrays.toString(authors)
                + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {
        Book book = new Book(false, 680, "CrazyGoose",
                new Databook("England", 2019, "ArtHouse"),
                "John Doe", "Jane Doe");
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(book, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Book result = (Book) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
