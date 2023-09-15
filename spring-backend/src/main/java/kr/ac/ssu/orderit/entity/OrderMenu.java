package kr.ac.ssu.orderit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "order_menu", schema = "orderit", catalog = "")
public class OrderMenu {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idx", nullable = false)
    private Integer idx;
    @Basic
    @Column(name = "OrderId", nullable = false)
    private Integer orderId;
    @Basic
    @Column(name = "MenuId", nullable = false)
    private Integer menuId;
    @Basic
    @Column(name = "qty", nullable = false)
    private Integer qty;
    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "OrderId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Order orderByOrderId;
    @ManyToOne
    @JoinColumn(name = "MenuId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Menu menuByMenuId;
}
