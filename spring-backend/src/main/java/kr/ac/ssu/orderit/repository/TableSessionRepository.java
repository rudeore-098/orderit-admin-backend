package kr.ac.ssu.orderit.repository;

import kr.ac.ssu.orderit.entity.TableSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TableSessionRepository extends JpaRepository<TableSession, Integer> {
    List<TableSession> findAllByDeletedAtIsNullOrderByTableNoAsc();
    Optional<TableSession> findByIdAndDeletedAtIsNull(Integer id);
}
