package com.hexmeet.pageobject;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class AudioMeetingPage {
    private AppiumDriver appiumDriver;
    private Logger logger = getLogger(this.getClass().getName());

    private String meeting_name_id="com.cninnovatel.ev:id/meeting_name";
    private String timer_id="com.cninnovatel.ev:id/timer_chronometer";
    private String audio_mute_id="com.cninnovatel.ev:id/mic_mute_btn";
    private String end_audio_call_id="com.cninnovatel.ev:id/endcall_btn";
    private String speaker_id="com.cninnovatel.ev:id/speaker_earphone";



    public AudioMeetingPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void mute_umute_mic(){
        logger.info("mute_umute_mic");
        Pause.stop(5);
        appiumDriver.findElementById(audio_mute_id).click();
        Pause.stop(10);
        appiumDriver.findElementById(audio_mute_id).click();
        Pause.stop(5);
    }

    public void speaker_earphone_mode(){
        logger.info("speaker_earphone_mode");
        Pause.stop(5);
        appiumDriver.findElementById(speaker_id).click();
        Pause.stop(10);
        appiumDriver.findElementById(speaker_id).click();
        Pause.stop(5);
    }

    public void hangup_audio_call(){
        logger.info("hangup_audio_call");
        appiumDriver.findElementById(end_audio_call_id).click();
    }

    public boolean isInAudioMeeting(){
        logger.info("isInAudioMeeting");
        return UIElement.byElementIsExist(appiumDriver, By.id(timer_id));
    }


}
