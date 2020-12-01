package ru.sbrf.gettime;

import ru.sbrf.mbeans.ClMBean;

import java.io.IOException;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Client {

    public static void main(String[] args) {
        try {
            String ipAdr="localhost";
            String port = "8082";

            if(args.length > 1) {  //если через консоль были введены аргументы
                ipAdr = args[0];
                port = args[1];
            }

            // Создание подключения к RMI серверу
            JMXServiceURL url = new JMXServiceURL(
                      "service:jmx:rmi:///jndi/rmi://" + ipAdr + ":" + port + "/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

            // Получение MBeanServerConnection
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

            //домены доступные для подключения
            String domains[] = mbsc.getDomains();
            for (int i = 0; i < domains.length; i++) {
                echo("\tDomain[" + i + "] = " + domains[i]);
            }
            //подключение к нужному mbean
            ObjectName mBeanName = new ObjectName("ru.sbrf.mbeans:type=Cl");
            ClMBean proxy = JMX.newMBeanProxy(mbsc, mBeanName, ClMBean.class, true);
            String nnm = proxy.getName();
            echo("\tConnected to Bean with Name" + proxy.getName());

            ClientListener listener = new ClientListener();
            mbsc.addNotificationListener(mBeanName, listener, null, null);

            //запрос метрики у удаленного mbean
            long remoteTime = proxy.getTimeInMillis();
            long deltaTime = System.currentTimeMillis() - remoteTime;
            echo("\tdelta time = " + deltaTime);

            waitForEnterPressed();
            // Закрытие соединение с RMI сервером
            jmxc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void echo(String msg) {
        System.out.println(msg);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void waitForEnterPressed() {
        try {
            echo("\nPress <Enter> to continue...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
