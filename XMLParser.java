import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLParser {

    public static ArrayList<Malware> MalArrList = new ArrayList<>();
    public static Map<String, Malware> malwareDB = new HashMap<>();
    public static Map<String, Malware> parse(String filename) {

        try { File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("row");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    int level = Integer.parseInt(eElement.getElementsByTagName("level").item(0).getTextContent());
                    String hash = eElement.getElementsByTagName("hash").item(0).getTextContent();
                    Malware ob = new Malware(title, level, hash);
                    MalArrList.add(ob);
                    malwareDB.put(hash,ob);}}
            return malwareDB;}
        catch (Exception e) { e.printStackTrace();}
        return null;}
    }
