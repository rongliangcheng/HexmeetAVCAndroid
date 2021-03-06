package com.hexmeet.pageobject;

import com.hexmeet.Utility.UIElement;
import com.hexmeet.base.CommonValue;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class ConferenceTabPage {
    private Logger log = getLogger(this.getClass().getName());
    private String conference_tab_id="com.cninnovatel.ev:id/conference";
    private String dialing_tab_id="com.cninnovatel.ev:id/dialing";
    private String contact_tab_id="com.cninnovatel.ev:id/contact";
    private String me_tab_id="com.cninnovatel.ev:id/me";

    private String my_meeting_id="com.cninnovatel.ev:id/my_room_id";

    private String reserved_meeting_xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.FrameLayout/android.widget.LinearLayout";

    private AppiumDriver appiumDriver;

    public ConferenceTabPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void clear_reserved_meetings(){
        log.info("");
        while (UIElement.byElementIsExist(appiumDriver, By.xpath(reserved_meeting_xpath))){
            appiumDriver.findElementByXPath(reserved_meeting_xpath).click();

            ReserveMeetingEditPage reserveMeetingEditPage = new ReserveMeetingEditPage(appiumDriver);
            ReserveMeetingDetailPage reserveMeetingDetailPage = new ReserveMeetingDetailPage(appiumDriver);
            if(reserveMeetingDetailPage.in_progress_meeting()){
                reserveMeetingDetailPage.end_meeting();
            }else{
                reserveMeetingEditPage.delete_reserved_meeting();
            }
        }
    }

    public void reserved_meeting_info(){
        log.info("");
        appiumDriver.findElementByXPath(reserved_meeting_xpath).click();
    }


    public boolean isOnConferenceTabPage(){
        log.info("");
        try{
            appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            appiumDriver.findElementById(conference_tab_id);
        }catch (Exception e){
            appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
            return false;
        }
        appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return true;
    }


}
