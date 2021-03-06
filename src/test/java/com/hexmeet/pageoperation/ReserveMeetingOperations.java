package com.hexmeet.pageoperation;

import com.hexmeet.Utility.Pause;
import com.hexmeet.pageobject.ReserveMeetingPage;
import io.appium.java_client.AppiumDriver;

public class ReserveMeetingOperations extends ReserveMeetingPage {
    public ReserveMeetingOperations(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void reserve_meeting(String password, String remark, boolean now){
        set_meeting_password(password);
        set_meeting_remark(remark);
        if( !now ){
            choose_time();
        }
        reserve_confirm();
        back_after_reserve_confirm();
    }

    public void reserve_meeting(String password, String remark, boolean now, int participant_numbers){
        set_meeting_password(password);
        set_meeting_remark(remark);
        if( !now ){
            choose_time();
        }
        if( 1 == participant_numbers ){
            delete_participant();
            add_other_participant();
        }else if( 2 == participant_numbers){
            delete_participant();
            add_participants();
        }
        reserve_confirm();
        Pause.stop(2);
        back_after_reserve_confirm();
    }


}
