package com.hexmeet.hexmeetavcandroid;

import com.hexmeet.hexmeetavcandroid.mediaStatistics.CALLTYPE;
import com.hexmeet.hexmeetavcandroid.mediaStatistics.MediaStatistics;

public interface EndPoint<T extends EndPoint<T>> extends SUT {

    String getDialString(CALLTYPE callType);

    T placeCall(EndPoint<?> callee, CALLTYPE callType, int callRate);
    T placeCall(String dialString, CALLTYPE callType, int callRate);

    MediaStatistics getMediaStatistics();

}
