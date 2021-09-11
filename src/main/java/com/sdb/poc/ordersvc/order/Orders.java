package com.sdb.poc.ordersvc.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;

/**
 * @author simar bawa
 */
@Getter
@Data
@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Boolean billingStatus;
}
