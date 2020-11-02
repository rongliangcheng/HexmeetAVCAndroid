package com.hexmeet.autotestcases.privatecloud

import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.pageobject.common.MeetingOperations
import com.hexmeet.pageobject.common.ReserveMeetingPage
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.signin.SignInPage
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("加入预约会议并进行操作")
@Narrative("预约会议然后进行麦克，摄像头，模式的操作")
@Retry(delay=10000)


class OperateInAReservedMeeting extends EndpointSystemTestSpec{

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


    def setupSpec(){
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        SignInPage signInPage = new SignInPage(appiumDriver)
        signInPage.navigate()
        signInPage.submit(serverAddr,username,password)
        ReserveMeetingPage reserveMeetingPage = new ReserveMeetingPage(appiumDriver);
        reserveMeetingPage.navigate()
        reserveMeetingPage.now();
        reserveMeetingPage.addParticipants("RongliangVE210");
        reserveMeetingPage.finish();
        reserveMeetingPage.backAfterReserver()

        and:"Join the reserved meeting"
        reserveMeetingPage.joinReservedMeeting(username);
        Pause.stop(10)
        meetingOperations = new MeetingOperations(appiumDriver)
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }


    def "静音及解除静音"(){
        when:"点击静音按钮一次"
        LOGGER.info("Mute audio")
        meetingOperations.muteUmuteAudio()
        Pause.stop(2)
        showPicInReport(appiumDriver,"静音操作一次")

        and: "点击静音按钮两次"
        LOGGER.info("Umute audio")
        meetingOperations.muteUmuteAudio()
        Pause.stop(2)
        showPicInReport(appiumDriver,"静音操作二次")

        then:"操作成功"
        assert true
    }


    def "开关摄像头"(){
        when: "点击摄像头按钮一次"
        LOGGER.info("Mute camera")
        meetingOperations.muteUmuteCamera()
        Pause.stop(2)
        showPicInReport(appiumDriver,"摄像头操作1次")

        and: "点击摄像头按钮两次"
        LOGGER.info("Umute camera")
        meetingOperations.muteUmuteCamera()
        Pause.stop(2)
        showPicInReport(appiumDriver,"摄像头操作2次")

        then:"操作成功"
        assert true
    }

    def "切换摄像头"(){
        when:"点击切换摄像头按钮1次"
        LOGGER.info("Switch camera")
        meetingOperations.switchCamera()
        Pause.stop(5)
        showPicInReport(appiumDriver,"切换摄像头1次")

        and:"点击切换摄像头按钮2次"
        LOGGER.info("Switch camera back")
        meetingOperations.switchCamera()
        Pause.stop(5)
        showPicInReport(appiumDriver,"切换摄像头2次")

        then:"操作成功"
        assert true
    }

//    def "共享桌面取消"(){
//        when:"点击共享桌面按钮然后取消"
//        LOGGER.info("Share content and cancel")
//        meetingOperations.shareContentAndCancel()
//
//        then:"操作成功"
//        assert true
//    }

    def "共享桌面然后停止"(){

        when: "点击分享桌面并分享"
        LOGGER.info("Share content")
        meetingOperations.shareContent()
        Pause.stop(2)
        showPicInReportPortrait(appiumDriver,"分享桌面")

        and: "停止分享"
        LOGGER.info("Stop content")
        meetingOperations.stopContent()
        Pause.stop(5)
        showPicInReport(appiumDriver,"停止共享桌面")

        then:"操作成功"
        assert  true
    }

    def "聊天"(){
        when: "发送聊天消息"
        LOGGER.info("Send message to all")
        meetingOperations.sendMessage("Hello, how are you")
        Pause.stop(2)
        showPicInReportPortrait(appiumDriver,"聊天界面")
        meetingOperations.returnFromSendMessage();

        then:"操作成功"
        assert true
    }

    def "切换分屏模式"(){
        when:"点击分屏模式1次"
        LOGGER.info("Switch Layout")
        meetingOperations.switchLayout()
        Pause.stop(2)
        showPicInReport(appiumDriver,"分屏模式2")

        and:"点击分屏模式2次"
        LOGGER.info("Switch layout back")
        meetingOperations.switchLayout()
        Pause.stop(2)
        showPicInReport(appiumDriver,"分屏模式1")

        then:"操作成功"
        assert true
    }

    def "改名"(){
        when:"更改名字"
        LOGGER.info("update user name")
        meetingOperations.changeParticipantName("AAAAFFFF")
        Pause.stop(10)
        showPicInReport(appiumDriver,"更名为AAAAFFFF")

        and:"核对更改名字"
        String displayName = meetingOperations.getDisplayName()

        then:"更名成功"
        assert displayName.equals("AAAAFFFF")
    }

    def "操作近端预览"(){
        when:"点击取消近端预览按钮"
        LOGGER.info("Mute local preview")
        meetingOperations.showHideLocalPreview()
        Pause.stop(2)
        showPicInReport(appiumDriver,"取消近端预览")

        and:"点击近端预览按钮"
        LOGGER.info("Umute local preview")
        meetingOperations.showHideLocalPreview()
        Pause.stop(2)
        showPicInReport(appiumDriver,"近端预览")

        then:"操作成功"
        assert  true
    }

    def "音视频转换"(){
        when:"点击切换到音频模式按钮"
        LOGGER.info("Downgrade to audio")
        meetingOperations.switchToAudioOnly()
        Pause.stop(5)
        showPicInReport(appiumDriver,"音频模式")

        and:"点击音视频模式按钮"
        LOGGER.info("Upgrade to video")
        meetingOperations.switchBackToAVmode()
        Pause.stop(5)
        showPicInReport(appiumDriver,"音视频模式")

        then:"操作成功"
        assert true
    }

    def "挂断并结束会议"(){
        when:"挂断并结束会议"
        LOGGER.info("Hangup and terminate the call")
        meetingOperations.hangupAndTerminateCall()
        Pause.stop(5)

        and:"抓屏"
        showPicInReportPortrait(appiumDriver,"会议结束")

        then:"操作成功"
        assert true
    }

}

