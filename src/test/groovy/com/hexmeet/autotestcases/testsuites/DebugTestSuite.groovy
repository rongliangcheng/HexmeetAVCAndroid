package com.hexmeet.autotestcases.testsuites

import com.hexmeet.autotestcases.signinoperation.DialPadOperate
import com.hexmeet.autotestcases.signinoperation.JoinMyMeeting
import org.junit.runner.RunWith
import org.junit.runners.Suite
import com.hexmeet.autotestcases.signinoperation.AndroidOp
import com.hexmeet.autotestcases.signinoperation.CallAContact
import com.hexmeet.autotestcases.signinoperation.AppVersion
import com.hexmeet.autotestcases.signinoperation.SignIn
import com.hexmeet.autotestcases.signinoperation.ReserveMeetingOperate
import com.hexmeet.autotestcases.signinoperation.PersonalInfoOperate
import com.hexmeet.autotestcases.signinoperation.ParameterSettingOperate
import com.hexmeet.autotestcases.signinoperation.ParameterSettingChangeBandwidth


@RunWith(Suite.class)
@Suite.SuiteClasses([
//        AndroidOp,
//        AppVersion,
//        SignIn,
//        CallAContact,
//        ReserveMeetingOperate,
        JoinMyMeeting,
//        DialPadOperate,
//        PersonalInfoOperate,
//        ParameterSettingOperate,
//        ParameterSettingChangeBandwidth
])

class DebugTestSuite {
}

