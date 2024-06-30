package com.smart.parking.domain.event;

import com.smart.parking.exception.BusinessException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class FrameEvent extends BaseEvent{

    private BusinessException baseException;

    public FrameEvent(Object source) {
        super(source);
    }
}
