package com.hexmeet.autotestcases.install

import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.base.JsonConfigSingleton
import com.hexmeet.pageobject.startup.UIStart
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Shared

import java.util.concurrent.TimeUnit

class InstallApp extends EndpointSystemTestSpec{
    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint()

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())


    def setupSpec(){

    }

    def cleanupSpec(){
//        if(androidEndpoint.getAppiumEndpointDriver() != null){
//                androidEndpoint.getAppiumEndpointDriver().quit();
//        }
    }

    def setup(){

    }

    def cleanup(){

    }

    def "Install Android apk"(){
        Process exec;

        when:
        String path = JsonConfigSingleton.getJsonObjectFromJsonConfig()
                .getJSONObject("Android_3_Install").getString("path");
        String deviceId = JsonConfigSingleton.getJsonObjectFromJsonConfig()
                .getJSONObject("Android_3_Install").getString("deviceId");

        String execCommand = "adb -s "+deviceId+" install -r "+path;

        log.info(execCommand);

        try {
            Runtime.getRuntime().exec("adb shell mount -o remount /system");
            exec = Runtime.getRuntime().exec(execCommand);

            log.info(exec.toString());

            sleep(60000)
        }catch (Exception e) {
            e.printStackTrace();
         }


        then:
        assert true
    }

    def "Check Version"(){

        when:
        androidEndpoint.initialAppiumEndpoint("Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        String version = JsonConfigSingleton.getJsonObjectFromJsonConfig().
                getJSONObject("Android_3_Install").getString("version");

        AppiumDriver driver = androidEndpoint.getAppiumEndpointDriver()


        UIStart uiStart = new UIStart(driver);
        uiStart.startUp()

        DeploymentTypePage.privateCloudDeployment(driver)
        PrivateCloudPage.setting(driver)
        PrivateCloudSettingPage.about(driver)
        String newVersion = PrivateCloudAboutPage.getVersion(driver)

        then:
            assert newVersion.equals(version);
    }
}
