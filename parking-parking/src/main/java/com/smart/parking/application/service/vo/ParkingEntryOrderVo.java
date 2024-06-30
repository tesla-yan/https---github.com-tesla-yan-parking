package com.smart.parking.application.service.vo;

import com.google.common.collect.Lists;
import com.smart.parking.domain.parking.entity.ParkingEntryOrder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class ParkingEntryOrderVo {

    private Integer id;
    private Integer parkingLotId;
    private String parkingLotName;
    private String plateNumber;

    public static List<ParkingEntryOrderVo> fillDataWithParkingEntryOrders(List<ParkingEntryOrder> parkingEntryOrders) {
        List<ParkingEntryOrderVo> parkingEntryOrderVos = Lists.newArrayList();
        for (ParkingEntryOrder parkingEntryOrder : parkingEntryOrders) {
            ParkingEntryOrderVo parkingEntryOrderVo = new ParkingEntryOrderVo();
            BeanUtils.copyProperties(parkingEntryOrder, parkingEntryOrderVo);
            parkingEntryOrderVos.add(parkingEntryOrderVo);
        }
        return parkingEntryOrderVos;
    }
}
