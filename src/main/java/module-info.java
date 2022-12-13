module com.maxmind.minfraud {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.maxmind.geoip2;
    requires commons.validator;
    requires java.net.http;

    exports com.maxmind.minfraud;
    exports com.maxmind.minfraud.exception;
    exports com.maxmind.minfraud.request;
    exports com.maxmind.minfraud.response;
}
