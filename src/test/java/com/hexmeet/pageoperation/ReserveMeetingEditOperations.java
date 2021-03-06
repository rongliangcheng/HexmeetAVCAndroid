package com.hexmeet.pageoperation;

import com.hexmeet.pageobject.ReserveMeetingEditPage;
import io.appium.java_client.AppiumDriver;

public class ReserveMeetingEditOperations extends ReserveMeetingEditPage {
    public ReserveMeetingEditOperations(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void edit_remark_and_confirm_ops(String remark_str){
        edit_reserved_meeting();
        edit_remark("This is a updated remark");
        edit_reserved_meeting_confirm();
    }

    public void delete_add_participant_ops(){
        edit_reserved_meeting();
        delete_participant_and_add_new();
        edit_reserved_meeting_confirm();
    }

}
