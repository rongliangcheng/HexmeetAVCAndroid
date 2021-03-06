package com.hexmeet.pageoperation;

import com.hexmeet.Utility.Pause;
import com.hexmeet.Utility.UICommon;
import com.hexmeet.pageobject.LoginPage;
import io.appium.java_client.AppiumDriver;
import org.apache.http.impl.cookie.BrowserCompatVersionAttributeHandler;

public class LoginOperations extends LoginPage {
    private AppiumDriver appiumDriver;
    private PrivacyPolicyOperations privacyPolicyOperation;

    public LoginOperations(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        privacyPolicyOperation = new PrivacyPolicyOperations(appiumDriver);
    }

    private void login_common(String rcm_server, String username, String password){
        fill_in_server_address(rcm_server);
        fill_in_username(username);
        fill_in_password(password);
    }

    public void login(String rcm_server, String username, String password){
        privacyPolicyOperation.ok();
        UICommon.devicePermissionAllowance(appiumDriver);
        Pause.stop(2);
        choose_more(false);
        login_common(rcm_server,username,password);
        login_confirm();
    }

    public void login_only(String rcm_server, String username, String password){
        Pause.stop(2);
        choose_more(false);
        login_common(rcm_server,username,password);
        login_confirm();
    }

    public void login_and_accept_access_permission(String rcm_server, String username, String password){
        privacyPolicyOperation.ok();
        UICommon.devicePermissionAllowance(appiumDriver);
        Pause.stop(2);
        choose_more(false);
        login_common(rcm_server,username,password);
        login_confirm();
        Pause.stop(5);
        UICommon.devicePermissionAllowanceAfterLogin(appiumDriver);
    }

    public void login_with_port(String rcm_server, String username, String password,String port){
        privacyPolicyOperation.ok();
        UICommon.devicePermissionAllowance(appiumDriver);
        Pause.stop(2);
        choose_more(true);
        login_common(rcm_server,username,password);
        fill_in_port(port);
        login_confirm();
    }

    public void login_with_port_and_accept_access_permission(String rcm_server, String username, String password,String port){
        privacyPolicyOperation.ok();
        UICommon.devicePermissionAllowance(appiumDriver);
        Pause.stop(2);
        choose_more(true);
        login_common(rcm_server,username,password);
        fill_in_port(port);
        login_confirm();
        Pause.stop(5);
        UICommon.devicePermissionAllowanceAfterLogin(appiumDriver);
    }

}
