package com.hexmeet.pageobject.common;

import com.hexmeet.Utility.Pause;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Keys;


public class CallAContactInStructure {
    AppiumDriver appiumDriver;

    public CallAContactInStructure(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void findAContactInStructure(String name){
        Pause.stop(0.5);
        appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.view.View").click();
        Pause.stop(5);
        appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText").sendKeys(name);
        appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText").click();

        appiumDriver.getKeyboard().sendKeys(Keys.RETURN);
        Pause.stop(1);
        appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View").click();

    }

    public void callTheContact(){
        Pause.stop(0.5);
        //Used for OPPO phone
        appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View").click();
        //Used for VIVO phone
        //appiumDriver.findElementByXPath("//android.widget.Image[@content-desc=\"cx4g3VPBEgprki4QXvfiVaGj54yW3z+rMFwF5f8jUMD86ThQdlJP8sNICyTCKOSseUxuPPwWyewSY9xHAHjqW9jHmcueY83hmyxUOOip2pnVDBQAAAABJRU5ErkJggg==\"]").click();
    }
}
