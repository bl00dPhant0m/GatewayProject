package ru.bl00dphant0m.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bl00dphant0m.userservice.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
