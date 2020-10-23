package com.hexmeet.pageobject.startup.deploytype.privatedeploy.joinmeeting;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import com.hexmeet.pageobject.common.UICommon;
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.PrivateDeployPage;
import com.hexmeet.pageobject.startup.deploytype.publicdeploy.signin.userpublicmainpage.publicmeeting.PublicMeeting;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class PrivateDirectJoinAMeetingPage {
    AppiumDriver appiumDriver;

    public PrivateDirectJoinAMeetingPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void navigate(){
        PrivateDeployPage privateDeployPage = new PrivateDeployPage(appiumDriver);
        privateDeployPage.navigate();
        privateDeployPage.joinAMeeting();
    }

    public void muteUmuteCamera(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/close_camera").click();
    }

    public void muteUmuteMic(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/close_mic").click();
    }

    public void joinAMeeting(String serveraddr,String number,String username){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_server").sendKeys(serveraddr);
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_conf_id").sendKeys(number);
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_conf_name").sendKeys(username);
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_btn").click();

        Pause.stop(0.5);
        if(UIElement.byElementIsExist(appiumDriver,By.id("com.lbe.security.miui:id/permission_allow_foreground_only_button")))
            UICommon.devicePermissionAllowance(appiumDriver);
    }

    public boolean isOnGuestPage(){
        return UIElement.byElementIsExist(appiumDriver, By.id("com.hexmeet.hjt:id/login_title"));
    }
}
