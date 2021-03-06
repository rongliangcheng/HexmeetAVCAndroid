package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.CallType
import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.pageobject.PageNavigation
import com.hexmeet.pageoperation.ConferenceTabOperations
import com.hexmeet.pageoperation.LoginOperations
import com.hexmeet.pageoperation.MeetingOperations
import com.hexmeet.pageoperation.MyMeetingRoomOperations
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("长呼叫")
@Narrative("长时间呼叫")

class LongCall extends EndpointSystemTestSpec{

    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    @Shared
    String serverAddr="hexmeet"

    @Shared
    String username="hjtautotest6"

    @Shared
    String password="123456"


    @Shared
    int loopCounter=600

    @Shared
    def nums;

    @Shared
    File psFile

    @Shared
    def outputPath="D:\\Dev\\workspace\\Jenkins\\workspace\\HJT_SVC_Android\\build\\psfile_long.txt"

    def setupSpec(){

    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup() {

    }

    def "长时间呼叫"(){

        when:"呼叫"
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

        and:"收集cpu,memory信息"
        Map<String,Object> args = new HashMap<>();
        args.put("command"," ps -eO PCPU ")
        args.put("args"," |grep com.cninnovatel.ev\$")

        int count=0;
        while( count < loopCounter ){
            String psResult = (String)appiumDriver.executeScript("mobile:shell",args);
            log.info(psResult)
            psFile.append("\n"+psResult)
            Pause.stop(60)
            count++;
        }

        and:"挂断终结"
        MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)
        meetingOperations.hangup_call()

        and:"删除会议"
        Pause.stop(2)
        //conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        then:"longall结束"
        assert true

    }

}

