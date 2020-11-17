package ru.sbrf.posttime;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.management.*;

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

        SimpleAgent jmxAgent = new SimpleAgent();

    }

}
