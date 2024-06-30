package com.smart.parking.adapter.controller;

import com.smart.parking.application.service.ParkingService;
import com.smart.parking.application.service.vo.ParkingEntryOrderVo;
import com.smart.parking.application.service.vo.ParkingEntryOrdersRequestVo;
import com.smart.parking.application.service.vo.ParkingEntryRequestVo;
import com.smart.parking.common.ApiResult;
import com.smart.parking.domain.parking.entity.ParkingEntryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ParkingController
 */
@RestController
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    /**
     * 停车进场
     * @param parkingEntryRequestVo
     * @return
     */
    @PostMapping("/parking/entry")
    public ApiResult<Boolean> parkingEntry(@RequestBody ParkingEntryRequestVo parkingEntryRequestVo) {

        Boolean isSuccess = parkingService.parkingEntry(parkingEntryRequestVo);

        return ApiResult.success(isSuccess);
    }

    /**
     * 停车进场后未出场的订单
     * @param parkingEntryOrdersRequestVo
     * @return
     */
    @PostMapping("/parking/entry/orders")
    public ApiResult<List<ParkingEntryOrderVo>> parkingEntryOrders(@RequestBody ParkingEntryOrdersRequestVo parkingEntryOrdersRequestVo) {

        List<ParkingEntryOrder> parkingEntryOrders = parkingService.parkingEntryOrders(parkingEntryOrdersRequestVo);
        return ApiResult.success(ParkingEntryOrderVo.fillDataWithParkingEntryOrders(parkingEntryOrders));
    }
}
