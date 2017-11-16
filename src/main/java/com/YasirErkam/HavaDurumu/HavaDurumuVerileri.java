package com.YasirErkam.HavaDurumu;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class HavaDurumuVerileri {


    public String sSicaklik;
    public String sSehirAdi;

    public void ListeyiDoldur() {

        String urlHD = "http://api.openweathermap.org/data/2.5/weather?q=kocaeli,tr&mode=xml&appid=65f43a51074bbf9e8736eb2d3eea46b7&units=metric&lang=tr";
        List<String> hdList = new ArrayList<>();

        HttpURLConnection baglanti = null;

        try {
            URL url = new URL(urlHD);
            baglanti = (HttpURLConnection) url.openConnection();

            int baglantiDurumu = baglanti.getResponseCode();
            if (baglantiDurumu == HttpURLConnection.HTTP_OK) {

                BufferedInputStream stream = new BufferedInputStream(baglanti.getInputStream());
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                Document document = documentBuilder.parse(stream);

                Element elementCurrent = (Element) document.getElementsByTagName("current").item(0);

                sSicaklik = ((Element) (elementCurrent.getElementsByTagName("temperature").item(0))).getAttribute("value");
                sSicaklik = Integer.toString(Math.round(Float.parseFloat(sSicaklik)));
                sSehirAdi = ((Element) (elementCurrent.getElementsByTagName("city").item(0))).getAttribute("name");

            } else
                System.out.println("----------HATA :  baglantiDurumu ----------");


        } catch (Exception e) {
            System.out.println("HATA : Xml parse hatası" + e.getMessage().toString());
        } finally {
            if (baglanti != null) baglanti.disconnect();
        }

    }
}
