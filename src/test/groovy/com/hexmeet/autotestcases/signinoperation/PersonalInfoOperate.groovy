package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.CallType
import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.pageobject.PageNavigation
import com.hexmeet.pageoperation.*
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("更改个人信息")
@Narrative("更改个人信息")
//@Retry(delay=20000)

class PersonalInfoOperate extends EndpointSystemTestSpec {
    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    @Shared
    String serverAddr="hexmeet"

    @Shared
    String username="hexautotest6"

    @Shared
    String password="123456"

    def setupSpec(){

    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }

    def "更换头像"(){

        when:"清除已经存在的会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)


        and:"更换头像"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_me_tab_page()
        pageNavigation.to_personal_information_page()

        PersonalInfoOperations personalInfoOperations = new PersonalInfoOperations(appiumDriver)
        personalInfoOperations.changeImage()

        Pause.stop(4)
        showPicInReportPortrait(appiumDriver,"更换头像")

        then:"操作成功"
        assert  true

    }


    def "更改名字"(){

        when:"清除已经存在的会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)


        and:"更改名字为Hex123"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_me_tab_page()
        pageNavigation.to_personal_information_page()

        PersonalInfoOperations personalInfoOperations = new PersonalInfoOperations(appiumDriver)
        String str1=personalInfoOperations.change_display_name("Hex123")

        Pause.stop(2)
        String str2=personalInfoOperations.change_display_name("!@#\$%^&*()_{}<>?123dwer")
        Pause.stop(2)
        showPicInReportPortrait(appiumDriver,"更改名字")

        Pause.stop(2)
        String str3=personalInfoOperations.change_display_name("hexautotest6")

        then:"操作成功"
        assert  str1.equals("Hex123")
        assert  str2.equals("!@#\$%^&*()_{}<>?123dwer")
        assert  str3.equals("hexautotest6")

    }


}
