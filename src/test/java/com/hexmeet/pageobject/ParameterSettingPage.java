package com.hexmeet.pageobject;

import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.slf4j.Logger;

import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class ParameterSettingPage {
    AppiumDriver appiumDriver;
    private Logger log = getLogger(this.getClass().getName());

    HashMap<String,String> hashMap = new HashMap<>();

    private String status_ok="Succeed";

    private String network_info_id="com.cninnovatel.ev:id/tv_netinfo";
    private String register_status="com.cninnovatel.ev:id/tv_register_status";
    private String network_info_back_id="com.cninnovatel.ev:id/back_btn";
    private String call_speed_id="com.cninnovatel.ev:id/tv_call_speed";
    private String call_speed_back_id="com.cninnovatel.ev:id/back_btn";
    private String set_call_speed_id="com.cninnovatel.ev:id/tv_call_speed";
    private String call_speed_prefix="com.cninnovatel.ev:id/tv_callspeed_";
    private String return_from_call_speed_id="com.cninnovatel.ev:id/back_btn";
    private String switch_call_1080p_id="com.cninnovatel.ev:id/switch_call_1080p";
    private String hfr_video_id="com.cninnovatel.ev:id/call_hfr_video";
    private String auto_answer_id="com.cninnovatel.ev:id/auto_answer";
    private String send_log_id="com.cninnovatel.ev:id/send_log";
    private String send_log_cancel_id="android:id/button2";
    private String send_suggestions_id="com.cninnovatel.ev:id/send_logs";
    private String send_suggestion_back_id="com.cninnovatel.ev:id/back_icon";
    private String setting_back_id="com.cninnovatel.ev:id/back_btn";

    public ParameterSettingPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        hashMap.put("128","one");
        hashMap.put("256","two");
        hashMap.put("384","three");
        hashMap.put("512","four");
        hashMap.put("768","five");
        hashMap.put("1024","six");
        hashMap.put("1536","seven");
        hashMap.put("2048","eight");
    }

    public boolean check_network(){
        log.info("");
        appiumDriver.findElementById(network_info_id).click();
        Pause.stop(1);
        boolean status = status_ok.equals(appiumDriver.findElementById(register_status).getAttribute("text"));
        appiumDriver.findElementById(network_info_back_id).click();
        return status;
    }

    public void change_bandwidth(String bandwidth){
        log.info("");
        appiumDriver.findElementById(call_speed_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(set_call_speed_id).click();
        Pause.stop(1.5);
        appiumDriver.findElementById(call_speed_prefix+hashMap.get(bandwidth)).click();
        Pause.stop(1);
        appiumDriver.findElementById(return_from_call_speed_id).click();
        Pause.stop(1);

    }

    public void turn_on_1080P(boolean on){
        log.info("");
        if( on && "false".equals(appiumDriver.findElementById(switch_call_1080p_id).getAttribute("checked"))){
            appiumDriver.findElementById(switch_call_1080p_id).click();
        } else if( !on && "true".equals(appiumDriver.findElementById(switch_call_1080p_id).getAttribute("checked"))){
            appiumDriver.findElementById(switch_call_1080p_id).click();
        }
    }

    public void turn_on_high_rate_video(boolean on){
        log.info("");
        if(on && "false".equals(appiumDriver.findElementById(hfr_video_id).getAttribute("checked"))){
            appiumDriver.findElementById(hfr_video_id).click();
        } else if ( !on && "true".equals(appiumDriver.findElementById(hfr_video_id).getAttribute("checked"))){
            appiumDriver.findElementById(hfr_video_id).click();
        }
    }

    public void send_diagnostic_logs(){
        log.info("");
        appiumDriver.findElementById(send_log_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(send_log_cancel_id).click();
    }



    public void send_suggestions(){
        log.info("");
        appiumDriver.findElementById(send_suggestions_id).click();
        Pause.stop(1);
        appiumDriver.findElementById(send_suggestion_back_id).click();
    }

    public void return_to_me_tab_page(){
        log.info("");
        appiumDriver.findElementById(setting_back_id).click();
        Pause.stop(1);
    }
}
