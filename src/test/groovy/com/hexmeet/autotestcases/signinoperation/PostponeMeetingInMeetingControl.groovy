package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.pageobject.common.MeetingOperations
import com.hexmeet.pageobject.common.ReserveMeetingPage
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.SignInPage
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("会控中推迟会议")
@Narrative("预约会议中推迟会议半小时")
@Retry(delay=10000)

class PostponeMeetingInMeetingControl extends EndpointSystemTestSpec{

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

    @Shared
    MeetingOperations meetingOperations



    def setupSpec(){
        log.info("Startup")

    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }


    def "推迟会议并核对推迟时间"(){
        when:"清除已经预约会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr,username,password)
        reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.clearReservedMeetings(username)

        and:"创建预约会议"
        reserveMeetingPage.navigate()
        reserveMeetingPage.now();
        reserveMeetingPage.finish();
        showPicInReportPortrait(appiumDriver,"会议1小时预约成功")
        reserveMeetingPage.backAfterReserver()

        and:"入会"
        reserveMeetingPage.joinReservedMeeting(username,true,false,true);
        Pause.stop(10)
        meetingOperations = new MeetingOperations(appiumDriver)

        and:"推迟半个小时"
        //LOGGER.info("Mute audio")
        Pause.stop(3)
        meetingOperations.postponeTheMeeting()
        Pause.stop(2)
        meetingOperations.hangupAndLeave()

        and:"核对推迟时间"
        Pause.stop(1)

        boolean postponed = reserveMeetingPage.containStringInDuration(username,"30")
        showPicInReportPortrait(appiumDriver,"会议增加了30分钟")

        reserveMeetingPage.deleteReservedMeeting(username)

        then:"操作成功"
        assert postponed
    }

}

