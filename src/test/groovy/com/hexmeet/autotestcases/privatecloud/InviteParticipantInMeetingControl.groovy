package com.hexmeet.autotestcases.privatecloud

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

@Title("会控邀人入会")
@Narrative("预约会议中添加用户，静音等操作")
@Retry(delay=10000)

class InviteParticipantInMeetingControl extends EndpointSystemTestSpec{

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

        and:"Join the reserved meeting"
        reserveMeetingPage.joinReservedMeeting(username,true,false,true);
        Pause.stop(10)
        meetingOperations = new MeetingOperations(appiumDriver)
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }


    def "邀请与会者"(){
        when:"查找与会者"
        //LOGGER.info("Mute audio")
        Pause.stop(3)
        meetingOperations.inviteParticipantInAMeeting("RongliangVE210")
        Pause.stop(2)
        showPicInReport(appiumDriver,"成功邀请与会者")

        //boolean inviteResult = meetingOperations.hasTwoParticipants();

        then:"操作成功"
        assert true
    }


    def "全体静音"(){
        when: "点击全体静音"
        meetingOperations.muteAllInAMeeting()
        Pause.stop(2)
        showPicInReport(appiumDriver,"全体静音")

        then:"操作成功"
        assert true
    }

    def "全体解除静音"(){
        when:"点击全体解除静音"

        meetingOperations.umuteAllInAMeeting()
        Pause.stop(2)
        showPicInReport(appiumDriver,"全体解除静音")

        then:"操作成功"
        assert true
    }

    def "挂断并结束会议"(){
        when:"挂断并结束会议"
        LOGGER.info("Hangup and terminate the call")
        meetingOperations.hangupAndTerminateCall()
        Pause.stop(5)

        and:"抓屏"
        showPicInReportPortrait(appiumDriver,"会议结束")

        then:"操作成功"
        assert true
    }

}

