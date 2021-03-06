package com.hexmeet.pageobject;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class AboutPage {

    private String version_id="com.cninnovatel.ev:id/version";
    private AppiumDriver appiumDriver;
    private Logger log = getLogger(this.getClass().getName());

    public AboutPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public String getVersion(){
        log.info("");
        return appiumDriver.findElementById(version_id).getText();
    }

}
