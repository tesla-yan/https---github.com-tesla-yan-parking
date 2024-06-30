package com.smart.parking.application.service;

import com.smart.parking.application.service.vo.ParkingEntryOrdersRequestVo;
import com.smart.parking.application.service.vo.ParkingEntryRequestVo;
import com.smart.parking.domain.parking.entity.ParkingEntryOrder;
import com.smart.parking.domain.parking.entity.ParkingRoot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    public Boolean parkingEntry(ParkingEntryRequestVo parkingEntryRequestVo) {

        ParkingRoot parkingRoot = new ParkingRoot();
        parkingRoot.parkingEntry(parkingEntryRequestVo.getPlateNumber());

        return true;
    }

    public List<ParkingEntryOrder> parkingEntryOrders(ParkingEntryOrdersRequestVo parkingEntryOrdersRequestVo) {

        ParkingRoot parkingRoot = new ParkingRoot();
        List<ParkingEntryOrder> parkingEntryOrders = parkingRoot.parkingEntryOrders(parkingEntryOrdersRequestVo.getParkingLotId());

        return parkingEntryOrders;
    }
}
