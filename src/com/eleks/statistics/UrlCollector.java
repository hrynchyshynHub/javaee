package com.eleks.statistics;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ivan.hrynchyshyn on 10.07.2017.
 */
public class UrlCollector {
    private List<UrlObject> allRequestedUrls = new CopyOnWriteArrayList<>();
    private static UrlCollector urlCollector;
    private UrlCollector(){}
    public static UrlCollector getInstance(){
        if(urlCollector == null){
            synchronized (UrlCollector.class){
                if(urlCollector ==null) urlCollector = new UrlCollector();
            }
        }
        return  urlCollector;
    }

    public void addUrl(UrlObject urlObject){
        allRequestedUrls.add(urlObject);
    }

    public List<UrlObject> getVisitedUrlForHour(int startedHour){
        List<UrlObject> urls = new ArrayList<>();
        for(UrlObject url:allRequestedUrls){
            if(startedHour==url.getHour()){
                urls.add(url);
            }
        }
        return urls;
    }
    public int getNumberOfVisitedUrl(String urlString){
        int counter = 0;
        for(UrlObject url:allRequestedUrls){
            if(url.getUrl().equals(urlString)){
                counter++;
            }
        }
        return counter;
    }
    public Set<String> getUniqUrl(){
        Set<String> urls = new HashSet<>();
        for(UrlObject url:allRequestedUrls){
            urls.add(url.getUrl());
        }
        return urls;
    }

}
