package com.hexmeet.pageobject;

import com.hexmeet.Utility.Pause;
import com.hexmeet.base.CommonValue;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class PageNavigation {
    AppiumDriver appiumDriver;
    private Logger log = getLogger(this.getClass().getName());
    private String my_meeting_room_id="com.cninnovatel.ev:id/my_room_id";
    private String my_meeting_room_xpath_2nd="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]";
    private String conference_tab_id="com.cninnovatel.ev:id/conference";
    private String reserve_meeting_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ImageView";
    private String reserve_meeting_xpath_2nd="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]";
    private String dialing_tab_id="com.cninnovatel.ev:id/dialing";
    private String contact_tab_id="com.cninnovatel.ev:id/contact";
    private String me_tab_id="com.cninnovatel.ev:id/me";


    //me page
    private String personal_information_id="com.cninnovatel.ev:id/avatar";
    private String logout_id="com.cninnovatel.ev:id/logout";
    private String setting_id="com.cninnovatel.ev:id/parameter_setting";

    //about page
    private String about_xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.TextView";

    public PageNavigation(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    //when there is reserved meeting, the xpath will be different
    public void to_my_meeting_room_page(){
        log.info("");
        if(isExisting_id(my_meeting_room_id)) {
            appiumDriver.findElementById(my_meeting_room_id).click();
        }else{
            appiumDriver.findElementByXPath(my_meeting_room_xpath_2nd).click();
        }
    }

    //when there is reserved meeting, the xpath will be different
    public void to_reserve_meeting_page(){
        log.info("");
        if(isExisting_xpath(reserve_meeting_xpath)) {
            appiumDriver.findElementByXPath(reserve_meeting_xpath).click();
        }else{
            appiumDriver.findElementByXPath(reserve_meeting_xpath_2nd).click();
        }
    }

    public void to_conference_tab_page(){
        log.info("");
        Pause.stop(1);
        appiumDriver.findElementById(conference_tab_id).click();
    }

    public void to_dialing_tab_page(){
        log.info("");
        Pause.stop(1);
        appiumDriver.findElementById(dialing_tab_id).click();
    }

    public void to_contact_tab_page(){
        log.info("");
        Pause.stop(1);
        appiumDriver.findElementById(contact_tab_id).click();
    }

    public void to_me_tab_page(){
        log.info("");
        Pause.stop(1);
        appiumDriver.findElementById(me_tab_id).click();
    }
    public void to_setting_page() {
        log.info("");
        Pause.stop(1);
        appiumDriver.findElementById(setting_id).click();
    }
    public void to_setting_from_conference_tab(){
        log.info("");
        to_me_tab_page();
        to_setting_page();
    }
    public void to_conference_from_setting(){
        log.info("");

    }
    public void to_about_page(){
        log.info("");
        Pause.stop(1);
        appiumDriver.findElementByXPath(about_xpath).click();
    }

    public void to_personal_information_page(){
        log.info("");
        Pause.stop(1);
        appiumDriver.findElementById(personal_information_id).click();
    }

    public void to_login_page(){
        log.info("");
        to_me_tab_page();
        to_personal_information_page();
        appiumDriver.findElementById(logout_id).click();
    }

    private boolean isExisting_xpath(String xpath){
        log.info("");
        try{
            appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            appiumDriver.findElementByXPath(xpath);
        } catch (Exception e){
            appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
            return false;
        }
        appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return true;
    }

    private boolean isExisting_id(String id){
        log.info("");
        try{
            appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            appiumDriver.findElementById(id);
        }catch (Exception e){
            appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
            return false;
        }
        appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return true;
    }
}
