package com.YasirErkam.HavaDurumu;

import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class HavaDurumuVerileri {


    public String sSehirAdi;
    public String sSicaklikAnlik;
    public String sSicaklikGunluk1;
    public String sSicaklikGunluk2;
    public String sSicaklikGunluk3;
    public String sSicaklikGunluk4;
    public String sSicaklikGunluk5;

    public void HavaDurumuAl() {

        String urlHavaDurumuAnlik = "http://api.openweathermap.org/data/2.5/weather?q=kocaeli,tr&mode=xml&appid=65f43a51074bbf9e8736eb2d3eea46b7&units=metric&lang=tr";
        String urlHavaDurumuGunluk = "http://api.openweathermap.org/data/2.5/forecast?q=kocaeli,tr&mode=xml&appid=65f43a51074bbf9e8736eb2d3eea46b7&units=metric&lang=tr";

        HttpURLConnection baglantiAnlik = null;
        HttpURLConnection baglantiGunluk = null;

        try {
            URL urlAnlik = new URL(urlHavaDurumuAnlik);
            URL urlGunluk = new URL(urlHavaDurumuAnlik);
            baglantiAnlik = (HttpURLConnection) urlAnlik.openConnection();
            baglantiGunluk = (HttpURLConnection) urlGunluk.openConnection();

            int baglantiDurumuAnlik = baglantiAnlik.getResponseCode();
            int baglantiDurumuGunluk = baglantiGunluk.getResponseCode();
            if (baglantiDurumuAnlik == HttpURLConnection.HTTP_OK && baglantiDurumuGunluk == HttpURLConnection.HTTP_OK) {

                BufferedInputStream streamAnlik = new BufferedInputStream(baglantiAnlik.getInputStream());
                DocumentBuilderFactory documentBuilderFactoryAnlik = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilderAnlik = documentBuilderFactoryAnlik.newDocumentBuilder();

                BufferedInputStream streamGunluk = new BufferedInputStream(baglantiGunluk.getInputStream());
                DocumentBuilderFactory documentBuilderFactoryGunluk = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilderGunluk = documentBuilderFactoryGunluk.newDocumentBuilder();

                Document documentAnlik = documentBuilderAnlik.parse(streamAnlik);
                Document documentGunluk = documentBuilderGunluk.parse(streamGunluk);

                //--
                Element elementCurrent = (Element) documentAnlik.getElementsByTagName("current").item(0);

                sSehirAdi = ((Element) (elementCurrent.getElementsByTagName("city").item(0))).getAttribute("name");
                sSicaklikAnlik = ((Element) (elementCurrent.getElementsByTagName("temperature").item(0))).getAttribute("value");
                sSicaklikAnlik = Integer.toString(Math.round(Float.parseFloat(sSicaklikAnlik)));
                //--

                //-----
         /*
                 NodeList nodeListGunluk = documentGunluk.getElementsByTagName("time");

                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                for (int i = 0; i < nodeListGunluk.getLength(); i++) {
                    System.out.println(LocalDateTime.parse((((Element) nodeListGunluk.item(i)).getAttribute("from")), formatter).toLocalTime().getHour());
                }
               sSicaklikGunluk1 = ((Element) (elementGunluk.getElementsByTagName("temperature").item(0))).getAttribute("value");
                sSicaklikGunluk1 = Integer.toString(Math.round(Float.parseFloat(sSicaklikAnlik)));
                sSicaklikGunluk2 = ((Element) (elementGunluk.getElementsByTagName("temperature").item(0))).getAttribute("value");
                sSicaklikGunluk2 = Integer.toString(Math.round(Float.parseFloat(sSicaklikAnlik)));
                sSicaklikGunluk3 = ((Element) (elementGunluk.getElementsByTagName("temperature").item(0))).getAttribute("value");
                sSicaklikGunluk3 = Integer.toString(Math.round(Float.parseFloat(sSicaklikAnlik)));
                sSicaklikGunluk4 = ((Element) (elementGunluk.getElementsByTagName("temperature").item(0))).getAttribute("value");
                sSicaklikGunluk4 = Integer.toString(Math.round(Float.parseFloat(sSicaklikAnlik)));
                sSicaklikGunluk5 = ((Element) (elementGunluk.getElementsByTagName("temperature").item(0))).getAttribute("value");
                sSicaklikGunluk5 = Integer.toString(Math.round(Float.parseFloat(sSicaklikAnlik)));
*/
            } else
                System.out.println("----------HATA :  baglantiDurumu ----------");


        } catch (Exception e) {
            System.out.println("HATA : Xml parse hatası" + e.getMessage().toString());
        } finally {
            if (baglantiAnlik != null) baglantiAnlik.disconnect();
        }

    }
}
