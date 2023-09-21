package kr.ac.ssu.orderit.repository.mapping;

import java.sql.Timestamp;

public interface TeamOrderMapping {
    Integer getOrderId();
    Integer getTableNo();
    Integer getStatus();
    Timestamp getCreatedAt();
    String getTitle();
    Integer getIdx();
    Integer getQty();
}
