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
@Table(name = "table_session", schema = "orderit", catalog = "")
public class TableSession {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "tableNo", nullable = false)
    private Integer tableNo;
    @Basic
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "deletedAt", nullable = true)
    private Timestamp deletedAt;
    @OneToMany(mappedBy = "tableSessionByTableSessionId")
    private Collection<Order> ordersById;

}
