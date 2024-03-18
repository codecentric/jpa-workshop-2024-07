package de.codecentric.workshops.jpaworkshop.jpa.datatypes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
