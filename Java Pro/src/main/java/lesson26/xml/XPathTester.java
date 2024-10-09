package lesson26.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

/*
https://en.wikipedia.org/wiki/XPath
 */

public class XPathTester {
    public static void main(String[] args) throws Exception {
        // Create a DocumentBuilderFactory instance to configure the parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Create a DocumentBuilder instance to parse the XML file
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Document - дерево из элементов и атрибутов в памяти
        // Parse the XML file and return a Document object
        Document doc = builder.parse(new File("./src/main/java/lesson26/files/test.xml"));
        // Get the root element of the document
        Element root = doc.getDocumentElement();
        /*
            /CATALOG
            //LIGHT - все элементы LIGHT в любом месте документа (// - безразлично какого уровня)
            /CATALOG/PLANT - все элементы PLANT дочерние для CATALOG
            //MOVIE/ACTOR - элементы ACTOR дочерние для MOVIE в любом месте документа
            PLANT[@plantid='456']/PRICE
         */
        // Create an XPath instance to evaluate XPath expressions
        XPath xPath = XPathFactory.newInstance().newXPath();
        // Compile an XPath expression to select the desired nodes
        XPathExpression expression = xPath.compile("//PLANT[@plantid='456']/PRICE");
        // Evaluate the expression and return a NodeList object
        NodeList price = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        // Loop through the NodeList and print the text content of each node
        for (int i = 0; i < price.getLength(); i++) {
            System.out.println(
                    "price: " + price.item(i).getFirstChild().getTextContent()
            );
        }
    }
}
