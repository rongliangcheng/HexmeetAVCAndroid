package com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.userprivatemainPage;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import com.hexmeet.pageobject.common.UICommon;
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.SignInPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class UserPrivateMainPage {
    Logger logger = getLogger("UserPrivateMainPage");
    AppiumDriver appiumDriver;

    public UserPrivateMainPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void navigate(String serverAddr,String account,String password){

        logger.info("");
        SignInPage signInPage = new SignInPage(appiumDriver);
        signInPage.navigate();
        signInPage.submit(serverAddr,account,password);
        Pause.stop(0.5);

        if(UIElement.byElementIsExist(appiumDriver, By.id("android:id/button1")))
            UICommon.devicePermissionAllowance(appiumDriver);
    }

    public void meeting(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/conference").click();
    }

    public void joinAMeeting(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/dialing").click();
    }

    public void favoriteContact(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/contacts").click();
    }

    public void contacts(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/contacts").click();
//        Pause.stop(0.5);
//        appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.view.View").click();
    }

    public void aboutMe(){
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/me").click();
    }

}
