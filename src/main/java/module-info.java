module com.maxmind.minfraud {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.net.http;
    requires commons.validator;
    requires geoip2;

    exports com.maxmind.minfraud;
    exports com.maxmind.minfraud.exception;
    exports com.maxmind.minfraud.request;
    exports com.maxmind.minfraud.response;
}
