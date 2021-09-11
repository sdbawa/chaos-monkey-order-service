package com.sdb.poc.ordersvc.order;

import com.sdb.poc.ordersvc.client.billingsvc.BillingServiceClient;
import com.sdb.poc.ordersvc.order.OrderRepository;
import com.sdb.poc.ordersvc.order.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author simar bawa
 */
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final BillingServiceClient billingServiceClient;

    public OrderService(OrderRepository orderRepository, BillingServiceClient billingServiceClient) {
        this.orderRepository = orderRepository;
        this.billingServiceClient = billingServiceClient;
    }

    public List<Orders> getAllOrders() {
        List<Orders> orders = null;
        //try {
            orders =  orderRepository.findAll();
        //} catch(Exception e) {
            //log.error("Exception when fetching DB records {}", e);
        //}
        return orders;
    }


    public Optional<Orders> getOrderBillingStatus(int id) {
        Optional<Orders> orders = orderRepository.findById(id);
            if(! orders.isEmpty()) {
                Boolean billingStatus = billingServiceClient.retrieveOrderStatus(id);
                if(billingStatus != null) {
                    orders.get().setBillingStatus(billingStatus);
                }
            }
        return orders;
    }
}
