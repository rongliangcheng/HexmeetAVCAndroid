package com.hexmeet.pageobject.common;

import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;

public class UICommon {


    public static void devicePermissionAllowance(final AppiumDriver appiumDriver) {

        //logger.info("Allow permissions on device");
        Pause.stop(2);

        appiumDriver.findElementById("com.lbe.security.miui:id/permission_allow_foreground_only_button").click();
        Pause.stop(1);
        appiumDriver.findElementById("com.lbe.security.miui:id/permission_allow_foreground_only_button").click();
        Pause.stop(1);
        appiumDriver.findElementById("com.lbe.security.miui:id/permission_allow_button_1").click();
    }

    public static void devicePermissionAllowanceOnce(final AppiumDriver appiumDriver) {

        //LOGGER.info("Allow permissions on device");
        Pause.stop(2);
        appiumDriver.findElementById("com.lbe.security.miui:id/permission_allow_foreground_only_button").click();
    }

    //Used to check the wrong input password operation
    public static boolean detectToast(final AppiumDriver appiumDriver){
        //LOGGER.info("Detect toast on screen");
        Pause.stop(1);
        if(appiumDriver.findElementByXPath("/hierarchy/android.widget.Toast") != null)
            return true;

        return false;
    }


}
