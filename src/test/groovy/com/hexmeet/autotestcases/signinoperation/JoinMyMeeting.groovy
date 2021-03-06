package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.CallType
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.pageobject.PageNavigation
import com.hexmeet.pageoperation.AudioMeetingOperations
import com.hexmeet.pageoperation.ConferenceTabOperations
import com.hexmeet.pageoperation.LoginOperations
import com.hexmeet.pageoperation.MeetingOperations
import com.hexmeet.pageoperation.MyMeetingRoomOperations
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("加入我的会议")
@Narrative("呼叫我的会议室，静音解除静音，启用关闭视频，切换摄像头")
@Retry(delay=10000)

class JoinMyMeeting extends EndpointSystemTestSpec{
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

        LOGGER.info("Setup")
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }

    def "视频加入我的会议"(){


        when: "初始化"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)

        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        and :"进入我的会议室然后视频入会"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_my_meeting_room_page()

        MyMeetingRoomOperations myMeetingRoomOperations = new MyMeetingRoomOperations(appiumDriver)
        myMeetingRoomOperations.start_call(CallType.VideoCall)
        Pause.stop(4)
        showPicInReport(appiumDriver,"视频加入我的会议")

        and:"静音/解除静音"
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.mute_umute_audio()

        and:"停止/启用视频"
        meetingOperations.mute_umute_video()

        and:"切换摄像头"
        meetingOperations.switch_camera()

        and:"挂断会议"
        Pause.stop(15)
        meetingOperations.hangup_call()

        and:"删除会议"
        Pause.stop(2)
        //conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        then:"操作成功"
        assert  true

    }


    def "音频加入我的会议"(){


        when: "初始化"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)

        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        and :"进入我的会议室然后音频入会"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_my_meeting_room_page()

        MyMeetingRoomOperations myMeetingRoomOperations = new MyMeetingRoomOperations(appiumDriver)
        myMeetingRoomOperations.start_call(CallType.AudioCall)
        Pause.stop(4)
        showPicInReport(appiumDriver,"音频加入我的会议")

        and:"静音/解除静音"
        AudioMeetingOperations audioMeetingOperations = new AudioMeetingOperations(appiumDriver)
        audioMeetingOperations.mute_umute_mic()

        and:"免提/听筒"
        audioMeetingOperations.speaker_earphone_mode()

        and:"挂断会议"
        Pause.stop(15)
        audioMeetingOperations.hangup_audio_call()

        then:"操作成功"
        assert  true

    }
}
