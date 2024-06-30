package com.smart.parking.adapter;

import com.smart.parking.domain.event.FrameEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AdapterEvent extends FrameEvent {

    private String cmd;

    public AdapterEvent(Object source) {
        super(source);
    }

    private final Map<String, Object> parameterMap = new LinkedHashMap<>();

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void putParameterData(String key, Object value) {
        parameterMap.put(key, value);
    }
}
