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

@Title("拨号盘呼叫")
@Narrative("创建预约会议并加入，邀请别的用户")
//@Retry(delay=20000)

class DialPadOperate extends EndpointSystemTestSpec {
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

    def "以视频拨带密码的会议并执行静音/关闭摄像头/切换摄像头"(){

        when:"清除已经存在的会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)

        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()


        and:"预约会议"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_reserve_meeting_page()
        ReserveMeetingOperations reserveMeetingOperations = new ReserveMeetingOperations(appiumDriver)
        String meeting_password="123456"
        reserveMeetingOperations.reserve_meeting(meeting_password,"This is a reserved test meeting",true,1)

        ReserveMeetingDetailOperations reserveMeetingDetailOperations = new ReserveMeetingDetailOperations(appiumDriver)
        String conference_num = reserveMeetingDetailOperations.get_conference_num()
        String dial_str=conference_num+"*"+meeting_password

        and:"拨号盘加入会议"
        pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_dialing_tab_page()

        DialPadOperations dialPadOperations = new DialPadOperations(appiumDriver)
        dialPadOperations.start_meeting(dial_str,CallType.VideoCall)
        Pause.stop(10)
        showPicInReport(appiumDriver,"以视频拨带密码的会议")

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


    def "以音频拨带密码的会议并执行静音/切换扬声器"(){

        when:"清除已经存在的会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)

        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()


        and:"预约会议"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_reserve_meeting_page()
        ReserveMeetingOperations reserveMeetingOperations = new ReserveMeetingOperations(appiumDriver)
        String meeting_password="123456"
        reserveMeetingOperations.reserve_meeting(meeting_password,"This is a reserved test meeting",true,1)

        ReserveMeetingDetailOperations reserveMeetingDetailOperations = new ReserveMeetingDetailOperations(appiumDriver)
        String conference_num = reserveMeetingDetailOperations.get_conference_num()
        String dial_str=conference_num+"*"+meeting_password

        and:"拨号盘加入会议"
        pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_dialing_tab_page()

        DialPadOperations dialPadOperations = new DialPadOperations(appiumDriver)
        dialPadOperations.start_meeting(dial_str,CallType.AudioCall)
        Pause.stop(10)
        showPicInReportPortrait(appiumDriver,"以音频拨带密码的会议")

        and:"静音/解除静音"
        AudioMeetingOperations audioMeetingOperations = new AudioMeetingOperations(appiumDriver)
        audioMeetingOperations.mute_umute_mic()

        and:"免提/听筒"
        audioMeetingOperations.speaker_earphone_mode()

        and:"挂断会议"
        Pause.stop(15)
        audioMeetingOperations.hangup_audio_call()

        and:"删除会议"
        Pause.stop(2)
        //conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        then:"操作成功"
        assert  true

    }

    def "以视频拨不带密码的会议并执行静音/关闭摄像头/切换摄像头"(){

        when:"清除已经存在的会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)

        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()


        and:"预约会议"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_reserve_meeting_page()
        ReserveMeetingOperations reserveMeetingOperations = new ReserveMeetingOperations(appiumDriver)
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",true,1)

        ReserveMeetingDetailOperations reserveMeetingDetailOperations = new ReserveMeetingDetailOperations(appiumDriver)
        String conference_num = reserveMeetingDetailOperations.get_conference_num()

        and:"拨号盘加入会议"
        pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_dialing_tab_page()

        DialPadOperations dialPadOperations = new DialPadOperations(appiumDriver)
        dialPadOperations.start_meeting(conference_num,CallType.VideoCall)
        Pause.stop(10)
        showPicInReport(appiumDriver,"以视频拨不带密码的会议")

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


    def "以音频拨不带密码的会议并执行静音/切换扬声器"(){

        when:"清除已经存在的会议"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)

        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()


        and:"预约会议"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_reserve_meeting_page()
        ReserveMeetingOperations reserveMeetingOperations = new ReserveMeetingOperations(appiumDriver)
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",true,1)

        ReserveMeetingDetailOperations reserveMeetingDetailOperations = new ReserveMeetingDetailOperations(appiumDriver)
        String conference_num = reserveMeetingDetailOperations.get_conference_num()

        and:"拨号盘加入会议"
        pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_dialing_tab_page()

        DialPadOperations dialPadOperations = new DialPadOperations(appiumDriver)
        dialPadOperations.start_meeting(conference_num,CallType.AudioCall)
        Pause.stop(10)
        showPicInReportPortrait(appiumDriver,"以音频拨不带密码的会议")

        and:"静音/解除静音"
        AudioMeetingOperations audioMeetingOperations = new AudioMeetingOperations(appiumDriver)
        audioMeetingOperations.mute_umute_mic()

        and:"免提/听筒"
        audioMeetingOperations.speaker_earphone_mode()

        and:"挂断会议"
        Pause.stop(15)
        audioMeetingOperations.hangup_audio_call()

        and:"删除会议"
        Pause.stop(2)
        //conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        then:"操作成功"
        assert  true

    }
//
//    def "不带密码呼密码的会议"(){
//
//        when:"清除已经存在的会议"
//        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
//        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
//        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
//
//        LoginOperations loginOperations = new LoginOperations(appiumDriver)
//        loginOperations.login_and_accept_access_permission(serverAddr,username,password)
//
//        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
//        conferenceTabOperations.clear_reserved_meetings()
//
//
//        and:"预约会议"
//        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
//        pageNavigation.to_reserve_meeting_page()
//        ReserveMeetingOperations reserveMeetingOperations = new ReserveMeetingOperations(appiumDriver)
//        reserveMeetingOperations.reserve_meeting("123456","This is a reserved test meeting",true,1)
//
//        ReserveMeetingDetailOperations reserveMeetingDetailOperations = new ReserveMeetingDetailOperations(appiumDriver)
//        String conference_num = reserveMeetingDetailOperations.get_conference_num()
//
//
//        and:"拨号盘加入会议"
//        pageNavigation = new PageNavigation(appiumDriver)
//        pageNavigation.to_dialing_tab_page()
//
//        DialPadOperations dialPadOperations = new DialPadOperations(appiumDriver)
//        dialPadOperations.start_meeting(conference_num,CallType.VideoCall)
//
//        and:"仍然在拨号盘界面"
//        boolean isOnDialPadPage = dialPadOperations.isOnDialPadPage()
//
//        then:"操作成功"
//        assert  isOnDialPadPage
//
//    }

}
