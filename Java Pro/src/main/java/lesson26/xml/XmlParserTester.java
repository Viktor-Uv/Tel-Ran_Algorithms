package lesson26.xml;

// parse - извлечь полезную информацию

// XML - eXtensible Markup Language

// SAX - потоковая библиотека - быстрая
// не строит дерево документа
// низкие требования к памяти

// DOM - Document Object Model
// строится полное дерево документа
// требует больше памяти

// XML Schema
// XML DTD
//      позволяют автоматически проверить соответствие XML документа
//      заранее определенной структуре

// Xpath - позволяет выполнить запрос к документу
// XSLT - используется для трансформации XML документов к другому виду и формату
// JAXB и SimpleXML - самые распространенные библиотеки для ORM -
//      - чтобы можно было сериализовать и десериализовать объект в XML
// ORM - Object Relation Mapping

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlParserTester {
    public static void main(String[] args) throws Exception {
        // DOM parsing
        // Create a DocumentBuilderFactory object to configure the parser settings
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Create a DocumentBuilder object to parse XML documents
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Document - дерево из элементов и атрибутов в памяти

        // Parse the existing test.xml file and create a Document object that represents the XML tree in memory
        Document doc = builder.parse(new File("./src/main/java/lesson26/files/test.xml"));
        // корневой элемент документа
        // Get the root element of the document, which is <CATALOG>
        Element root = doc.getDocumentElement();
        // Get a list of all elements with the tag name <PRICE> in the document
        NodeList priceNodeList = root.getElementsByTagName("PRICE");
        // Loop through the list and print the text content of each <PRICE> element
        for (int i = 0; i < priceNodeList.getLength(); i++) {
            Node priceNode = priceNodeList.item(i);
            System.out.println(
                    priceNode.getFirstChild().getTextContent()
            );
        }

        // сложите текстовые значения всех элементов ZONE
        // Integer.parseInt("123")
        NodeList zoneNodeList = root.getElementsByTagName("ZONE");
        int zoneSum = 0;
        for (int i = 0; i < zoneNodeList.getLength(); i++) {
            zoneSum += Integer.parseInt(
                    zoneNodeList.item(i).getFirstChild().getTextContent()
            );
        }
        System.out.println(zoneSum);

        System.out.println(
                root.getAttribute("name")
        );

        System.out.println(
                root.getAttribute("size")
        );

        Element plant2 = (Element) root.getChildNodes().item(3);
        System.out.println(plant2.getAttribute("plantid"));

        System.out.println(
                root.getChildNodes().item(1).getNodeType()
        );

        // Get a list of all child nodes of the root element,
        // which include elements, text nodes, comments, etc.
        NodeList childNodes = root.getChildNodes();
        // Loop through the list and print the node type of each child node
        for (int i = 0; i < childNodes.getLength(); i++) {
            System.out.println(
                    childNodes.item(i).getNodeType()
            );
        }

    }
}
