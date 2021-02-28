package nl.novi.backenddjdondiablo.repository;

import nl.novi.backenddjdondiablo.domain.Demo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface DemoRepository extends JpaRepository <Demo,Long>{
    List<Demo> findAllByUsername(String username);
}
