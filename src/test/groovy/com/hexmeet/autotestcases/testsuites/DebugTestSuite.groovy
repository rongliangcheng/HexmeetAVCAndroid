package com.hexmeet.autotestcases.testsuites

import com.hexmeet.autotestcases.privatecloud.CallAContactInPrivate
import com.hexmeet.autotestcases.privatecloud.GuestCall
import com.hexmeet.autotestcases.privatecloud.LongCall
import com.hexmeet.autotestcases.privatecloud.OperateInAGuestCall
import com.hexmeet.autotestcases.privatecloud.OperateInAReservedMeeting
import com.hexmeet.autotestcases.privatecloud.AppVersion
import com.hexmeet.autotestcases.privatecloud.OperateReserveMeeting
import com.hexmeet.autotestcases.privatecloud.PostponeMeetingInMeetingControl
import com.hexmeet.autotestcases.privatecloud.ShortCalls
import org.junit.runner.RunWith
import org.junit.runners.Suite



@RunWith(Suite.class)
@Suite.SuiteClasses([
//          SignIn
        AppVersion,

//            OperateInAGuestCall
//         GuestCall
        OperateReserveMeeting
//        InviteParticipantInMeetingControl
//        PostponeMeetingInMeetingControl
//        LockMeetingInMeetingControl
//LongCall
//        CallAContactInPrivate
//        JoinAReserveMeeting,
 //       OperateInAReservedMeeting
//        ShortCalls
])
class DebugTestSuite {
}

