package kr.ac.ssu.orderit.repository;

import kr.ac.ssu.orderit.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
