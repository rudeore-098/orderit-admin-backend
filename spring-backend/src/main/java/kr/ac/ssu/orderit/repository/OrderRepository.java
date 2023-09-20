package kr.ac.ssu.orderit.repository;

import kr.ac.ssu.orderit.entity.Order;
import kr.ac.ssu.orderit.repository.mapping.OrderMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT IFNULL(SUM(price), 0) FROM `order` WHERE TableSessionId = ?1", nativeQuery = true)
    Integer sumPriceByTableSessionId(Integer tableSessionId);

    @Query(value = "SELECT t.tableNo as tableNo, o.id as orderId, o.price as price, o.createdAt as createdAt FROM `order` o " +
            "JOIN table_session t ON o.TableSessionId = t.id " +
            "ORDER BY o.id DESC", nativeQuery = true)
    List<OrderMapping> findAllOrders();
}
