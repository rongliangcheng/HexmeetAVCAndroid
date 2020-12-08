package com.hexmeet.autotestcases.privatecloud

import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.Utility.Pause
import com.hexmeet.Utility.UIElement
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.pageobject.common.MeetingOperations
import com.hexmeet.pageobject.common.ReserveMeetingPage
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.SignInPage
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit
@Title("加入预约会议")
@Narrative("创建预约会议并加入，邀请别的用户")
@Retry(delay=20000)

class JoinAReserveMeeting extends EndpointSystemTestSpec {
    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    @Shared
    ReserveMeetingPage reserveMeetingPage

    @Shared
    String serverAddr="cloudbeta.hexmeet.com"

    @Shared
    String username="hjtautotest1"

    @Shared
    String password="123456"

    def setupSpec(){

 //       LOGGER.info("Setup")
//        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
//        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
//        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
//        SignInPage signInPage = new SignInPage(appiumDriver)
//        signInPage.navigate()
//        signInPage.submit(serverAddr,username,password)
//        reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
//        reserveMeetingPage.navigate()

    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }

    def "创建即时会议并加入"(){

        when:"创建一即时会议并改变时长"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr,username,password)
        ReserveMeetingPage reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.navigate()
        reserveMeetingPage.now();
        reserveMeetingPage.changeDuration()
        reserveMeetingPage.finish();
        reserveMeetingPage.backAfterReserver()

        and:"加入预约会议"
        reserveMeetingPage.joinReservedMeeting("hjtautotest1",true,false,true);
        Pause.stop(30)
        showPicInReport(appiumDriver,"与会中")

        and:"挂断并结束会议"
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.hangupAndTerminateCall()

        then:"操作成功"
        assert  true

    }


    def "创建即时会议并邀请其他与会者"(){
        when:"创建一即时会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr,username,password)
        ReserveMeetingPage reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.navigate()
        Pause.stop(5);
        reserveMeetingPage.now();
        reserveMeetingPage.addParticipants("RongliangVE210");
        reserveMeetingPage.finish();
        reserveMeetingPage.backAfterReserver()

        and:"加入会议"
        reserveMeetingPage.joinReservedMeeting("hjtautotest1",true,false,true);
        Pause.stop(30)
        showPicInReport(appiumDriver,"与会中")

        and:"挂断并结束会议"
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.hangupAndTerminateCall()

        then:"操作成功"
        assert  true
    }

//暂时没有解决 setpassword的问题
//    def "Create a password protected meeting and join"(){
//        when:"Create a reserved meeting"
//        reserveMeetingPage.now();
//        reserveMeetingPage.setMeetingPassword("1234567890")
//        reserveMeetingPage.finish();
//        reserveMeetingPage.backAfterReserver()
//
//        and:"Join the reserved meeting"
//        reserveMeetingPage.joinReservedMeeting()
//        Pause.stop(30)
//        showPicInReport(appiumDriver)
//
//        then:
//        assert true
//    }



}
