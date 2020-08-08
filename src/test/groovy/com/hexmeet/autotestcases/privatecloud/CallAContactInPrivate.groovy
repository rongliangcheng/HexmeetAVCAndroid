package com.hexmeet.autotestcases.privatecloud

import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.Utility.Pause
import com.hexmeet.Utility.UIElement
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.pageobject.common.MeetingOperations
import com.hexmeet.pageobject.common.ReserveMeetingPage
import com.hexmeet.pageobject.common.CallAContactInStructure
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.userprivatemainPage.UserPrivateMainPage
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit


@Title("从通讯录中呼叫")
@Narrative("从通信录中找到用户并呼叫")

class CallAContactInPrivate extends EndpointSystemTestSpec{

    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    @Shared
    ReserveMeetingPage reserveMeetingPage

    @Shared
    String serverAddress="cloudbeta.hexmeet.com"

    @Shared
    String accout="hjtautotest1"

    @Shared
    String password="123456"

    def setupSpec(){

        LOGGER.info("Setup")

    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }

    def "call a contact in Favorite List"(){

    }

    @Retry(delay = 30000)
    def "呼叫组织架构中的用户"(){
        when:"初始化并登录"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        UserPrivateMainPage userPrivateMainPage = new UserPrivateMainPage(appiumDriver)
        userPrivateMainPage.navigate(serverAddress,accout,password)
        userPrivateMainPage.contacts()

        and:"在公司的组织架构中找到用户"
        CallAContactInStructure callAContactInStructure = new CallAContactInStructure(appiumDriver)
        callAContactInStructure.findAContactInStructure("hjtautotest2")
        Pause.stop(3)
        showPicInReportPortrait(appiumDriver,"找到用户");

        and:"呼叫用户"
        callAContactInStructure.callTheContact()
        Pause.stop(5)
        showPicInReport(appiumDriver,"呼叫用户");
        Pause.stop(30)

        then:"呼叫成功"
        assert  new MeetingOperations(appiumDriver).isInMeetingPage()
    }


}
