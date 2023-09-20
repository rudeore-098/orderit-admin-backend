package kr.ac.ssu.orderit.repository.mapping;

import java.sql.Timestamp;

public interface OrderMapping {
    Integer getOrderId();
    Integer getTableNo();
    Integer getPrice();
    Timestamp getCreatedAt();
}
