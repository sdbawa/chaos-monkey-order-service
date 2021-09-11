package com.sdb.poc.ordersvc.client.billingsvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * @author simar bawa
 */
@Service
@Slf4j
public class BillingServiceClient {

    public Boolean retrieveOrderStatus (Integer orderId) {
        var processBilling = new ProcessBillingResponse();
        processBilling.setOrderStatus(Boolean.FALSE);

        var req = new ProcessBillingRequest();
        req.setOrderId(orderId);
        req.setOrderDetails("Please process this new order");

            WebClient webClient = WebClient.create("http://localhost:8081/billing-svc");

            processBilling = webClient.post()
                    .uri("/process-billing-for-order")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(req), ProcessBillingRequest.class)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> {
                        System.out.println("4xx error");
                        return Mono.empty();
                    })
                    .onStatus(HttpStatus::is5xxServerError, response -> {
                        System.out.println("5xx error");
                        return Mono.empty();
                    })
                    .bodyToMono(ProcessBillingResponse.class)
                    .block()
                    ;
            return processBilling.getOrderStatus();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ProcessBillingRequest {
        private Integer orderId;
        private String orderDetails;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ProcessBillingResponse {
        private Boolean orderStatus;

    }
}
