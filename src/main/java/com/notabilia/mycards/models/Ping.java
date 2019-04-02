package com.notabilia.mycards.models;

import java.time.Instant;

public class Ping implements Model {

    private static final Double MODEL_VERSION = 1.0;

    private Instant date;
    private String version;

    public Ping(Instant date, String version) {
        this.date = date;
        this.version = version;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public Double getModelVersion() {
        return MODEL_VERSION;
    }
}
