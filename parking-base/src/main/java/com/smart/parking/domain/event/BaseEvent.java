package com.smart.parking.domain.event;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseEvent extends ApplicationEvent implements Serializable {

    private String eventName;

    private final Map<String, Object> parameterMap = new LinkedHashMap<>();

    private Object eventResult;

    public BaseEvent(Object source) {
        super(source);
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void putParameterData(String key, Object value) {
        parameterMap.put(key, value);
    }

    public Object getEventResult() {
        return eventResult;
    }

    public void setEventResult(Object eventResult) {
        this.eventResult = eventResult;
    }

    public BaseEvent publish() {
        SpringEventPublisherSupport eventPublisherSupport = new SpringEventPublisherSupport();
        eventPublisherSupport.publish(this);
        return this;
    }
}
