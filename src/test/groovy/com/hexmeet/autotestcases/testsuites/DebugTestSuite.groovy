package com.hexmeet.autotestcases.testsuites


import org.junit.runner.RunWith
import org.junit.runners.Suite
import com.hexmeet.autotestcases.signinoperation.CallAContact

@RunWith(Suite.class)
@Suite.SuiteClasses([
//        AndroidOp,
//        AppVersion,
//        SignIn,
        CallAContact,
//        JoinAReserveMeeting,
//        OperateReserveMeeting,
//        PostponeMeetingInMeetingControl,
//        LockMeetingInMeetingControl,
//        OperateInAReservedMeeting,
//        JoinMyMeetingInPrivate
])
class DebugTestSuite {
}

