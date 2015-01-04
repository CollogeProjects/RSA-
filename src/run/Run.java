package run;

import domains.Item;
import domains.Pos;
import domains.ShoppingChart;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;
/**
 * Created by Hugh on 2014/12/29 0029.
 */
public class Run {
    public static void main(String[] args) throws Exception{
        Run run = new Run();
        String actualShoppingList;
        actualShoppingList= run.SaxIndex();
        System.out.println(actualShoppingList);
    }
    public String SaxIndex() throws DocumentException{
        Item item=null;
        Document doc1;
        String absent="需要寻找的商品不存在！";
        Pos pos = new Pos();
        SAXReader sax1 = new SAXReader();
        ShoppingChart shoppingChart = new ShoppingChart();
        doc1 = sax1.read("Index.xml");
        Element root = doc1.getRootElement();
        Iterator<Element> it = root.elementIterator();
        while (it.hasNext()) {
            Element el=it.next();
            String barcode=el.getText();
            item=SaxListing(barcode);
            if(item==null){
                return absent;
            }else {
                shoppingChart.add(item);
            }

        }
        String actualShoppingList = pos.getShoppingList(shoppingChart);
        return actualShoppingList;
    }

    public Item SaxListing(String barcode) throws DocumentException{
        Item item=null;
        SAXReader sax2 = new SAXReader();
        Document doc2;
        doc2 = sax2.read("Listing.xml");
        Element root = doc2.getRootElement();
        Iterator<Element> it = root.elementIterator();
        while (it.hasNext()) {
            Element el = it.next();
            if(el.attributeValue("type").equals(barcode)&&(el.elements().size()==3)){
                String name=el.elementText("name");
                String unit=el.elementText("unit");
               double price=Double.parseDouble(el.elementText("price"));
              item=new Item(barcode,name,unit,price);
            }
            if(el.attributeValue("type").equals(barcode)&&(el.elements().size()==4)){
                String name=el.elementText("name");
                String unit=el.elementText("unit");
                double price=Double.parseDouble(el.elementText("price"));
                double discount=Double.parseDouble(el.elementText("discount"));
                 item=new Item(barcode,name,unit,price,discount);
            }
            if(el.attributeValue("type").equals(barcode)&&(el.elements().size()==5)){
                String name=el.elementText("name");
                String unit=el.elementText("unit");
                double price=Double.parseDouble(el.elementText("price"));
                double discount=Double.parseDouble(el.elementText("discount"));
                boolean prom = Boolean.parseBoolean(el.elementText("promotion"));
                item=new Item(barcode,name,unit,price,discount,prom);
            }

        }
        return item;
    }
}
