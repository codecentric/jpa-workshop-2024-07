package de.codecentric.workshops.jpaworkshop.jpa.update;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
