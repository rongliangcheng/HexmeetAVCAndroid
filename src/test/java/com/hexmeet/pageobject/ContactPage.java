package com.hexmeet.pageobject;

import com.hexmeet.Utility.CallType;
import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;

import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class ContactPage {
    private Logger log = getLogger(this.getClass().getName());
    private AppiumDriver appiumDriver;

    private String contact_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView";
    private String videoCall_id="com.cninnovatel.ev:id/start_video_communication_btn";
    private String audioCall_id="com.cninnovatel.ev:id/start_audio_communication_btn";

    private HashMap<CallType,String> hashMap = new HashMap<>() ;

    public ContactPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        hashMap.put(CallType.VideoCall,videoCall_id);
        hashMap.put(CallType.AudioCall,audioCall_id);
    }

    public void call_contact(CallType callType){
        log.info("");
        Pause.stop(5);
        appiumDriver.findElementByXPath(contact_xpath).click();
        Pause.stop(5);
        appiumDriver.findElementById(hashMap.get(callType)).click();
    }


}
