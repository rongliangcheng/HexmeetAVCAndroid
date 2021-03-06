package com.hexmeet.pageobject;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class PrivacyPolicyPage {
    private String ok_id="com.cninnovatel.ev:id/dialog_ok";
    private AppiumDriver appiumDriver;
    private Logger log = getLogger(this.getClass().getName());

    public PrivacyPolicyPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void ok(){
        log.info("");
        appiumDriver.findElementById(ok_id).click();
    }
}
