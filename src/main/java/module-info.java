/**
 * This module provides an API for the minFraud Score, Insights, and Factors web services.
 * */ 
module com.maxmind.minfraud {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires transitive com.maxmind.geoip2;
    requires org.apache.commons.validator;
    requires java.net.http;

    exports com.maxmind.minfraud;
    exports com.maxmind.minfraud.exception;
    exports com.maxmind.minfraud.request;
    exports com.maxmind.minfraud.response;
}
