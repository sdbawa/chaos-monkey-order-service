package com.sdb.poc.ordersvc.order;

import com.sdb.poc.ordersvc.client.billingsvc.BillingServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author simar bawa
 */
@Component
@Slf4j
public class OrderComponent {

    private final OrderRepository orderRepository;

    private final BillingServiceClient billingServiceClient;

    public OrderComponent(OrderRepository orderRepository, BillingServiceClient billingServiceClient) {
        this.orderRepository = orderRepository;
        this.billingServiceClient = billingServiceClient;
    }

    public List<Orders> getAllOrders() {
        List<Orders> orders;
        //try {
            orders =  orderRepository.findAll();
        //} catch(Exception e) {
            //log.error("Exception when fetching DB records {}", e);
        //}
        log.debug ( "returning orders ");
        return orders;
    }


    public Optional<Orders> getOrderBillingStatus(int id) {
        Optional<Orders> orders;
        //Optional<Orders> orders = Optional.empty();
        //try {
            orders = orderRepository.findById(id);
            if(! orders.isEmpty()) {

                    Boolean billingStatus = billingServiceClient.retrieveOrderStatus(id);
                    if(billingStatus != null) {
                        orders.get().setBillingStatus(billingStatus);
                        log.debug ( "SUCCESS gettig billing status from the billing-svc for {}", id );
                    }
                }
            //} catch (Exception e) {
            //log.error("Error calling the Billing Service {}", e);
            //return orders;
        //}
        return orders;
    }
}
