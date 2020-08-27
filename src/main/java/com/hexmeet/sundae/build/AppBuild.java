package com.hexmeet.sundae.build;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class AppBuild {

    private final String jenkinsBuildUrl="http://172.27.0.12:8080/view/HJT%20SVC%20EVSDK%20APP/job/swep-evsdk-android-phone-1.4/lastSuccessfulBuild/artifact/";
    private final String outputPath="D:\\Dev\\workspace\\Jenkins\\workspace\\HJT_SVC_Android\\build\\";
    private final String password="123456";
    private final String username="chengrl";
    private final int cache = 10 * 1024;
    private String webResponse;
    private String buildName;
    private String buildVersion;


//    public static void main(String[] args) {
//        AppBuild appBuild = new AppBuild();
//        //System.out.println(appBuild.webResponse);
//        System.out.println(appBuild.getBuildName());
//        System.out.println(appBuild.getVersion());
//        System.out.println(appBuild.getBuildFilePath());
//        appBuild.downloadBuildFile();
//
//    }

    public AppBuild(){
        webResponse = response();

        Pattern pattern = Pattern.compile("\"HexMeetHJT-([\\d|.]+)-release.apk\"");
        Matcher matcher = pattern.matcher(webResponse);

        if(matcher.find()){
            buildName=matcher.group(0).split("\"")[1];
            buildVersion=matcher.group(1);
        }
    }


    private String response(){
        try{
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(jenkinsBuildUrl);
            httpGet.addHeader("Authorization","Basic "+ Base64.getUrlEncoder().encodeToString((username+":"+password).getBytes()));

            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String webresponse = EntityUtils.toString(httpEntity,"UTF-8");
            client.close();
            return webresponse;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public String getWebResponse(){
        return webResponse;
    }

    public String getBuildName(){
        return buildName;
    }

    public String getVersion(){

        return buildVersion;
    }

    public String getBuildFilePath(){
        return jenkinsBuildUrl+buildName;
    }

    public void downloadBuildFile(){

        try{
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(getBuildFilePath());
            httpGet.addHeader("Authorization","Basic "+ Base64.getUrlEncoder().encodeToString((username+":"+password).getBytes()));

            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            InputStream is = httpEntity.getContent();

            File outputBuildFile=new File(outputPath+getBuildName());
            FileOutputStream fileOutputStream = new FileOutputStream(outputBuildFile);

            byte[] buffer = new byte[cache];

            int ch=0;
            while((ch = is.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,ch);
            }
            is.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            client.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getOutputBuildPath(){
        return outputPath+buildName;
    }
}
