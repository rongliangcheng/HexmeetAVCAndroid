package com.hexmeet.Utility;

import com.hexmeet.base.CommonValue;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UIElement {

    public static boolean byElementIsExist(AppiumDriver appiumDriver, By by) {
        appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            //LOGGER.info("Check element");
            WebDriverWait explicitWait = new WebDriverWait(appiumDriver, 1);
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            //LOGGER.info("Element is not shown");
            appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
            return false;
        }

        appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
        //LOGGER.info("Check complete");
        return true;

    }



}
