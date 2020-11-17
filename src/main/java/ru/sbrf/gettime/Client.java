package ru.sbrf.gettime;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.management.Attribute;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import ru.sbrf.mbeans.*;

public class Client {

    public static void main(String[] args) {
        try {
            // Create an RMI connector client and
            // connect it to the RMI connector server

            JMXServiceURL url = new JMXServiceURL(
                      "service:jmx:rmi:///jndi/rmi://localhost:8082/server");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

            // Create listener
            //
            ClientListener listener = new ClientListener();

            // Get an MBeanServerConnection
            //
            echo("\nGet an MBeanServerConnection");
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            waitForEnterPressed();

            // Get domains from MBeanServer
            //
            echo("\nDomains:");
            String domains[] = mbsc.getDomains();
            for (int i = 0; i < domains.length; i++) {
                echo("\tDomain[" + i + "] = " + domains[i]);
            }

            // Get MBeanServer's default domain
            //
            String domain = mbsc.getDefaultDomain();

            // Create MBean
            //
            ObjectName mBeanName =
                new ObjectName(domain +":type=Cl");
            echo("\nCreate MBean...");
            mbsc.createMBean("Cl", mBeanName, null, null);

            Cl proxy = JMX.newMBeanProxy(
                    mbsc, mBeanName, Cl.class, true);
            echo("\nState = " + proxy.getTimeInMillis());

            // Close MBeanServer connection
            waitForEnterPressed();
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
