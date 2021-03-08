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
import com.hexmeet.pageoperation.ReserveMeetingDetailOperations
import com.hexmeet.pageoperation.ReserveMeetingEditOperations
import com.hexmeet.pageoperation.ReserveMeetingOperations
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit
@Title("预约会议操作")
@Narrative("创建预约会议并加入，邀请别的用户")
//@Retry(delay=20000)

class ReserveMeetingOperate extends EndpointSystemTestSpec {
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

    def "创建预约会议"(){

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
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",false)

        and:"删除会议"
        conferenceTabOperations.clear_reserved_meetings()

        then:"操作成功"
        assert  true

    }

    def "创建预约会议并视频入会并执行静音/关闭摄像头/切换摄像头"(){

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
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",false)

        and:"加入会议"
        conferenceTabOperations.reserved_meeting_info()
        ReserveMeetingEditOperations reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
        reserveMeetingEditOperations.start_meeting_now_and_confirm(CallType.VideoCall)
        Pause.stop(10)
        showPicInReport(appiumDriver,"创建预约会议并视频入会")

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



    def "创建预约会议,视频入会,挂断后再次音视频入会并执行静音/切换扬声器"(){

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
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",false)

        and:"加入会议"
        conferenceTabOperations.reserved_meeting_info()
        ReserveMeetingEditOperations reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
        reserveMeetingEditOperations.start_meeting_now_and_confirm(CallType.VideoCall)


        and:"挂断会议"
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.hangup_call()

        and:"再次视频入会"
        conferenceTabOperations.reserved_meeting_info()
        ReserveMeetingDetailOperations reserveMeetingDetailOperations = new ReserveMeetingDetailOperations(appiumDriver)
        reserveMeetingDetailOperations.start_meeting(CallType.VideoCall)
        Pause.stop(15)
        showPicInReport(appiumDriver,"挂断会议后视频入会")

        and:"挂断会议"
        meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.hangup_call()

        and:"再次音频入会"
        reserveMeetingDetailOperations = new ReserveMeetingDetailOperations(appiumDriver)
        reserveMeetingDetailOperations.start_meeting(CallType.AudioCall)
        Pause.stop(15)
        showPicInReportPortrait(appiumDriver,"挂断会议后音频入会")

        and:"挂断会议"
        AudioMeetingOperations audioMeetingOperations = new AudioMeetingOperations(appiumDriver)
        audioMeetingOperations.hangup_audio_call()

        and:"删除会议"
        Pause.stop(2)
        //conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        reserveMeetingDetailOperations.end_meeting()

        then:"操作成功"
        assert  true

    }

    def "创建预约会议并音频入会并执行静音/切换扬声器"(){

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
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",false)

        and:"加入会议"
        conferenceTabOperations.reserved_meeting_info()
        ReserveMeetingEditOperations reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
        reserveMeetingEditOperations.start_meeting_now_and_confirm(CallType.AudioCall)
        Pause.stop(10)
        showPicInReportPortrait(appiumDriver,"创建预约会议并音频入会")

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


    def "创建预约会议并修改会议信息后入会并执行静音/关闭摄像头/切换摄像头"(){

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
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",false)

        and:"修改会议备注信息"
        conferenceTabOperations.reserved_meeting_info()
        ReserveMeetingEditOperations reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
        reserveMeetingEditOperations.edit_remark_and_confirm_ops("This is an updated remark")

        and:"删除并添加与会人"
        conferenceTabOperations.reserved_meeting_info()
        reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
        reserveMeetingEditOperations.delete_add_participant_ops()

        and:"加入会议"
        conferenceTabOperations.reserved_meeting_info()
        reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
        reserveMeetingEditOperations.start_meeting_now_and_confirm(CallType.VideoCall)
        Pause.stop(4)
        showPicInReport(appiumDriver,"修改预约会议并视频入会")

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

}
