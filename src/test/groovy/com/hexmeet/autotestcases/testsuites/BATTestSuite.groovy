package com.hexmeet.autotestcases.testsuites

import com.hexmeet.autotestcases.signinoperation.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
        AndroidOp,
        AppVersion,
        SignIn,
        CallAContact,
        ReserveMeetingOperate,
        JoinMyMeeting,
        DialPadOperate,
        PersonalInfoOperate,
        ParameterSettingOperate,
        ParameterSettingChangeBandwidth
])

class BATTestSuite {
}

