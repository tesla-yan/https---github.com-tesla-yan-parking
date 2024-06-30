package com.smart.parking.domain.parking.entity;

import com.smart.parking.domain.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "parking_entry_order")
@Data
@Slf4j
@EqualsAndHashCode(callSuper=false)
public class ParkingEntryOrder extends ParkingEntityBase implements Serializable {

    private final static String ENTITY_NAME = "com.smart.parking.domain.parking.entity.ParkingEntryOrder";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer parkingLotId;
    private String parkingLotName;
    private String plateNumber;
    private Integer active;
//    private String cardType;
//    private String entryWay;
//    private Date entryTime;
//    private Integer createdBy;
//    private Date createdTime;
//    private Integer updatedBy;
//    private Date updatedTime;

    public void createParkingEntryOrder() {
        ParkingEntryOrder parkingEntryOrder = new ParkingEntryOrder();
        parkingEntryOrder.setPlateNumber(this.plateNumber);
        // 存哪些字段
        parkingEntryOrder.save();
    }

    public static List<ParkingEntryOrder> queryParkingEntryOrderList(Integer parkingLotId) {
        return invokeFind(ENTITY_NAME, "findAllByParkingLotId", parkingLotId);
    }

    @Override
    protected Integer getEntityId() {
        return id;
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    public int compareTo(EntityBase o) {
        return 0;
    }
}
