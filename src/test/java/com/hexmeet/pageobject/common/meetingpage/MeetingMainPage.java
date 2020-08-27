package com.hexmeet.pageobject.common.meetingpage;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MeetingMainPage {

    Logger logger = getLogger("MeetingMainPage");
    AppiumDriver appiumDriver;

    public MeetingMainPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void MyMeetingRoom(){
        logger.info("");
        Pause.stop(0.5);

        appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.ListView/android.view.View").click();
    }

    public void reserveMeetingPage(){
        logger.info("");
        Pause.stop(0.5);
        String xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]";
        if(UIElement.byElementIsExist(appiumDriver, By.xpath(xpath))) {
            appiumDriver.findElementByXPath(xpath).click();
        }else {
            appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[2]").click();
        }
    }

    public boolean isOnMyMeetingRoom(){
        logger.info("");
        return UIElement.byElementIsExist(appiumDriver,By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.ListView/android.view.View"));
    }

    public boolean isOnMeetingPage(){
        logger.info("");
        return UIElement.byElementIsExist(appiumDriver,By.id("com.hexmeet.hjt:id/conference"));
    }
}
