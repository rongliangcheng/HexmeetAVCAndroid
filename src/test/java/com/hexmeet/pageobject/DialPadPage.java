package com.hexmeet.pageobject;

import com.hexmeet.Utility.CallType;
import com.hexmeet.Utility.Pause;
import com.hexmeet.base.CommonValue;
import io.appium.java_client.AppiumDriver;
import okhttp3.Call;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class DialPadPage {
    private Logger log = getLogger(this.getClass().getName());
    private AppiumDriver appiumDriver;

    private HashMap<CallType,String> hashMap = new HashMap<>();
    private String called_number_id="com.cninnovatel.ev:id/call_number";

    private String video_call_id="com.cninnovatel.ev:id/video_call_btn";
    private String audio_call_id="com.cninnovatel.ev:id/audio_call_btn";

    private String dial_pad_id="com.cninnovatel.ev:id/dialing";

    public DialPadPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        hashMap.put(CallType.VideoCall,video_call_id);
        hashMap.put(CallType.AudioCall,audio_call_id);
    }

    public void start_meeting(String conferenceNum, CallType callType){
        log.info("");
        appiumDriver.findElementById(called_number_id).sendKeys(conferenceNum);
        appiumDriver.findElementById(called_number_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(hashMap.get(callType)).click();
    }

    public boolean isOnDialPadPage(){
        log.info("");
        try{
            appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            appiumDriver.findElementById(dial_pad_id);
        }catch (Exception e){
            appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
            return false;
        }
        appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return true;
    }
}
