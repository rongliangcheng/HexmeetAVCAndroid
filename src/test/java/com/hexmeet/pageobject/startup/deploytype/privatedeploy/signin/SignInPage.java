package com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UIElement;
import com.hexmeet.pageobject.common.UICommon;
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.PrivateDeployPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SignInPage {

    private AppiumDriver appiumDriver;

    public SignInPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void navigate(){
        //LOGGER.info("Sign in page");
        PrivateDeployPage privateDeployPage = new PrivateDeployPage(appiumDriver);
        privateDeployPage.navigate();
        privateDeployPage.signIn();
    }

    public void submit(String serveraddr,String accout,String password){
        //LOGGER.info("Sign in page Sumbit");
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_server").sendKeys(serveraddr);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_name").sendKeys(accout);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_password").sendKeys(password);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_btn").click();

        Pause.stop(0.5);

        if(UIElement.byElementIsExist(appiumDriver, By.id("com.lbe.security.miui:id/permission_allow_foreground_only_button")))
            UICommon.devicePermissionAllowance(appiumDriver);
    }

    public void submit(String serveraddr,String accout,String password,String port,boolean useHttps){
        //LOGGER.info("Sign in page Submit");
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/text_advance_setting").click();
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_port").sendKeys(port);
        if(useHttps)
            appiumDriver.findElementById("com.hexmeet.hjt:id/https_switch").click();
        Pause.stop(0.5);
        appiumDriver.findElementById("com.hexmeet.hjt:id/save_btn").click();

        Pause.stop(0.5);

        appiumDriver.findElementById("com.hexmeet.hjt:id/login_server").sendKeys(serveraddr);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_name").sendKeys(accout);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_password").sendKeys(password);
        appiumDriver.findElementById("com.hexmeet.hjt:id/login_btn").click();

        Pause.stop(0.5);

        if(UIElement.byElementIsExist(appiumDriver, By.id("com.lbe.security.miui:id/permission_allow_foreground_only_button")))
            UICommon.devicePermissionAllowance(appiumDriver);
    }

    public boolean isOnSignInPage(){
        return UIElement.byElementIsExist(appiumDriver,By.id("com.hexmeet.hjt:id/login_title"));
    }


}
