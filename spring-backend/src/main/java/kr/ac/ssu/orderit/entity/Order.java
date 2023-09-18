package kr.ac.ssu.orderit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "order", schema = "orderit", catalog = "")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "TableSessionId", nullable = false)
    private Integer tableSessionId;
    @Basic
    @Column(name = "price", nullable = false)
    private Integer price;
    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;
    @ManyToOne
    @JoinColumn(name = "TableSessionId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private TableSession tableSessionByTableSessionId;
    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<OrderMenu> orderMenusById;
}
