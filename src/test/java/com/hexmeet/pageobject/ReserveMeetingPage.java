package com.hexmeet.pageobject;

import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReserveMeetingPage {

    private AppiumDriver appiumDriver;

    Logger log = LoggerFactory.getLogger(this.getClass());

    private String starttime_id="com.cninnovatel.ev:id/starttime_arrow";
    private String endtime_id="com.cninnovatel.ev:id/endtime_arrow";
    private String confirm_time_id="com.cninnovatel.ev:id/ok";

    private String password_text_id="com.cninnovatel.ev:id/password_view";
    private String remark_id="com.cninnovatel.ev:id/remark_view";
    private String remark_content_id="com.cninnovatel.ev:id/property_value";
    private String confirm_id="com.cninnovatel.ev:id/right_btn";

    private String add_pariticipant_id="com.cninnovatel.ev:id/gridview_img";
    private String remove_participant_xpath="(//android.widget.ImageView[@content-desc=\"HexMeet\"])[3]";
    private String remove_first_participant_xpath="(//android.widget.ImageView[@content-desc=\"HexMeet\"])[2]";

    private String first_participant_xpath="(//android.widget.ImageView[@content-desc=\"头像\"])[1]";
    private String second_participant_xpath="(//android.widget.ImageView[@content-desc=\"头像\"])[3]";

    private String invite_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.Button";

    private String reserve_confirm_id="com.cninnovatel.ev:id/left_button";

    private String back_after_reserve_id="com.cninnovatel.ev:id/back";

    public ReserveMeetingPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void choose_time(){
        log.info("");
        appiumDriver.findElementById(starttime_id).click();
        Pause.stop(2);

        TouchAction touchAction = new TouchAction(appiumDriver);

        PointOption d_start = new PointOption().withCoordinates(440,1200);
        PointOption d_end = new PointOption().withCoordinates(440,850);
        touchAction.press(d_start).moveTo(d_end).release().perform();

        PointOption h_start = new PointOption().withCoordinates(625,1200);
        PointOption h_end = new PointOption().withCoordinates(625,850);
        touchAction.press(h_start).moveTo(h_end).release().perform();

        appiumDriver.findElementById(confirm_time_id).click();

    }

    public void set_meeting_password(String password){
        log.info("");
        log.info("set_meeting_password");
        appiumDriver.findElementById(password_text_id).sendKeys(password);
    }

    public void set_meeting_remark(String remark){
        log.info("");
        appiumDriver.findElementById(remark_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(remark_content_id).sendKeys(remark);
        appiumDriver.findElementById(confirm_id).click();
    }

    public void delete_participant(){
        log.info("delete_participant");
        appiumDriver.findElementByXPath(remove_participant_xpath).click();
        appiumDriver.findElementByXPath(remove_first_participant_xpath).click();
    }

    public void add_participants(){
        log.info("add_participants");
        appiumDriver.findElementById(add_pariticipant_id).click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(first_participant_xpath).click();
        appiumDriver.findElementByXPath(second_participant_xpath).click();
        appiumDriver.findElementByXPath(invite_xpath).click();
    }

    public void add_other_participant(){
        log.info("add_participants");
        appiumDriver.findElementById(add_pariticipant_id).click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(first_participant_xpath).click();
        appiumDriver.findElementByXPath(invite_xpath).click();
    }

    public void reserve_confirm(){
        log.info("reserve_confirm");
        appiumDriver.findElementById(reserve_confirm_id).click();
    }

    public void back_after_reserve_confirm(){
        log.info("back_after_reserve_confirm");
        appiumDriver.findElementById(back_after_reserve_id).click();
    }



}
