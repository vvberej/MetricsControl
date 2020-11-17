package ru.sbrf.posttime.mbeans;

public class Cl implements ClMBean {

    public String getName() {
        return "ClMBeanImpl";
    }

    public long getTimeInMillis() {
        return System.currentTimeMillis();
    }

    public void writeToConsole(String message) {
        System.out.println(message);
    }
}
