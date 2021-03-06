package com.hexmeet.pageoperation;

import com.hexmeet.Utility.Pause;
import com.hexmeet.pageobject.PageNavigation;
import com.hexmeet.pageobject.ReserveMeetingDetailPage;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class ReserveMeetingDetailOperations extends ReserveMeetingDetailPage {
    private Logger log = getLogger(this.getClass().getName());
    private AppiumDriver appiumDriver;
    public ReserveMeetingDetailOperations(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    public String get_conference_num(){
        log.info("get_conference_num");
        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver);
        conferenceTabOperations.reserved_meeting_info();
        String num = get_conference_number_on_page();
        Pause.stop(1);
        back_to_conference_tab_page();
        return num;
    }

    public String get_password(){
        log.info("get_password");
        ConferenceTabOperations conferenceTabOperations = new ConferenceTabOperations(appiumDriver);
        conferenceTabOperations.reserved_meeting_info();
        String password = get_conference_password_on_page();
        log.info("*****"+password+"*****");
        Pause.stop(1);
        back_to_conference_tab_page();
        return password;
    }
}
