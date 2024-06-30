package com.smart.parking.domain.parking.entity;

import java.util.List;

public class ParkingRoot {

    public void parkingEntry(String plateNumber) {

        ParkingEntryOrder parkingEntryOrder = new ParkingEntryOrder();
        parkingEntryOrder.setPlateNumber(plateNumber);

        parkingEntryOrder.createParkingEntryOrder();

    }

    public List<ParkingEntryOrder> parkingEntryOrders(Integer parkingLotId) {
        return ParkingEntryOrder.queryParkingEntryOrderList(parkingLotId);
    }
}
