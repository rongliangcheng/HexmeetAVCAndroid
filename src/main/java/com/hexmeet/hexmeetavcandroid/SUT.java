package com.hexmeet.hexmeetavcandroid;

public interface SUT {
    String getIP();
    String getVersion();

    void setKeyWords(String[] keyWords);
    String[] getKeyWords();

    String collectLogsTo(String targetIP,String targetFolder,String username,String password);

    String toHtml();
}
