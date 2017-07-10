package com.eleks.statistics;

import java.time.LocalDateTime;

/**
 * Created by ivan.hrynchyshyn on 10.07.2017.
 */
public class UrlObject {
    private String url;
    private LocalDateTime localDateTime;


    public UrlObject(String url) {
        this.url = url;
        this.localDateTime = LocalDateTime.now();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    public int getHour(){
        return localDateTime.getHour();
    }

}
