package com.hexmeet.pageobject;

import com.hexmeet.Utility.CallType;
import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;
import okhttp3.Call;
import org.slf4j.Logger;

import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class MyMeetingRoomPage {
    AppiumDriver appiumDriver;
    private Logger log = getLogger(this.getClass().getName());
    private HashMap<CallType,String> hashMap = new HashMap<>();

    private String video_call_id="com.cninnovatel.ev:id/start_video";
    private String audio_call_id="com.cninnovatel.ev:id/start_audio";

    public MyMeetingRoomPage(AppiumDriver appiumDriver){
        this.appiumDriver= appiumDriver;
        hashMap.put(CallType.AudioCall,audio_call_id);
        hashMap.put(CallType.VideoCall,video_call_id);
    }

    public void start_call(CallType callType){
        log.info("");
        Pause.stop(1);
        appiumDriver.findElementById(hashMap.get(callType)).click();
    }

}
