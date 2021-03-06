package com.hexmeet.pageobject;

import com.hexmeet.Utility.CallType;
import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

@SuppressWarnings("unchecked")
public class ReserveMeetingEditPage {
    AppiumDriver appiumDriver;
    private Logger log = getLogger(this.getClass().getName());
    HashMap<CallType,String> hashMap = new HashMap();

    private String delete_reserved_meeting_id="com.cninnovatel.ev:id/btn_delete";
    private String delete_reserved_meeting_confirm_xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.TextView";
    private String edit_reserved_meeting_id="com.cninnovatel.ev:id/btn_edit";

    private String confirm_edit_id="com.cninnovatel.ev:id/left_button";
    private String back_after_edit_id="com.cninnovatel.ev:id/back";
    private String start_meeting_id="com.cninnovatel.ev:id/left_button";
    private String start_meeting_cancel_id="com.cninnovatel.ev:id/cancel";
    private String start_meeting_confirm_id="com.cninnovatel.ev:id/submit";

    private String remark_edit_id="com.cninnovatel.ev:id/remark_view";
    private String remark_edit_context_id="com.cninnovatel.ev:id/property_value";
    private String remark_edit_confirm_id="com.cninnovatel.ev:id/right_btn";

    private String delete_participant_xpath="(//android.widget.ImageView[@content-desc=\"HexMeet\"])[3]";
    private String delete_first_participant_id="com.cninnovatel.ev:id/btn_delete";
    private String add_participant_id="com.cninnovatel.ev:id/gridview_img";

    private String first_participant_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]";
    private String second_participant_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]";
    private String invite_confirm_id="com.cninnovatel.ev:id/invite";


    private String audio_call_id="com.cninnovatel.ev:id/btn_audio";
    private String video_call_id="com.cninnovatel.ev:id/btn_video";
    private String close_id="com.cninnovatel.ev:id/close";

    private String accept_in_audio_id="com.cninnovatel.ev:id/accept_in_audio";
    private String accept_in_video_id="com.cninnovatel.ev:id/accept_in_video";
    private String reject_id="com.cninnovatel.ev:id/reject";

    public ReserveMeetingEditPage(AppiumDriver appiumDriver){
        log.info("");
        this.appiumDriver = appiumDriver;
        hashMap.put(CallType.AudioCall,audio_call_id);
        hashMap.put(CallType.VideoCall,video_call_id);
    }

    public void delete_reserved_meeting(){
        log.info("");
        appiumDriver.findElementById(delete_reserved_meeting_id).click();
        Pause.stop(2);
        appiumDriver.findElementByXPath(delete_reserved_meeting_confirm_xpath).click();
    }

    public void edit_reserved_meeting() {
        log.info("");
        appiumDriver.findElementById(edit_reserved_meeting_id).click();
    }

    public void edit_remark(String remark_str){
        log.info("");
        appiumDriver.findElementById(remark_edit_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(remark_edit_context_id).clear();
        appiumDriver.findElementById(remark_edit_context_id).sendKeys(remark_str);
        appiumDriver.findElementById(remark_edit_confirm_id).click();
    }


    public void delete_participant_and_add_new(){
        log.info("");
        appiumDriver.findElementByXPath(delete_participant_xpath).click();
        appiumDriver.findElementById(delete_first_participant_id).click();
        appiumDriver.findElementById(add_participant_id).click();
        appiumDriver.findElementByXPath(first_participant_xpath).click();
        appiumDriver.findElementByXPath(second_participant_xpath).click();
        appiumDriver.findElementById(invite_confirm_id).click();
    }


    public void edit_reserved_meeting_confirm(){
        log.info("");
        Pause.stop(2);
        appiumDriver.findElementById(confirm_edit_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(back_after_edit_id).click();
    }

    public void start_meeting_now_and_cancel(){
        log.info("");
        appiumDriver.findElementById(start_meeting_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(start_meeting_cancel_id).click();
    }

    public void start_meeting_now_and_confirm(CallType callType){
        log.info("");
        appiumDriver.findElementById(start_meeting_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(start_meeting_confirm_id).click();
        Pause.stop(2);
        appiumDriver.findElementById(hashMap.get(callType)).click();
    }

    public boolean future_meeting(){
        log.info("");
        return UIElement.byElementIsExist(appiumDriver, By.id(delete_reserved_meeting_id));
    }

}
