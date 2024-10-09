package lesson26.xml.jaxb;

/*
JAXB stands for Java Architecture for XML Binding. It is a standard that defines an API for reading and
writing Java objects to and from XML documents. It supports a binding framework that maps XML elements and
attributes to Java fields and properties using Java annotations. JAXB also provides tools to generate
Java classes from XML schemas and vice versa. JAXB was part of the Java SE platform until Java SE 10,
but it was removed in Java SE 11 and moved to a separate module.

A marshaller and an unmarshaller are concepts related to the process of converting Java objects to and
from XML documents. A marshaller serializes an object to XML, and an unmarshaller deserializes XML stream
to an object. You can use a marshaller to generate an XML representation of an object, and use an unmarshaller
to create an object from an XML file. JAXB is a standard that defines an API for marshalling and
unmarshalling Java objects using annotations. Marshal and unmarshal are also English words that mean
“to arrange or organize” and “to undo or disperse”, respectively.
 */

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.Arrays;

public class JaxbSerializer {
    public static void main(String[] args) throws Exception {
        // Create a JAXBContext instance for the Catalog class
        JAXBContext context = JAXBContext.newInstance(Catalog.class);

        // Create a Plant object and set its fields
        Plant p = new Plant();
        p.setCommon("COMMON");
        p.setZone("ZONE");
        p.setAvailability("AVAILABLE");
        p.setPrice("$123");
        p.setLight("LIGHT");
        p.setBotanical("BOTANICAL");

        // Create a Catalog object and set its fields
        Catalog c = new Catalog();
        c.setSize("BIG");
        c.setName("NAME");
        // Add the Plant object to the list of plants in the Catalog object
        c.setPlants(Arrays.asList(p));

        // Create a Marshaller instance to convert Java objects to XML
        Marshaller marshaller = context.createMarshaller();
        // Set the property to format the output XML
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Marshal the Catalog object and write it to a file
        marshaller.marshal(c, new File("./src/main/java/lesson26/files/catalog.xml"));
    }
}
