package com.sdb.poc.ordersvc.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author simar bawa
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
