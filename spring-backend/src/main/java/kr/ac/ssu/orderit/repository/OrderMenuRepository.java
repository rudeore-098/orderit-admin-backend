package kr.ac.ssu.orderit.repository;

import jakarta.transaction.Transactional;
import kr.ac.ssu.orderit.entity.OrderMenu;
import kr.ac.ssu.orderit.repository.mapping.OrderMenuMapping;
import kr.ac.ssu.orderit.repository.mapping.TeamOrderMapping;
import kr.ac.ssu.orderit.repository.mapping.TeamOrderMenuMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer> {
    @Query(value = "SELECT om.OrderId as orderId, t.tableNo as tableNo, om.status as status, o.createdAt as createdAt " +
            "FROM order_menu om " +
            "JOIN menu m ON om.MenuId = m.id " +
            "JOIN `order` o ON o.id = om.OrderId " +
            "JOIN table_session t ON t.id = o.TableSessionId " +
            "WHERE m.team = ?1 AND status < 2 GROUP BY om.OrderId ORDER BY OrderId asc", nativeQuery = true)
    List<TeamOrderMapping> findAllTeamOrderIds(String team);
    @Query(value = "SELECT om.qty as qty, " +
            "m.title as title " +
            "FROM order_menu om " +
            "JOIN menu m ON om.MenuId = m.id " +
            "WHERE m.team = ?1 AND status < 2 AND om.OrderId = ?2", nativeQuery = true)
    List<TeamOrderMenuMapping> findAllTeamOrders(String team, Integer orderId);

    @Query(value = "UPDATE order_menu om " +
            "JOIN menu m ON om.MenuId = m.id " +
            "SET status = ?3 " +
            "WHERE m.team = ?1 AND status < 2 AND om.OrderId = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void updateTeamOrdersStatus(String team, Integer orderId, Integer status);

    @Query(value = "SELECT om.qty as qty, " +
            "m.title as title, om.status as status " +
            "FROM order_menu om " +
            "JOIN menu m ON om.MenuId = m.id " +
            "WHERE om.OrderId = ?1", nativeQuery = true)
    List<OrderMenuMapping> findAllByOrderId(Integer orderId);
}
