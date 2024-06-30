package com.smart.parking.adapter.repository.jpa;

import com.smart.parking.adapter.jpa.RepositoryBase;
import com.smart.parking.domain.parking.entity.ParkingEntryOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingEntryOrderRepository extends RepositoryBase<ParkingEntryOrder, Integer> {

    @Query(nativeQuery = true, value = "select * from parking_entry_order where plate_number =:plateNumber")
    List<ParkingEntryOrder> findByPlateNumber(String plateNumber);

    @Query(nativeQuery = true, value = "select * from parking_entry_order where parking_lot_id =:parkingLotId")
    List<ParkingEntryOrder> findAllByParkingLotId(Integer parkingLotId);
}
