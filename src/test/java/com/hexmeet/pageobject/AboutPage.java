package com.hexmeet.pageobject;

import io.appium.java_client.AppiumDriver;

public class AboutPage {

    private String version_id="com.cninnovatel.ev:id/version";
    private AppiumDriver appiumDriver;

    public AboutPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public String getVersion(){
        return appiumDriver.findElementById(version_id).getText();
    }

}
