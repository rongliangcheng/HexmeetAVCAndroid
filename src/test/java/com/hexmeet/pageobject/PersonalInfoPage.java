package com.hexmeet.pageobject;

import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

public class PersonalInfoPage {
    AppiumDriver appiumDriver;

    private Logger log = getLogger(this.getClass().getName());

    private String image_id="com.cninnovatel.ev:id/pic";
    private String choose_pic_from_photo_id="com.cninnovatel.ev:id/tv_content2";
    private String choose_album_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.GridView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.ImageView";
    private String first_image_xpath="//android.widget.FrameLayout[@content-desc=\"拍摄于2021年2月26日 11:17:59的图片\"]/android.widget.FrameLayout/android.widget.TextView";
    private String second_image_xpath="//android.widget.FrameLayout[@content-desc=\"拍摄于2021年2月26日 11:17:31的图片\"]/android.widget.FrameLayout/android.widget.TextView";

    private String submit_confirm_id="com.miui.gallery:id/ok";

    private String display_name_id="com.cninnovatel.ev:id/display_name";
    private String display_name_text_id="com.cninnovatel.ev:id/et_content";
    private String change_display_name_confirm_id="com.cninnovatel.ev:id/submit";


    public PersonalInfoPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void changeImage(){
        log.info("");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Pause.stop(1);
        appiumDriver.findElementById(image_id).click();
        appiumDriver.findElementById(choose_pic_from_photo_id).click();
        appiumDriver.findElementByXPath(choose_album_xpath).click();
        if( day%2 == 0 ){
            appiumDriver.findElementByXPath(first_image_xpath).click();
        }else {
            appiumDriver.findElementByXPath(second_image_xpath).click();
        }
        appiumDriver.findElementById(submit_confirm_id).click();
        Pause.stop(1);
    }

    public String change_display_name(String name){
        log.info("");
        appiumDriver.findElementById(display_name_id).click();
        appiumDriver.findElementById(display_name_text_id).clear();
        Pause.stop(1);
        appiumDriver.findElementById(display_name_text_id).sendKeys(name);
        appiumDriver.findElementById(change_display_name_confirm_id).click();
        Pause.stop(1);
        try {
            PointOption pointOption = new PointOption().withCoordinates(300, 300);
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction.press(pointOption).release().perform();
        } catch (Exception e){

        }
        Pause.stop(1);
        return appiumDriver.findElementById(display_name_id).getAttribute("text");
    }


}
