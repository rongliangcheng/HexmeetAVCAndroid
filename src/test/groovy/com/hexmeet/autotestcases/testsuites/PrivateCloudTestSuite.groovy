package com.hexmeet.autotestcases.testsuites

import com.hexmeet.autotestcases.signinoperation.AndroidOp
import com.hexmeet.autotestcases.signinoperation.AppVersion
import com.hexmeet.autotestcases.signinoperation.CallAContact
import com.hexmeet.autotestcases.signinoperation.JoinAReserveMeeting
import com.hexmeet.autotestcases.signinoperation.JoinMyMeetingInPrivate
import com.hexmeet.autotestcases.signinoperation.LockMeetingInMeetingControl
import com.hexmeet.autotestcases.signinoperation.OperateInAReservedMeeting
import com.hexmeet.autotestcases.signinoperation.OperateReserveMeeting
import com.hexmeet.autotestcases.signinoperation.PostponeMeetingInMeetingControl
import com.hexmeet.autotestcases.signinoperation.SignIn

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
        AndroidOp,
        AppVersion,
        SignIn,
        JoinMyMeetingInPrivate,
        CallAContact,
        JoinAReserveMeeting,
        OperateReserveMeeting,
        PostponeMeetingInMeetingControl,
        LockMeetingInMeetingControl,
        OperateInAReservedMeeting
])

class signinoperationTestSuite {
}
