package com.YasirErkam.HavaDurumu;


import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import java.io.File;

@SpringUI
public class VaadinUI extends UI {

    @Autowired
    public VaadinUI() {
    }

    public DesignHD designHD;

    @Override
    protected void init(VaadinRequest request) {

        DesignHD designHD = new DesignHD();
        setContent(designHD);


        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/weather.jpg"));
        designHD.resimUst.setSource(resource);

        FileResource resourceBG = new FileResource(new File(basepath + "/WEB-INF/images/weatherBG.png"));
        designHD.arkaPlan.setSource(resourceBG);

        FileResource resourceCloud = new FileResource(new File(basepath + "/WEB-INF/images/mostlycloudy.png"));
        designHD.cloud.setSource(resourceCloud);

        FileResource resourceButton = new FileResource(new File(basepath + "/WEB-INF/images/location.png"));
        designHD.locImg.setSource(resourceButton);

        HavaDurumuVerileri hdV = new HavaDurumuVerileri();
        hdV.HavaDurumuAl();
        designHD.simdikiSicaklik.setValue(hdV.sSicaklikAnlik +"°");
        designHD.buttonKonum.setCaption(hdV.sSehirAdi);

    }


}
