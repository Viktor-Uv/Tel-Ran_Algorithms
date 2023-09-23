package lesson26.xml.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JaxbDeserializer {
    public static void main(String[] args) throws Exception {
        // Create a JAXBContext instance for the Catalog class
        JAXBContext context = JAXBContext.newInstance(Catalog.class);
        // Create an Unmarshaller instance to convert XML to Java objects
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // Unmarshal the XML file and cast it to a Catalog object
        Catalog catalog = (Catalog) unmarshaller.unmarshal(new File(
                "./src/main/java/lesson26/files/test.xml"
        ));

        System.out.println(catalog);
        catalog.getPlants()
                .forEach(System.out::println);
    }
}
