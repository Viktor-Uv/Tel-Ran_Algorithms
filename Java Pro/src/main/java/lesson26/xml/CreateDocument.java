package lesson26.xml;

/*
This is a Java program that creates an XML document with the following structure:

<test hello="123">
    <world id="456"/>
</test>

The program uses the DOM API to create and manipulate the XML elements and attributes.
The program also saves the XML document to a file using a Transformer object.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class CreateDocument {
    public static void main(String[] args) throws Exception {
        // Create a DocumentBuilder object to parse XML documents
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Create a new Document object to store the XML tree
        Document doc = builder.newDocument();

        // Create the root element <test> with an attribute hello=“123”
        Element root = doc.createElement("test");
        root.setAttribute("hello", "123");
        // Append the root element to the document
        doc.appendChild(root);

        // Create a child element <world> with an attribute id="456"
        Element world = doc.createElement("world");
        world.setAttribute("id", "456");
        // Append the child element to the root element
        root.appendChild(world);

        // Saving document:
        // Create a FileOutputStream object to write to result.xml
        FileOutputStream fos = new FileOutputStream("./src/main/java/lesson26/files/result.xml");
        // Create a DOMSource object to hold the XML tree
        DOMSource source = new DOMSource(doc);
        // Create a StreamResult object to write the output stream
        StreamResult result = new StreamResult(fos);
        // Create a TransformerFactory object to create Transformer objects
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // Create a Transformer object to transform the XML tree into a stream
        Transformer transformer = transformerFactory.newTransformer();
        // Transform the source into the result
        transformer.transform(source, result);
    }
}
