package com.hexmeet.autotestcases.signinoperation

import com.hexmeet.Utility.Pause
import com.hexmeet.Utility.UICommon
import com.hexmeet.appiumendpoint.AppiumEndpoint
import com.hexmeet.autotestcases.TestSpec.EndpointSystemTestSpec
import com.hexmeet.pageobject.AboutPage
import com.hexmeet.pageobject.PageNavigation
import com.hexmeet.pageoperation.LoginOperations
import com.hexmeet.pageoperation.PrivacyPolicyOperations
import com.hexmeet.hexmeetavcandroid.build.AppBuild
import io.appium.java_client.AppiumDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Title

import java.util.concurrent.TimeUnit

@Title("版本升级")
@Narrative("获取App版本")

class AppVersion extends EndpointSystemTestSpec{

    @Shared
    AppiumDriver appiumDriver;

    @Shared
    AppiumEndpoint androidEndpoint = new AppiumEndpoint();

    @Shared
    Logger log = LoggerFactory.getLogger(this.getClass())

    @Shared
    String server_addr="172.25.0.213"

    @Shared
    String username="hexautotest6"

    @Shared
    String password="123456"

    @Shared
    String version

    @Shared
    boolean versionMatch = true

    def setupSpec(){

        LOGGER.info("Setup")

        androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
        androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
        appiumDriver = androidEndpoint.getAppiumEndpointDriver()
        sleep(9)
        PrivacyPolicyOperations privacyPolicyOperation = new PrivacyPolicyOperations(appiumDriver)
        privacyPolicyOperation.ok()

        UICommon.devicePermissionAllowance(appiumDriver)

        LoginOperations loginOperations = new LoginOperations(appiumDriver)
        loginOperations.login(server_addr,username,password)

        UICommon.devicePermissionAllowanceAfterLogin(appiumDriver)
    }

    def cleanupSpec(){

    }

    def setup(){

    }

    def cleanup(){

    }


    def "版本升级"(){
        when:"当前App版本信息"
        PageNavigation pageNavigation = new PageNavigation(appiumDriver)
        pageNavigation.to_me_tab_page()
        pageNavigation.to_about_page()

        AboutPage aboutPage = new AboutPage(appiumDriver)
        version=aboutPage.getVersion()

        log.info(version)
        showPicInReportPortrait(appiumDriver,"版本信息")


        AppBuild appBuild = new AppBuild();
        String buildVersion=appBuild.getVersion();
        reportInfo("Jenkins Build版本信息："+appBuild.buildName)

        and:"Jenkins build版本信息${buildVersion}"

        if(version != buildVersion){
            appBuild.downloadBuildFile();
            appiumDriver.installApp(appBuild.getOutputBuildPath());

            Pause.stop(30)

            androidEndpoint.initialAppiumEndpointfromJson("config.json","Android_3")
            androidEndpoint.getAppiumEndpointDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)
            appiumDriver = androidEndpoint.getAppiumEndpointDriver()
            sleep(3)
            PrivacyPolicyOperations privacyPolicyOperation = new PrivacyPolicyOperations(appiumDriver)
            privacyPolicyOperation.ok()
            UICommon.devicePermissionAllowance(appiumDriver)

            LoginOperations loginOperations = new LoginOperations(appiumDriver)
            loginOperations.login(server_addr,username,password)

            UICommon.devicePermissionAllowanceAfterLogin(appiumDriver)

            pageNavigation = new PageNavigation(appiumDriver)
            pageNavigation.to_me_tab_page()
            pageNavigation.to_about_page()

            aboutPage = new AboutPage(appiumDriver)
            String newVersion=aboutPage.getVersion()

            showPicInReportPortrait(appiumDriver,"新版本信息")
            versionMatch = newVersion == buildVersion
            version=newVersion;
        }

        def filePath="D:\\Dev\\workspace\\Jenkins\\workspace\\Hexmeet_AVC_Android\\build\\version.txt"
        File file = new File(filePath)
        file.text=version

        then:"版本升级成功"
        assert versionMatch

    }

}
