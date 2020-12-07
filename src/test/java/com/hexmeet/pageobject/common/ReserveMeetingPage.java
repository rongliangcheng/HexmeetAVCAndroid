package com.hexmeet.pageobject.common;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import com.hexmeet.pageobject.common.meetingpage.MeetingMainPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.slf4j.Logger;

import java.time.Duration;

import static org.slf4j.LoggerFactory.getLogger;


public class ReserveMeetingPage {

    Logger logger = getLogger("ReserveMeetingPage");

    private AppiumDriver appiumDriver;


    private String userNameInMeeting = "com.hexmeet.hjt:id/name_dialog";
    private String videoJoin = "com.hexmeet.hjt:id/video_btn";
    private String audioJoin = "com.hexmeet.hjt:id/audio_btn";
    private String camera_switch = "com.hexmeet.hjt:id/close_camera_switch";
    private String mic_switch = "com.hexmeet.hjt:id/close_mic_switch";


    private String reserverPageXpathBase="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/";

    ReserveMeetingPage(AppiumDriver appiumDriver) throws ClassNotFoundException {
        this.appiumDriver = appiumDriver;
    }

    public void navigate(){
        logger.info("ReserveMeetingPage navigate");
        Pause.stop(5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/conference").click();
        MeetingMainPage meetingMainPage = new MeetingMainPage(appiumDriver);
        meetingMainPage.reserveMeetingPage();
    }

    public void  finish(){
        logger.info("Reserve meeting finished");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View").click();
    }

    public void now(){
        logger.info("Choose now checkbox");
        Pause.stop(0.5);
        //Used for OPPO
        //appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View[1]").click();
        //Used for VIVO
        //appiumDriver.findElementByXPath("//android.webkit.WebView[@content-desc=\"会捷通\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View[1]").click();
        //Used for xiaomi
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View[1]").click();
    }

    public void changeDuration(){
        logger.info("Change duration");

        //调出时长页面
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]").click();

        //设定时间
        Pause.stop(1);
        TouchAction touchAction = new TouchAction(appiumDriver);
        Point pointStart = new Point(260,1950);
        Point pointEnd = new Point(260,1350);
        touchAction.press(PointOption.point(pointStart)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(pointEnd)).release().perform();
        Pause.stop(1);
        touchAction.press(PointOption.point(pointStart)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(pointEnd)).release().perform();

        //确定
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[3]/android.view.View[1]/android.view.View[2]").click();
    }


    public boolean containStringInDuration(String username,String string){
        logger.info("Duration contains special numbers");
        findReservedMeeting(username);
        Pause.stop(1);
        boolean findString = appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View[2]").getText().contains(string);
        Pause.stop(0.5);
        logger.info(String.valueOf(findString));
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View").click();
        Pause.stop(0.5);
        return findString;

    }

    public void setMeetingPassword(String password){
        logger.info("Set meeting password");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[3]").click();
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.widget.EditText").sendKeys(password);
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View").click();
    }

    public void muteWhenJointheMeeting(){
        logger.info("mute when join the meeting");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[1]/android.widget.CheckBox").click();
    }

    public void AnnoymousUserNotPermitted(){
        logger.info("Annoymous check box");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.widget.CheckBox").click();
    }

