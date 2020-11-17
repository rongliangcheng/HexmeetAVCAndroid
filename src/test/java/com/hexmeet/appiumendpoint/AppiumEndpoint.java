package com.hexmeet.appiumendpoint;

import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class AppiumEndpoint {
    private AppiumDriver appiumDriver;
    private DesiredCapabilities capabilities = new DesiredCapabilities();

    Logger log = LoggerFactory.getLogger(this.getClass());

    //Initial Appium Endpoint manually

    public void initialAppiumEndpointfromJson(String fileName, String keyWord) {
        StringBuilder stringBuilder = new StringBuilder("Initial Appium Endpoint ...");
        log.info(stringBuilder.toString());
        setAppiumCapabilities(fileName,keyWord);
        try {
            appiumDriver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void initialAppiumEndpoint(String fileName,String keyWord,String port) {
        StringBuilder stringBuilder = new StringBuilder("Initial Appium Endpoint ");
        log.info(stringBuilder.toString());
        setAppiumCapabilities(fileName,keyWord);
        try {
            appiumDriver = new AppiumDriver(new URL("http://0.0.0.0:"+port+"/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    };

    public String getDeviceSN(String fileName, String keyword){
        JSONObject jsonObject = getJSONObjectFromFile(fileName);
        return jsonObject.getJSONObject(keyword).getString("deviceName");
    }

    //Initial Appium Endpoint from config.json file
    private JSONObject getJSONObjectFromFile(String fileName) {
        log.info("Initial Appium endpoint from config.json");
        JSONObject jsonObject=null;
        File file = new File(AppiumEndpoint.class.getClassLoader().getResource(fileName).getPath());
        try {
            String content = FileUtils.readFileToString(file, "UTF-8");
            log.info(content);
            jsonObject = JSONObject.parseObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void setAppiumCapabilities(String fileName,String keyword){
        JSONObject jsonObject = getJSONObjectFromFile(fileName);
        Set<String> keySet = jsonObject.getJSONObject(keyword).keySet();
        for(String key: keySet){
            log.info("set capacity of "+key);
            capabilities.setCapability(key,jsonObject.getJSONObject(keyword).getString(key));
        }
    }





    public AppiumDriver getAppiumEndpointDriver(){
        return appiumDriver;
    };

}
