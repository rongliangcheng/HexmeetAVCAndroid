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
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

@Title("加入预约会议")
@Narrative("创建预约会议并加入，邀请别的用户")
//@Retry(delay=20000)

class ParameterSettingChangeBandwidth extends EndpointSystemTestSpec {
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

    @Shared boolean flag=true

    def setupSpec(){
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login_and_accept_access_permission(serverAddr,username,password)

        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }


    def "预约会议"(){

        when:"预约会议"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_reserve_meeting_page()
        ReserveMeetingOperations reserveMeetingOperations = new ReserveMeetingOperations(appiumDriver)
        reserveMeetingOperations.reserve_meeting("","This is a reserved test meeting",false,2)

        then:"操作成功"
        assert  true

    }

    @Unroll
    def "改变带宽 #bandwidth kbps"(){

        when:"设置带宽"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_setting_from_conference_tab()
        ParameterSettingOperations parameterSettingOperations = new ParameterSettingOperations(appiumDriver)
        parameterSettingOperations.change_bandwidth(bandwidth)
        parameterSettingOperations.return_to_me_tab_page()


        and:"加入会议"
        pageNavigation.to_conference_tab_page()
        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.reserved_meeting_info()

        ReserveMeetingDetailOperations reserveMeetingDetailOperations = new ReserveMeetingDetailOperations(appiumDriver)
        if(flag){
            ReserveMeetingEditOperations reserveMeetingEditOperations = new ReserveMeetingEditOperations(appiumDriver)
            reserveMeetingEditOperations.start_meeting_now_and_confirm(CallType.VideoCall)
            flag=false
        } else{
            reserveMeetingDetailOperations.start_meeting(CallType.VideoCall)
        }

        Pause.stop(4)
        showPicInReport(appiumDriver,bandwidth)


        and:"检查媒体信息"
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.show_media_statistics()
        boolean match = meetingOperations.video_tx_resolution().contains(resolution)

        Pause.stop(10)
        meetingOperations.exit_media_statistics()


        and:"挂断会议"
        Pause.stop(15)
        meetingOperations.hangup_call()
        if(reserveMeetingDetailOperations.isOnDetailPage())
            reserveMeetingDetailOperations.back_to_conference_tab_page()

        then:"操作成功"
        assert  match

        where:
        bandwidth   || resolution
        "128"       || "180p"
        "256"       || "360p"
        "384"       || "360p"
        "512"       || "360p"
        "768"       || "720p"
        "1024"      || "720p"
        "1536"      || "720p"
        "2048"      || "720p"

    }

    def "清除预约会议"(){
        when:"删除会议"
        Pause.stop(2)
        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        then:
        assert true
    }


}
