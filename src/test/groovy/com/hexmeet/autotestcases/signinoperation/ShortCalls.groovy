package com.hexmeet.autotestcases.signinoperation

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
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr,username,password)
        reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.navigate()
        reserveMeetingPage.now();
        reserveMeetingPage.changeDuration();
        reserveMeetingPage.finish();
        reserveMeetingPage.backAfterReserver()

        meetingOperations = new MeetingOperations(appiumDriver)
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup() {

        if (UIElement.byElementIsExist(appiumDriver, By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]"))) {
            appiumDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View").click();
        }

        if (meetingOperations.isInMeetingPage()) {
            meetingOperations.hangupAndLeave()
        }

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
        reserveMeetingPage.joinReservedMeeting(username,true,false,true);
        Pause.stop(10)

        then:"呼叫成功"
        if( counter == arraySize-1 ){
            meetingOperations.hangupAndTerminateCall()
        } else {
            meetingOperations.hangupAndLeave()
        }

        where:"counter"
        counter << nums
    }

}

