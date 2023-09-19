package kr.ac.ssu.orderit.repository;

import kr.ac.ssu.orderit.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findAllByIdIsNotNull();
    Optional<Menu> findByIdAndIsAvail(Integer id, Integer isAvail);
}
