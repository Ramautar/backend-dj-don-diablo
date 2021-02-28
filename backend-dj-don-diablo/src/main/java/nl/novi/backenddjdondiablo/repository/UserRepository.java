package nl.novi.backenddjdondiablo.repository;

import nl.novi.backenddjdondiablo.domain.Demo;
import nl.novi.backenddjdondiablo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * De JpaRepositories die jullie al kennen. Hier wordt gebruik gemaakt van Query Creation. Op basis van de methode naam
 * weten Spring en JPA welke query op de database uitgevoerd moet worden.
 * Hier kan meer informatie over gevonden worden:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 */
public interface UserRepository extends JpaRepository<User, Long> {
//    List<Demo> findAllByUsername(String username);

    Optional<User> findAllByUsername(String username);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
