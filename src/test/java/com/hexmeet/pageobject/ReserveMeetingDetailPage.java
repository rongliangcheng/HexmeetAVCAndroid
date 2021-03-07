package com.hexmeet.pageobject;

import com.hexmeet.Utility.CallType;
import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class ReserveMeetingDetailPage {
    AppiumDriver appiumDriver;
    private Logger log = getLogger(this.getClass().getName());
    HashMap<CallType,String> hashMap = new HashMap<>();

    private String end_call_id="com.cninnovatel.ev:id/btn_end";
    private String video_call_id="com.cninnovatel.ev:id/left_button";
    private String audio_call_id="com.cninnovatel.ev:id/right_button";
    private String conference_num_id="com.cninnovatel.ev:id/conf_num_view";
    private String password_num_id="com.cninnovatel.ev:id/password_view";

    private String back_id="com.cninnovatel.ev:id/back";
    private String head_title_id="com.cninnovatel.ev:id/head_title";

    private String end_call_confirm_xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.TextView";

    public ReserveMeetingDetailPage(AppiumDriver appiumDriver){
        this.appiumDriver=appiumDriver;
        hashMap.put(CallType.AudioCall,audio_call_id);
        hashMap.put(CallType.VideoCall,video_call_id);
    }

    private void meeting_operate(String id){
        log.info("");
        appiumDriver.findElementById(id).click();
    }

    public void start_meeting(CallType callType){
        log.info("");
        meeting_operate(hashMap.get(callType));
    }

    public String get_conference_number_on_page(){
        log.info("");
        return appiumDriver.findElementById(conference_num_id).getAttribute("text");
    }

    public String get_conference_password_on_page(){
        log.info("");
        return appiumDriver.findElementById(password_num_id).getAttribute("text");
    }

    public void back_to_conference_tab_page(){
        log.info("");
        Pause.stop(2.5);
        appiumDriver.findElementById(back_id).click();
    }

    public void end_meeting(){
        log.info("");
        meeting_operate(end_call_id);
        Pause.stop(1);
        appiumDriver.findElementByXPath(end_call_confirm_xpath).click();
    }

    public boolean in_progress_meeting(){
        log.info("");
        return UIElement.byElementIsExist(appiumDriver, By.id(end_call_id));
    }

    public boolean isOnDetailPage(){
        log.info("");
        return UIElement.byElementIsExist(appiumDriver,By.id(head_title_id));
    }


}
