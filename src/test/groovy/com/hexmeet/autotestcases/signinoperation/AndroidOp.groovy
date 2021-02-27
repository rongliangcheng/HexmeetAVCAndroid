package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.Pause
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

@Title("重启手机")
@Narrative("重启手机释放资源")

class AndroidOp extends EndpointSystemTestSpec{

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    def setupSpec(){

    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }

    def "重启测试设备"(){
        def sn = androidEndpoint.getDeviceSN("config.json","Android_3");

        when:"重启测试设备"
        "adb reboot -s $sn ".execute()

        Pause.stop(60)
        String upStatus = "adb -s $sn shell uptime ".execute().text
        log.info("upStatus")

        then:"测试设备重启完成"
        upStatus.contains("0 min")
    }
}
