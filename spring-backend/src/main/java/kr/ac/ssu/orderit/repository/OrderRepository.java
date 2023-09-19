package kr.ac.ssu.orderit.repository;

import kr.ac.ssu.orderit.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT IFNULL(SUM(price), 0) FROM `order` WHERE TableSessionId = ?1", nativeQuery = true)
    Integer sumPriceByTableSessionId(Integer tableSessionId);
}
