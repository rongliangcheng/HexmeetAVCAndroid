package com.hexmeet.pageobject.startup;

import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;

public class UIStart {
    final private AppiumDriver appiumDriver;

    public UIStart(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public  void startUp() {

        appiumDriver.findElementById("com.hexmeet.hjt:id/dialog_ok").click();

        Pause.stop(1);

        appiumDriver.findElementById("com.lbe.security.miui:id/permission_allow_foreground_only_button").click();

    }

}
