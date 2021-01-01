package app.mma.jsonxml;


import org.jdom2.Element;

import java.io.InputStream;

public class TourJdomParser extends XmlJdomParser<Tour>{

    private InputStream input;
    public TourJdomParser(InputStream input){
        this.input = input;
    }

    @Override
    public InputStream getInput() {
        return input;
    }

    @Override
    public String getObjectNodesKey() {
        return "tour";
    }

    @Override
    public Tour getObjectFromNode(Element node) {
        Tour tour = new Tour();
        tour.setTourId(Long.valueOf(node.getChildText("tourId")));
        tour.setTourTitle(node.getChildText("tourTitle"));
        tour.setDescription(node.getChildText("description"));
        tour.setImage(node.getChildText("image"));
        tour.setLink(node.getChildText("link"));
        tour.setDifficulty(node.getChildText("difficulty"));
        tour.setLength(Integer.valueOf(node.getChildText("length")));
        tour.setPackageTitle(node.getChildText("packageTitle"));
        tour.setPrice(Integer.valueOf(node.getChildText("price")));
        return tour;
    }
}
