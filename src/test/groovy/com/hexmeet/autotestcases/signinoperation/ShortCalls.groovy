package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.CallType
import com.hexmeet.Utility.Pause
import com.hexmeet.Utility.UIElement
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.pageoperation.ConferenceTabOperations
import com.hexmeet.pageoperation.LoginOperations
import com.hexmeet.pageoperation.MeetingOperations
import com.hexmeet.pageoperation.MyMeetingRoomOperations
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

@Title("短呼叫")
@Narrative("短呼叫500次")

class ShortCalls extends EndpointSystemTestSpec{

    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    @Shared
    MyMeetingRoomOperations myMeetingRoomOperations = new MyMeetingRoomOperations(appiumDriver)

    @Shared
    MeetingOperations meetingOperations = new MeetingOperations(appiumDriver)

    @Shared
    String serverAddr="hexmeet"

    @Shared
    String username="hjtautotest1"

    @Shared
    String password="123456"


    @Shared
    int arraySize=500

    @Shared
    def nums;

    @Shared
    File psFile

    @Shared
    def outputPath="D:\\Dev\\workspace\\Jenkins\\workspace\\HJT_SVC_Android\\build\\psfile.txt"

    def setupSpec(){
        log.info("Startup")

        nums = new int[arraySize]
        def count=0;
        arraySize.times{
            nums[count] = count;
            count++;
        }

        psFile = new File(outputPath)
        psFile.write("")

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

    def cleanup() {

        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver)
        conferenceTabOperations.clear_reserved_meetings()

        Map<String,Object> args = new HashMap<>();
        args.put("command","ps -e")
        args.put("args","|grep hexmeet.hjt\$")
        String psResult = (String)appiumDriver.executeScript("mobile:shell",args);

        log.info(psResult)

        psFile.append("\n"+psResult)

    }

    @Unroll
    def "短呼叫#counter"(){
        when:"呼叫"
        Pause.stop(2)
        myMeetingRoomOperations.start_call(CallType.VideoCall)
        Pause.stop(10)

        then:"呼叫成功"
        meetingOperations.hangup_call()

        where:"counter"
        counter << nums
    }

}

