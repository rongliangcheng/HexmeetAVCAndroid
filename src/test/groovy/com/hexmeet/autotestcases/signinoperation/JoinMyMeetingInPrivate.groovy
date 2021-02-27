package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.pageobject.common.MeetingOperations
import com.hexmeet.pageobject.common.ReserveMeetingPage
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.userprivatemainPage.mymeeting.MyMeetingPage
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("加入私有部署我的会议")
@Narrative("呼叫私有部署中我的会议室，修改麦克，摄像头设置，并修改会议密码")
@Retry(delay=10000)

class JoinMyMeetingInPrivate extends EndpointSystemTestSpec{
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

        LOGGER.info("Setup")
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }

    def "修改会议密码并加入会议"(){

        when: "初始化"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        and :"修改我的会议密码，麦克摄像头设置"
        String newPassword="12345"
        MyMeetingPage myMeetingPage = new MyMeetingPage(appiumDriver);
        myMeetingPage.navigate(serverAddr,username,password);
        myMeetingPage.mymeetingpage()
        myMeetingPage.muteUmuteCamera()
        myMeetingPage.muteUmuteAudio()
        myMeetingPage.muteUmuteCamera()
        myMeetingPage.muteUmuteAudio()
        myMeetingPage.meetingSettings(newPassword,false,false,false,false,true)
        myMeetingPage.joinTheMeeting(newPassword)

        Pause.stop(10)
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        showPicInReport(appiumDriver,"私有部署中我的会议")
        Boolean isInAmeeting = meetingOperations.isInMeetingPage();

        and:"挂断并结束会议"
        Pause.stop(20)
        meetingOperations.hangupAndTerminateCall()
        Pause.stop(5)
        showPicInReportPortrait(appiumDriver,"挂断")

        then:"操作成功"
        assert isInAmeeting

    }
}
