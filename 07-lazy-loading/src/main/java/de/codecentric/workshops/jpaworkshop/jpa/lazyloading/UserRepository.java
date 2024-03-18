package de.codecentric.workshops.jpaworkshop.jpa.lazyloading;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
