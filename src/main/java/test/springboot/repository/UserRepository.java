package test.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.springboot.model.Role;
import test.springboot.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    List<User> findAllByRolesContains(Collection<Role> roles);
    List<User> findAllByName(String name);
}
