package com.hexmeet.Utility;

import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;

public class UICommon {
    private static final String permission_allow_foreground_only_id="com.lbe.security.miui:id/permission_allow_foreground_only_button";
    private static final String permission_allow_button="com.lbe.security.miui:id/permission_allow_button_1";

    public static void devicePermissionAllowance(final AppiumDriver appiumDriver) {

        //logger.info("Allow permissions on device");
        Pause.stop(0.5);
        appiumDriver.findElementById(permission_allow_foreground_only_id).click();
        Pause.stop(0.5);
        appiumDriver.findElementById(permission_allow_button).click();
    }

    public static void devicePermissionAllowanceOnce(final AppiumDriver appiumDriver) {

        //LOGGER.info("Allow permissions on device");
        Pause.stop(0.5);
        appiumDriver.findElementById("com.lbe.security.miui:id/permission_allow_foreground_only_button").click();
    }

    public static void devicePermissionAllowanceAfterLogin(final AppiumDriver appiumDriver){
        Pause.stop(0.5);
        appiumDriver.findElementById(permission_allow_foreground_only_id).click();
        Pause.stop(0.5);
        appiumDriver.findElementById(permission_allow_foreground_only_id).click();
        Pause.stop(0.5);
        appiumDriver.findElementById(permission_allow_foreground_only_id).click();
        Pause.stop(0.5);
        appiumDriver.findElementById(permission_allow_foreground_only_id).click();
        Pause.stop(0.5);
        appiumDriver.findElementById(permission_allow_button).click();
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
