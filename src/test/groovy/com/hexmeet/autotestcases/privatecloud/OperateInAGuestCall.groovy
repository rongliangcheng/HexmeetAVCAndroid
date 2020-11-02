package com.hexmeet.autotestcases.privatecloud

import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.pageobject.common.MeetingOperations
import com.hexmeet.pageobject.startup.deploytype.privatedeploy.joinmeeting.PrivateDirectJoinAMeetingPage
import io.appium.java_client.AppiumDriver
import spock.lang.Narrative
import spock.lang.Retry
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("非注册用户入会后进行操作")
@Narrative("非注册用户入会进行麦克，摄像头，模式等的操作")
@Retry(delay=10000)

class OperateInAGuestCall extends EndpointSystemTestSpec{

    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    String serverAddress="cloudbeta.hexmeet.com"

    @Shared
    String conferenceNumber="13910001001*12345"

    @Shared
    String username="hjtautotest1"

    @Shared
    MeetingOperations meetingOperations

    @Shared
    PrivateDirectJoinAMeetingPage privateDirectJoinAMeetingPage

    def setupSpec(){

        LOGGER.info("Setup")
        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_1")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()

        privateDirectJoinAMeetingPage  = new PrivateDirectJoinAMeetingPage(appiumDriver)
        privateDirectJoinAMeetingPage.navigate()

        privateDirectJoinAMeetingPage.joinAMeeting(serverAddress,conferenceNumber,username)
        Pause.stop(10)
        showPicInReport(appiumDriver,username)

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

        and: "点击摄像头两次"
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
        Pause.stop(2)
        showPicInReport(appiumDriver,"切换摄像头1次")

        and:"点击切换摄像头按钮2次"
        LOGGER.info("Switch camera back")
        meetingOperations.switchCamera()
        Pause.stop(2)
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

 //   def "Share content and stop content"(){

    def "共享桌面然后停止"(){
        when: "点击共享桌面并分享"
        LOGGER.info("Share content")
        meetingOperations.shareContent()
        Pause.stop(2)
        showPicInReportPortrait(appiumDriver,"共享桌面")
        Pause.stop(20)

        and: "停止分享"
        LOGGER.info("Stop content")
        meetingOperations.stopContent()
        Pause.stop(2)
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
        meetingOperations.returnFromSendMessage()

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
        meetingOperations.persistentCallHangupAndLeave()
        Pause.stop(5)

        and:"抓屏"
        showPicInReportPortrait(appiumDriver,"会议结束")

        then:"操作成功"
        assert privateDirectJoinAMeetingPage.isOnGuestPage()
    }

}
