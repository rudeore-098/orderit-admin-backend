package kr.ac.ssu.orderit.repository;

import kr.ac.ssu.orderit.entity.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer> {
}
