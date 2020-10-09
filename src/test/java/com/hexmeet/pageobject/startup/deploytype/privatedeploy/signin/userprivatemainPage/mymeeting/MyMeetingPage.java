package com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.userprivatemainPage.mymeeting;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.userprivatemainPage.UserPrivateMainPage;
import com.hexmeet.pageobject.startup.deploytype.publicdeploy.signin.userpublicmainpage.publicmeeting.PublicMeeting;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MyMeetingPage {
    private String myMeetingXpathBase="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/";

    Logger logger = getLogger("MyMeetingPage");

    AppiumDriver appiumDriver;

    public MyMeetingPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void navigate(String serveraddr,String accout,String password){
        logger.info("");
        UserPrivateMainPage userPrivateMainPage = new UserPrivateMainPage(appiumDriver);
        userPrivateMainPage.navigate(serveraddr,accout,password);
        userPrivateMainPage.meeting();
    }

    public void mymeetingpage(){
        logger.info("");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[2]/android.view.View/android.view.View[1]").click();
    }

    public void joinTheMeeting(String password){
        logger.info("");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[2]/android.view.View[2]").click();
        Pause.stop(4);
        if(UIElement.byElementIsExist(appiumDriver, By.id("com.hexmeet.hjt:id/input_password"))) {
            appiumDriver.findElementById("com.hexmeet.hjt:id/input_password").sendKeys("1111");
            appiumDriver.findElementById("com.hexmeet.hjt:id/positiveButton").click();
            Pause.stop(1.5);
            appiumDriver.findElementById("com.hexmeet.hjt:id/input_password").sendKeys(password);
            appiumDriver.findElementById("com.hexmeet.hjt:id/positiveButton").click();
        }
    }

    public void meetingSettings(String password, boolean muteWhenJoin,boolean allowAnonymousUser,boolean onlyHosterCanActivateMeeting,boolean allowOthersReserveMeeting,boolean groupSelectionBoolean){
        logger.info("");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[2]/android.view.View[3]").click();

        //password
        Pause.stop(3);
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[3]/android.view.View/android.widget.EditText").sendKeys(password);

        if(muteWhenJoin)
            appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[4]/android.view.View[1]").click();

        if(allowAnonymousUser)
            appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[4]/android.view.View[2]").click();

        if(onlyHosterCanActivateMeeting)
            appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[4]/android.view.View[3]").click();

        if(allowAnonymousUser)
            appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[4]/android.view.View[4]/android.view.View[1]").click();

        if(groupSelectionBoolean)
            groupSelection();

        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[6]/android.view.View[1]").click();
    }

    private void groupSelection(){
        logger.info("");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[4]/android.view.View[4]/android.view.View[2]/android.widget.Button").click();
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[6]/android.view.View/android.view.View[2]/android.widget.Button[1]").click();

        Pause.stop(0.5);
        //remove the group selection
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[4]/android.view.View[4]/android.view.View[3]").click();
    }

    public void muteUmuteCamera(){
        logger.info("");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[2]/android.widget.CheckBox[1]").click();
    }

    public void muteUmuteAudio(){
        logger.info("");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(myMeetingXpathBase+"android.view.View[2]/android.widget.CheckBox[2]").click();
    }
}
