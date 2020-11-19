package ru.sbrf.mbeans;

import javax.management.NotificationBroadcasterSupport;

public class Cl extends NotificationBroadcasterSupport implements ClMBean {
    String mName = "default";

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getTimeInMillis() {
        return System.currentTimeMillis();
    }

    public void writeToConsole(String message) {
        System.out.println(message);
    }
}
