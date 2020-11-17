package ru.sbrf.posttime;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import ru.sbrf.posttime.mbeans.*;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class MetricsControlApplication extends Application<MetricsControlConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MetricsControlApplication().run(args);
    }

    @Override
    public String getName() {
        return "MetricsControl";
    }

    @Override
    public void initialize(final Bootstrap<MetricsControlConfiguration> bootstrap) {
        // TODO: application initialization
    }
    @Override
    public void run(final MetricsControlConfiguration configuration,
                    final Environment environment) throws MalformedObjectNameException,
            InstanceAlreadyExistsException, NotCompliantMBeanException, MBeanRegistrationException,
            InterruptedException {
        // TODO: implement application
        System.out.println("running here");

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.sbrf.posttime.mbeans:type=Cl");
        Cl mbean = new Cl();
        mbs.registerMBean(mbean, name);
        //System.out.println("Waiting forever...");
        //Thread.sleep(Long.MAX_VALUE);

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

    }
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

}
