package com.hexmeet.pageobject;

import com.hexmeet.base.CommonValue;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class LoginPage {
    private String rcmserver_id="com.cninnovatel.ev:id/rcmServer";
    private String username_id="com.cninnovatel.ev:id/username";
    private String password_id="com.cninnovatel.ev:id/password";
    private String more_id="com.cninnovatel.ev:id/more";
    private String login_id="com.cninnovatel.ev:id/login";
    private String port_id="com.cninnovatel.ev:id/port";

    private AppiumDriver appiumDriver;
    private Logger log = getLogger(this.getClass().getName());

    public LoginPage(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }

    public void fill_in_items(String id, String str){
        log.info("");
        appiumDriver.findElementById(id).clear();
        appiumDriver.findElementById(id).sendKeys(str);
    }

    public void fill_in_server_address(String server){
        log.info("");
        fill_in_items(rcmserver_id,server);
    }

    public void fill_in_username(String username){
        log.info("");
        fill_in_items(username_id,username);
    }

    public void fill_in_password(String password){
        log.info("");
        fill_in_items(password_id,password);
    }

    public void fill_in_port(String port){
        log.info("");
        fill_in_items(port_id,port);
    }

    public void choose_more(boolean flag){
        log.info("");
        if(flag){
            if(appiumDriver.findElementById(more_id).getAttribute("checked").equals("false")){
                appiumDriver.findElementById(more_id).click();
            }
        }

        if(!flag){
            if(appiumDriver.findElementById(more_id).getAttribute("checked").equals("true")){
                appiumDriver.findElementById(more_id).click();
            }
        }
    }

    public void login_confirm(){
        log.info("");
        appiumDriver.findElementById(login_id).click();
    }

    public boolean isOnLoginPage(){
        log.info("");
        try{
            appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            appiumDriver.findElementById(login_id);
        }catch (Exception e){
            appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
            return false;
        }
        appiumDriver.manage().timeouts().implicitlyWait(CommonValue.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return true;
    }
}
