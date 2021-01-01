package app.mma.jsonxml;


import android.content.Context;

import org.jdom2.Element;

import java.io.InputStream;

public class FlowerJdomParserV2 extends XmlJdomParser<Flower> {
    InputStream input;
    public FlowerJdomParserV2(InputStream input){
        this.input = input;
    }


    @Override
    public InputStream getInput() {
        return input;
    }

    @Override
    public String getObjectNodesKey() {
        return "product";
    }

    @Override
    public Flower getObjectFromNode(Element node) {
        Flower flower = new Flower();
        flower.setName(node.getChildText("name"));
        flower.setProductId(Long.valueOf(node.getChildText("productId")));
        flower.setCategory(node.getChildText("category"));
        flower.setInstructions(node.getChildText("instructions"));
        flower.setPhoto(node.getChildText("photo"));
        flower.setPrice(Double.valueOf(node.getChildText("price")));
        return flower;
    }
}
