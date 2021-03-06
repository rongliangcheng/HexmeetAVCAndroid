package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.UIElement
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.Utility.UICommon
import com.hexmeet.pageobject.ConferenceTabPage
import com.hexmeet.pageobject.LoginPage
import com.hexmeet.pageobject.common.meetingpage.MeetingMainPage
import com.hexmeet.pageoperation.LoginOperations
import com.hexmeet.pageoperation.PrivacyPolicyOperations
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit


@Title("登录")
@Narrative("测试用户登录场景：正常，用户名错，密码错，服务器错")
class SignIn extends EndpointSystemTestSpec{

    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    @Shared
    long IMPLICIT_WAIT_TIME = 30

    @Shared
    String configFileName="config.json"

    @Shared
    String androidKeyword_1 = "Android_3"

    @Shared
    String rcm_server = "hexmeet"

    @Shared
    String PORT = "443"

    @Shared
    String userName = "hexautotest6"

    @Shared
    String password = "123456"

    def setupSpec(){

        log.info("Setup")
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }



//    @Retry
    def "正常用户密码登录"(){
        when:"初始化AppiumDriver"
        androidEndpoint.initialAppiumEndpointfromJson(configFileName,androidKeyword_1)
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()


        and:"以hexautotest6/123456登录"
        LoginOperations  loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(rcm_server,userName,password)

        Pause.stop(5)
        showPicInReportPortrait(appiumDriver,"登录界面")

        ConferenceTabPage conferenceTabPage = new ConferenceTabPage(appiumDriver)
        boolean isOnConferenceTabPage = conferenceTabPage.isOnConferenceTabPage()


        then:"成功登录"
        assert isOnConferenceTabPage
    }

//    @Retry
    def "正常用户带port登录"(){
        when:"初始化AppiumDriver"
        androidEndpoint.initialAppiumEndpointfromJson(configFileName,androidKeyword_1)
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        and:"以hexautotest6/123456/port 80登录"
        LoginOperations  loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_with_port_and_accept_access_permission(rcm_server,userName,password,PORT)

        Pause.stop(4)
        showPicInReportPortrait(appiumDriver,"登录界面")

        ConferenceTabPage conferenceTabPage = new ConferenceTabPage(appiumDriver)
        boolean isOnConferenceTabPage = conferenceTabPage.isOnConferenceTabPage()


        then:"成功登录"
        assert isOnConferenceTabPage
    }

    @Retry
    def "以错误的port 8000登录"(){
        when:"初始化AppiumDriver"
        androidEndpoint.initialAppiumEndpointfromJson(configFileName,androidKeyword_1)
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        and:"以hexautotest6/123456/port 8000登录"
        LoginOperations  loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_with_port(rcm_server,userName,password,"8000")

        Pause.stop(5)
        showPicInReportPortrait(appiumDriver,"服务器不可达")

        Pause.stop(20)

        LoginPage loginPage = new LoginPage(appiumDriver)
        boolean isOnLoginPage = loginPage.isOnLoginPage()

        then:"登录失败"
        assert isOnLoginPage
    }

//    @Retry
    def "以不存在的用户登录"(){
        when:"初始化AppiumDriver"
        androidEndpoint.initialAppiumEndpointfromJson(configFileName,androidKeyword_1)
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()


        and:"以hjtautotest/123456/port 80登录"
        LoginOperations  loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login(rcm_server,"userName",password)

        Pause.stop(2)
        showPicInReportPortrait(appiumDriver,"用户名或密码错误")

        Pause.stop(4)
        if(UIElement.byElementIsExist(appiumDriver, By.id("com.lbe.security.miui:id/permission_allow_foreground_only_button")))
            UICommon.devicePermissionAllowance(appiumDriver);

        LoginPage loginPage = new LoginPage(appiumDriver)
        boolean isOnLoginPage = loginPage.isOnLoginPage()

        then:"登录失败"
        assert isOnLoginPage
    }


    def "用户密码错误登录5次被锁"(){
        when:"初始化AppiumDriver"
        androidEndpoint.initialAppiumEndpointfromJson(configFileName,androidKeyword_1)
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        and:"以hexautotest6/12345 登录 第1次"
        String password="12345"
        LoginOperations  loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login(rcm_server,userName,password)

        Pause.stop(0.6)
        showPicInReportPortrait(appiumDriver,"密码错误第1次")

        and:"以hexautotest6/12345 登录 第2次"
        Pause.stop(5)
        loginOperations.login_only(rcm_server,userName,password)
        Pause.stop(0.6)
        showPicInReportPortrait(appiumDriver,"密码错误第2次")

        and:"以hexautotest6/12345 登录 第3次"
        Pause.stop(5)
        loginOperations.login_only(rcm_server,userName,password)
        Pause.stop(0.6)
        showPicInReportPortrait(appiumDriver,"密码错误第3次")

        and:"以hexautotest6/12345 登录 第4次"
        Pause.stop(5)
        loginOperations.login_only(rcm_server,userName,password)
        Pause.stop(0.6)
        showPicInReportPortrait(appiumDriver,"密码错误第4次")

        and:"以hexautotest6/12345 登录 第5次"
        Pause.stop(5)
        loginOperations.login_only(rcm_server,userName,password)
        Pause.stop(0.6)
        showPicInReportPortrait(appiumDriver,"密码错误第5次")

        and:"以hexautotest6/123456 正常登录 第1次"
        Pause.stop(5)
        loginOperations.login_only(rcm_server,userName,password)
        Pause.stop(0.6)
        showPicInReportPortrait(appiumDriver,"账号被锁5分钟")

        LoginPage loginPage = new LoginPage(appiumDriver)
        boolean isOnLoginPage = loginPage.isOnLoginPage()

        and:"等待5分钟"
        Pause.stop(300)

        then:"6次失败"
        assert isOnLoginPage
    }

//    @Retry
    def "用户密码错误登录5次被锁后5分钟正常登录"(){
        when:"初始化AppiumDriver"
        androidEndpoint.initialAppiumEndpointfromJson(configFileName,androidKeyword_1)
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        

        and:"以hexautotest6/123456登录"
        LoginOperations  loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(rcm_server,userName,password)

        Pause.stop(4)
        showPicInReportPortrait(appiumDriver,"登录界面")

        ConferenceTabPage conferenceTabPage = new ConferenceTabPage(appiumDriver)
        boolean isOnConferenceTabPage = conferenceTabPage.isOnConferenceTabPage()

        then:"成功登录"
        assert isOnConferenceTabPage
    }

}
