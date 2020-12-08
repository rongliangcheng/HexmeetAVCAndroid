package com.hexmeet.autotestcases.privatecloud

import com.hexmeet.Utility.Pause
import com.hexmeet.Utility.UIElement
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.pageobject.common.MeetingOperations
import com.hexmeet.pageobject.common.ReserveMeetingPage
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.SignInPage
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title
import spock.lang.Unroll

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
    ReserveMeetingPage reserveMeetingPage

    @Shared
    String serverAddr="cloudbeta.hexmeet.com"

    @Shared
    String username="hjtautotest1"

    @Shared
    String password="123456"

    @Shared
    MeetingOperations meetingOperations

    @Shared
    int loopCounter=600

    @Shared
    def nums;

    @Shared
    File psFile

    @Shared
    def outputPath="D:\\Dev\\workspace\\Jenkins\\workspace\\HJT_SVC_Android\\build\\psfile_long.txt"

    def setupSpec(){
        log.info("Startup")

        psFile = new File(outputPath)
        psFile.write("")

        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr,username,password)
        reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.navigate()
        reserveMeetingPage.now();
        reserveMeetingPage.changeDuration();
        reserveMeetingPage.addParticipants("RongliangVE210")
        reserveMeetingPage.finish();
        reserveMeetingPage.backAfterReserver()
        meetingOperations = new MeetingOperations(appiumDriver)
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup() {

    }

    def "长时间呼叫"(){
        when:"呼叫"
        Pause.stop(2)
        reserveMeetingPage.joinReservedMeeting(username,true,false,true);
        Pause.stop(10)

        and:"收集cpu,memory信息"
        Map<String,Object> args = new HashMap<>();
        args.put("command"," ps -eO PCPU ")
        args.put("args"," |grep hexmeet.hjt\$")

        int count=0;
        while( count < loopCounter ){
            String psResult = (String)appiumDriver.executeScript("mobile:shell",args);
            log.info(psResult)
            psFile.append("\n"+psResult)
            Pause.stop(60)
            count++;
        }

        and:"挂断终结"
        meetingOperations.hangupAndTerminateCall()

        then:"longall结束"
        assert true

    }

}

