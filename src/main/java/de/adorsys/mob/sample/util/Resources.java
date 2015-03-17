package de.adorsys.mob.sample.util;


import java.util.Properties;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resources {

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    public Session produceMailSession(InjectionPoint injectionPoint) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", System.getProperty("smtp.host", "localhost"));

        return Session.getDefaultInstance(properties);
    }
}