    public void addParticipants(String name){
        logger.info("Choose participants");
        //click +
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[5]/android.view.View[2]/android.view.View").click();
        Pause.stop(3);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[3]/android.view.View[1]/android.view.View").click();
        Pause.stop(1);
        //input accout and enter
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText").sendKeys(name);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText").click();
        appiumDriver.getKeyboard().sendKeys(Keys.RETURN);
        //bug: need to click several times
        Pause.stop(1);
        //appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View").click();
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[4]/android.view.View/android.view.View").click();
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[4]/android.view.View/android.view.View").click();
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[4]/android.view.View/android.view.View").click();
        //OK
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[5]/android.view.View[2]").click();
    }

    public void backAfterReserver(){
        logger.info("BackAfterReserve");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View").click();
    }



    private void goToMeetingControl(){
        logger.info("Go to meeting control page");
        Pause.stop(0.5);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[5]/android.widget.ListView/android.view.View[2]/android.view.View[1]").click();
    }

    public void meetingControlInvite(){

    }


    public void unlockMeeting(String username){
        logger.info("unlock the meeting");
        findReservedMeeting(username);
        Pause.stop(3);
        goToMeetingControl();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[4]/android.view.View[4]/android.view.View[2]").click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[7]/android.widget.Button[1]").click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View").click();
    }

    public void postPoneMeeting(String username){
        logger.info("unlock the meeting");
        findReservedMeeting(username);
        Pause.stop(3);
        goToMeetingControl();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[4]/android.view.View[4]/android.view.View[2]").click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[7]/android.widget.Button[2]").click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View").click();
    }


    public void vote(String username){
        logger.info("unlock the meeting");
        findReservedMeeting(username);
        Pause.stop(3);
        goToMeetingControl();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[4]/android.view.View[4]/android.view.View[2]").click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[7]/android.widget.Button[3]").click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View").click();
    }

    private String findReservedMeetingXpath(String meetingOwner){
        logger.info("Find reserved meeting xpath");
        Pause.stop(0.5);
//        TouchAction touchAction = new TouchAction(appiumDriver);
//        Point pointStart = new Point(300,1900);
//        Point pointEnd = new Point(300,400);
//        touchAction.press(PointOption.point(pointStart)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(pointEnd)).perform();
        Pause.stop(1);
        //appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.widget.ListView/android.view.View").click();
        //appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[-1]/android.view.View[4]/android.widget.ListView/android.view.View").click();

        String preStr=reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[3]/android.view.View[";
        String appendixStr="]/android.view.View/android.widget.ListView/android.view.View/android.view.View[3]";
        String onlyOneItemXpath=reserverPageXpathBase+"android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.widget.ListView/android.view.View/android.view.View[3]";
        String xpath="";

        if(UIElement.byElementIsExist(appiumDriver,By.xpath(onlyOneItemXpath)) && appiumDriver.findElementByXPath(onlyOneItemXpath).getText().contains(meetingOwner)){
            xpath=onlyOneItemXpath;
        }else{
            int loop = 1;
            while(loop < 15 ){
                xpath=preStr+loop+appendixStr;
                if(UIElement.byElementIsExist(appiumDriver,By.xpath(xpath)) && appiumDriver.findElementByXPath(xpath).getText().contains(meetingOwner))
                    break;

                loop++;
            }
        }

        return xpath;
    }

    public void findReservedMeeting(String meetingOwner){
        logger.info("Find the reserved meeting");

        String xpath = findReservedMeetingXpath(meetingOwner);
        appiumDriver.findElementByXPath(xpath).click();
    }

    public void joinReservedMeeting(String meetingOwner, boolean videoCall, boolean cameraMute, boolean micMute){
        logger.info("Join the reserved meeting");
        findReservedMeeting(meetingOwner);

        Pause.stop(3);

        String joinMeetingButton1=reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[5]/android.view.View[1]";
        String joinMeetingButton2=reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[6]/android.view.View[1]";
        if(UIElement.byElementIsExist(appiumDriver,By.xpath(joinMeetingButton2))) {
            appiumDriver.findElementByXPath(joinMeetingButton2).click();
        }else if (UIElement.byElementIsExist(appiumDriver,By.xpath(joinMeetingButton1))){
            appiumDriver.findElementByXPath(joinMeetingButton1).click();
        }

        if(cameraMute){
            appiumDriver.findElementById(camera_switch).click();
        }

        if(!micMute){
            appiumDriver.findElementById(mic_switch).click();
        }

        if(videoCall){
            appiumDriver.findElementById(videoJoin).click();
        } else {
            appiumDriver.findElementById(audioJoin).click();
        }


    }

    public void deleteReservedMeeting(String meetingOwner){
        logger.info("Terminate reserve meeting");
        findReservedMeeting(meetingOwner);
// Try to use coordinate to locate the element
//        Point pointItem = new Point(300,1900);
//        touchAction.press(PointOption.point(pointItem)).release().perform();
        Pause.stop(3);

        String terminateButton1=reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[5]/android.widget.ListView/android.view.View[3]/android.view.View[1]";
        String terminateButton2=reserverPageXpathBase+"android.view.View[1]/android.view.View/android.view.View[6]/android.widget.ListView/android.view.View[3]/android.view.View[1]";
        if(UIElement.byElementIsExist(appiumDriver,By.xpath(terminateButton2))) {
            appiumDriver.findElementByXPath(terminateButton2).click();
        }else if (UIElement.byElementIsExist(appiumDriver,By.xpath(terminateButton1))){
            appiumDriver.findElementByXPath(terminateButton1).click();
        }

        if(UIElement.byElementIsExist(appiumDriver,By.xpath(reserverPageXpathBase+"android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]"))){
            appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]").click();
        }

        if(UIElement.byElementIsExist(appiumDriver,By.xpath(reserverPageXpathBase+"android.view.View[4]/android.view.View/android.view.View[3]/android.view.View[2]"))) {
            appiumDriver.findElementByXPath(reserverPageXpathBase+"android.view.View[4]/android.view.View/android.view.View[3]/android.view.View[2]").click();
        }
    }

    public void clearReservedMeetings(String meetingOwner){
        logger.info("Clear all "+meetingOwner+"'s meeting");
        while (UIElement.byElementIsExist(appiumDriver,By.xpath(findReservedMeetingXpath(meetingOwner)))){
            deleteReservedMeeting(meetingOwner);
        }
    }

}