package com.hexmeet.autotestcases.testsuites

import com.hexmeet.autotestcases.signinoperation.LongCall
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([
        LongCall
])
class LongCallTestSuite {
}

