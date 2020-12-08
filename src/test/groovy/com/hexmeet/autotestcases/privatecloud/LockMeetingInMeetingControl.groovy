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

@Title("会控中锁定解除会议")
@Narrative("预约会议中锁定会议，登录，解除锁定，登录")
@Retry(delay=10000)

class LockMeetingInMeetingControl extends EndpointSystemTestSpec{

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


    def "清除已预约会议"() {
        when: "清除hjtautotest1已预约会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json", "Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr, username, password)
        reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.clearReservedMeetings(username)
        showPicInReportPortrait(appiumDriver, "会议清除干净")

        then:"没有hjtautotest1预约会议"
        assert true
    }

     def "创建预约会议"() {
         when: "创建预约会议"
         reserveMeetingPage.navigate()
         reserveMeetingPage.now();
         reserveMeetingPage.finish();
         reserveMeetingPage.backAfterReserver()
         showPicInReportPortrait(appiumDriver, "创建新的会议")

         and: "入会"
         reserveMeetingPage.joinReservedMeeting(username,true,false,true);
         Pause.stop(10)
         meetingOperations = new MeetingOperations(appiumDriver)
         showPicInReport(appiumDriver, "入会")

         then:"入会成功"
         assert true
     }

    def "锁定会议，呼入禁止"() {
        when: "锁定会议"
        //LOGGER.info("Mute audio")
        Pause.stop(3)
        meetingOperations.lockTheMeeting()
        Pause.stop(2)
        meetingOperations.hangupAndLeave()

        and: "尝试进入锁定会议"
        Pause.stop(4)
        reserveMeetingPage.joinReservedMeeting(username,true,false,true);
        Pause.stop(4)
        showPicInReportPortrait(appiumDriver, "未能入会")

        then:"锁定会议成功"
        assert true
    }

    def "解锁会议，入会，挂断"(){
        when:"解锁会议"
        Pause.stop(4)
        reserveMeetingPage.unlockMeeting(username)

        and:"再次入会"
        Pause.stop(4)
        reserveMeetingPage.joinReservedMeeting(username,true,false,true);
        Pause.stop(10)
        showPicInReport(appiumDriver,"入会")

        and:"挂断结束会议"
        Pause.stop(20)
        meetingOperations.hangupAndTerminateCall()
        Pause.stop(10)
        showPicInReportPortrait(appiumDriver,"挂断")

        then:"操作成功"
        assert true
    }

}

