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
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("改变设置")
@Narrative("创建预约会议并加入，邀请别的用户")
@Retry(delay=20000)

class ParameterSettingOperate extends EndpointSystemTestSpec {
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

    def "检查网络状态/检查发送诊断信息页面/检查发送建议页面"(){

        when:"登录"
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)


        and:"检查网络"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_setting_from_conference_tab()

        ParameterSettingOperations parameterSettingOperations = new ParameterSettingOperations(appiumDriver)
        boolean status = parameterSettingOperations.check_network()

        and:"检查诊断信息页面"
        parameterSettingOperations.send_diagnostic_logs()

        and:"检查发送建议页面"
        parameterSettingOperations.send_suggestions()

        then:"操作成功"
        assert  status

    }

    def "设置发送1080P"(){

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
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",false,2)

        and:"设置1080P"
        pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_setting_from_conference_tab()
        ParameterSettingOperations parameterSettingOperations = new ParameterSettingOperations(appiumDriver)
        parameterSettingOperations.turn_on_1080P(true)
        parameterSettingOperations.change_bandwidth("2048")
        Pause.stop(1)
        parameterSettingOperations.return_to_me_tab_page()


        and:"加入会议"
        pageNavigation.to_conference_tab_page()
        conferenceTabOperations.reserved_meeting_info()
        ReserveMeetingEditOperations reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
        reserveMeetingEditOperations.start_meeting_now_and_confirm(CallType.VideoCall)

        and:"检查媒体信息"
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.show_media_statistics()
        Pause.stop(10)
        meetingOperations.exit_media_statistics()


        and:"挂断会议"
        Pause.stop(15)
        meetingOperations.hangup_call()

        and:"删除会议"
        Pause.stop(2)
        //conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        and:"恢复720P"
        pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_setting_from_conference_tab()
        parameterSettingOperations = new ParameterSettingOperations(appiumDriver)
        parameterSettingOperations.turn_on_1080P(false)

        then:"操作成功"
        assert  true

    }

    def "设置发送高帧率"(){

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
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",false,2)

        and:"设置高帧率"
        pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_setting_from_conference_tab()
        ParameterSettingOperations parameterSettingOperations = new ParameterSettingOperations(appiumDriver)
        parameterSettingOperations.turn_on_high_rate_video(true)
        parameterSettingOperations.change_bandwidth("2048")
        parameterSettingOperations.return_to_me_tab_page()


        and:"加入会议"
        pageNavigation.to_conference_tab_page()
        conferenceTabOperations.reserved_meeting_info()
        ReserveMeetingEditOperations reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
        reserveMeetingEditOperations.start_meeting_now_and_confirm(CallType.VideoCall)

        and:"检查媒体信息"
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.show_media_statistics()
        Pause.stop(10)
        meetingOperations.exit_media_statistics()


        and:"挂断会议"
        Pause.stop(15)
        meetingOperations.hangup_call()

        and:"删除会议"
        Pause.stop(2)
        //conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        and:"恢复帧率"
        pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_setting_from_conference_tab()
        parameterSettingOperations = new ParameterSettingOperations(appiumDriver)
        parameterSettingOperations.turn_on_high_rate_video(false)

        then:"操作成功"
        assert  true

    }

}
