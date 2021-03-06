package com.hexmeet.pageobject;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MeetingPage {
    private AppiumDriver appiumDriver;
    private Logger logger = getLogger(this.getClass().getName());
    private TouchAction action ;
    private PointOption pointOption ;

    private String mic_mute_id="com.cninnovatel.ev:id/mic_mute_btn";
    private String video_mute_id="com.cninnovatel.ev:id/video_mute_btn";
    private String camera_switch_id="com.cninnovatel.ev:id/camera_switch_btn";
    private String end_call_id="com.cninnovatel.ev:id/layout_end_call";

    private String media_statistics_id="com.cninnovatel.ev:id/call_statistics_btn";
    private String exit_media_statistics_id="com.cninnovatel.ev:id/call_statistics_back";

    private String video_rx_resolution_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.TextView[4]";
    private String video_tx_resolution_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[4]/android.widget.TextView[4]";

    private String local_preview_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.View";
    private String remote_video_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.View";

    public MeetingPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        action = new TouchAction(appiumDriver);
        pointOption = new PointOption();
        pointOption.withCoordinates(100,50);
    }

    private void touchScreenToShowButton(){
        logger.info("touchScreenToShowButton");
        Pause.stop(15);
        action.tap(pointOption).release().perform();
    }

    private void av_operation_double_click(String id){
        touchScreenToShowButton();
        appiumDriver.findElementById(id).click();
        Pause.stop(15);
        touchScreenToShowButton();
        appiumDriver.findElementById(id).click();
        Pause.stop(10);
    }

    public void mute_umute_audio(){
        logger.info("mute_umute_audio");
        av_operation_double_click(mic_mute_id);
    }

    public void mute_umute_video(){
        logger.info("mute_umute_video");
        av_operation_double_click(video_mute_id);
    }

    public void switch_camera(){
        logger.info("switch_camera");
        av_operation_double_click(camera_switch_id);
    }

    public void hangup_call(){
        logger.info("hangup_call");
        touchScreenToShowButton();
        appiumDriver.findElementById(end_call_id).click();
        Pause.stop(2);
    }

    public void show_media_statistics(){
        logger.info("show media statistics");
        touchScreenToShowButton();
        appiumDriver.findElementById(media_statistics_id).click();
    }

    public String video_tx_resolution(){
        logger.info("Video transmit resolution");
        return appiumDriver.findElementByXPath(video_tx_resolution_xpath).getAttribute("text");
    }

    public String video_rx_resolution(){
        logger.info("Video transmit resolution");
        return appiumDriver.findElementByXPath(video_rx_resolution_xpath).getAttribute("text");
    }

    public void exit_media_statistics(){
        logger.info("exit media statistics");
        appiumDriver.findElementById(exit_media_statistics_id).click();
    }


    public boolean isInMeeting(){
        logger.info("isInMeeting");
        return UIElement.byElementIsExist(appiumDriver, By.xpath(local_preview_xpath));
    }
}
