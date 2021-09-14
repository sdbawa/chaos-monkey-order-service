package com.sdb.poc.ordersvc.order;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author simar bawa
 */
@RestController
@RequestMapping("order-svc")
@Slf4j
public class OrderController {

    private final OrderComponent orderComponent;


    public OrderController(OrderComponent orderComponent) {
        this.orderComponent = orderComponent;
    }

    /**
     * This API returns a list of all orders along with the status of each order
     * @return
     */
    @GetMapping(value="/all-orders")
    public List<Orders> orders(){
        return orderComponent.getAllOrders();
    }

    /**
     * This API returns order status along with the billing status of a particular order by id
     * @param id
     * @return
     */
    @GetMapping(value="/order-billing-status/{id}")
    public Optional<Orders> getOrderWithBillingStatus(@PathVariable Integer id){
        return orderComponent.getOrderBillingStatus(id);
    }

}
