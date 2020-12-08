package com.hexmeet.pageobject.common;

import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;

public class JoinAMeeting {

    AppiumDriver appiumDriver;

    String userNameInMeeting = "com.hexmeet.hjt:id/name_dialog";
    String videoJoin = "com.hexmeet.hjt:id/video_btn";
    String audioJoin = "com.hexmeet.hjt:id/audio_btn";
    String camera_switch = "com.hexmeet.hjt:id/close_camera_switch";
    String mic_switch = "com.hexmeet.hjt:id/close_mic_switch";

    public JoinAMeeting(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void meetingNumber(String string){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/call_number").sendKeys(string);
    }

    public void muteCameraSwitch(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/close_camera_switch").click();
    }

    public void muteMicSwitch(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/close_mic_switch").click();
    }

    public void call(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/dial_btn").click();
    }
}
