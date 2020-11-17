package ru.sbrf.posttime;

import javax.management.*;
import java.lang.management.*;
import java.rmi.registry.LocateRegistry;
import javax.management.remote.*;
import ru.sbrf.mbeans.*;

public class SimpleAgent
{
    private MBeanServer mbs = null;

    public SimpleAgent()
    {
        // Получить экземпляр MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();
        //HtmlAdaptorServer adapter = new HtmlAdaptorServer();

        // Создаем наш MBean
        ObjectName beanname = null;
        Cl mbean = new Cl();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        try {
            // И регистрируем его на платформе MBeanServer
            beanname = new ObjectName("ru.sbrf.mbeans:type=Cl");
            mbs.registerMBean(mbean, beanname);

            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8082/jmxrmi");// new JMXServiceURL("service:jmx:rmi://");
            JMXConnectorServer cs =
                    JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
            LocateRegistry.createRegistry(8082);
            cs.start();
            JMXServiceURL addr = cs.getAddress();
            JMXConnector cc = JMXConnectorFactory.connect(addr);

            System.out.println(addr.getURLPath());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Это вспомогательный метод - он позволяет программе
    // остановиться и ждать
    private static void waitForEnterPressed()
    {
        try {
            System.out.println("Press  to continue...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[])
    {
        SimpleAgent agent = new SimpleAgent();
        System.out.println("SimpleAgent is running...");
        SimpleAgent.waitForEnterPressed();
        //Thread.sleep(Long.MAX_VALUE);
        //while(true) {}
    }
}




/*
        MetricRegistry metricRegistry = new MetricRegistry();
        JmxReporter reporter = JmxReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start();*/





/*
        Meter meter = metricRegistry.meter("meter");
        meter.mark();
        meter.mark(200);
        Histogram histogram = metricRegistry.histogram("histogram");
        histogram.update(12);
        histogram.update(17);
        Counter counter = metricRegistry.counter("counter");
        counter.inc();
        counter.dec();

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry).build();
        reporter.start(5, TimeUnit.MICROSECONDS);
        reporter.report();
        */


    /*protected JmxReporterServer(MetricRegistry registry, String host, int port,
    MBeanServer mBeanServer) {
  String serviceUrl =
      "service:jmx:rmi://localhost:" + port + "/jndi/rmi://" + host + ":" + port + "/jmxrmi";
  try {
    JMXServiceURL url = new JMXServiceURL(serviceUrl);
    connector = JMXConnectorServerFactory
        .newJMXConnectorServer(url, null, mBeanServer);
    rmiRegistry = LocateRegistry.createRegistry(port);
    reporter = JmxReporter.forRegistry(registry).registerWith(mBeanServer).build();
  } catch (Exception e) {
    throw new HoodieException("Jmx service url created " + serviceUrl, e);
  }
}*/
