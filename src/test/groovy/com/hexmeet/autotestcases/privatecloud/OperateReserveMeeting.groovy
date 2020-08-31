package com.hexmeet.autotestcases.privatecloud

import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.pageobject.common.MeetingOperations
import com.hexmeet.pageobject.common.ReserveMeetingPage
import com.hexmeet.pageobject.common.meetingpage.MeetingMainPage
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.SignInPage
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("操作预约会议")
@Narrative("创建预约会议，修改，删除")
@Retry(delay=20000)

class OperateReserveMeeting extends EndpointSystemTestSpec {
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

        log.info("Setup")
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }

    def "创建即时会议并删除"(){

        when:"创建一即时会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr,username,password)
        ReserveMeetingPage reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.navigate()
        reserveMeetingPage.now();
        reserveMeetingPage.finish();
        reserveMeetingPage.backAfterReserver()
        showPicInReportPortrait(appiumDriver,"预约即时会议成功")
        Pause.stop(2)

        and:"找到并删除即时会议"
        reserveMeetingPage.deleteReservedMeeting("hjtautotest1");
        Pause.stop(5)
        showPicInReportPortrait(appiumDriver,"预约会议删除")

        MeetingMainPage meetingMainPage = new MeetingMainPage(appiumDriver)

        then:"操作成功"
        assert  meetingMainPage.isOnMeetingPage()

    }


    def "创建预约会议并删除"(){
        when:"创建一预约会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr,username,password)
        ReserveMeetingPage reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.navigate()
        Pause.stop(5);
        //reserveMeetingPage.now();
        reserveMeetingPage.addParticipants("RongliangVE210");
        reserveMeetingPage.finish();
        reserveMeetingPage.backAfterReserver()
        showPicInReportPortrait(appiumDriver,"预约会议成功")
        Pause.stop(2)

        and:"找到并删除预约会议"
        reserveMeetingPage.deleteReservedMeeting("hjtautotest1");
        Pause.stop(5)
        showPicInReportPortrait(appiumDriver,"预约会议删除")

        MeetingMainPage meetingMainPage = new MeetingMainPage(appiumDriver)


        then:"操作成功"
        assert  meetingMainPage.isOnMeetingPage()
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
