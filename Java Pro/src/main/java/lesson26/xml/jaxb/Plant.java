package lesson26.xml.jaxb;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

public class Plant {
    private String plantid;
    private String common;
    private String botanical;
    private String zone;
    private String light;
    private String price;
    private String availability;

    public String getPlantid() {
        return plantid;
    }

    @XmlAttribute(name = "plantid")
    public void setPlantid(String plantid) {
        this.plantid = plantid;
    }

    public String getCommon() {
        return common;
    }

    @XmlElement(name = "COMMON")
    public void setCommon(String common) {
        this.common = common;
    }

    public String getBotanical() {
        return botanical;
    }

    @XmlElement(name = "BOTANICAL")
    public void setBotanical(String botanical) {
        this.botanical = botanical;
    }

    public String getZone() {
        return zone;
    }

    @XmlElement(name = "ZONE")
    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLight() {
        return light;
    }

    @XmlElement(name = "LIGHT")
    public void setLight(String light) {
        this.light = light;
    }

    public String getPrice() {
        return price;
    }

    @XmlElement(name = "PRICE")
    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    @XmlElement(name = "AVAILABILITY")
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "P{" +
                "i='" + plantid + '\'' +
                ", c='" + common + '\'' +
                ", b='" + botanical + '\'' +
                ", z='" + zone + '\'' +
                ", l='" + light + '\'' +
                ", p='" + price + '\'' +
                ", a='" + availability + '\'' +
                '}';
    }
}
