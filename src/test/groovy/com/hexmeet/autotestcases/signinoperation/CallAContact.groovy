package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.CallType
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.pageobject.AudioMeetingPage
import com.hexmeet.pageobject.PageNavigation
import com.hexmeet.pageoperation.AudioMeetingOperations
import com.hexmeet.pageoperation.ContactOperations
import com.hexmeet.pageoperation.LoginOperations
import com.hexmeet.pageoperation.MeetingOperations
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit


@Title("从通讯录中呼叫")
@Narrative("从通信录中找到用户并呼叫")

class CallAContact extends EndpointSystemTestSpec{

    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    @Shared
    String serverAddress="hexmeet"

    @Shared
    String accout="hexautotest6"

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

//    @Retry(delay = 30000)
    def "视频呼叫组织架构中的用户"(){
        given:"初始化"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        when:"登录"
        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddress,accout,password)
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        Pause.stop(5)
        pageNavigation.to_contact_tab_page()

        and:"视频呼叫用户"
        ContactOperations contactOperations = new ContactOperations(appiumDriver)
        contactOperations.call_contact(CallType.VideoCall)
        Pause.stop(20)

        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        boolean inMeeting = meetingOperations.isInMeeting()

        and:"静音及解除静音"
        meetingOperations.mute_umute_audio()

        and:"停止视频及打开视频"
        meetingOperations.mute_umute_video()

        and:"切换摄像头"
        meetingOperations.switch_camera()

        and:"挂断"
        meetingOperations.hangup_call()

        then:"呼叫成功"
        assert  inMeeting
    }


    def "音频呼叫组织架构中的用户"(){
        given:"初始化"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        when:"登录"
        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddress,accout,password)
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        Pause.stop(5)
        pageNavigation.to_contact_tab_page()

        and:"音频呼叫用户"
        ContactOperations contactOperations = new ContactOperations(appiumDriver)
        contactOperations.call_contact(CallType.AudioCall)
        Pause.stop(20)

        AudioMeetingOperations audioMeetingOperations = new AudioMeetingOperations(appiumDriver)
        boolean inAudioMeeting = audioMeetingOperations.isInAudioMeeting()

        and:"静音与解除静音切换"
        audioMeetingOperations.mute_umute_mic()

        and:"听筒与免提切换"
        audioMeetingOperations.speaker_earphone_mode()

        and:"挂断"
        audioMeetingOperations.hangup_audio_call()


        then:"呼叫成功"
        assert  inAudioMeeting

    }

}
