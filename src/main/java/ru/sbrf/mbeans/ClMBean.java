package ru.sbrf.mbeans;

public interface ClMBean {
    String getName(); // Name for implementation
    void setName(String name); // Name for implementation
    long getTimeInMillis(); // Get current time in millis
    void writeToConsole(String message);//test function
}
