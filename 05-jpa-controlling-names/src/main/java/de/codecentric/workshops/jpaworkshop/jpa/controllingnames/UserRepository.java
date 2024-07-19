package de.codecentric.workshops.jpaworkshop.jpa.controllingnames;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserPK> {
}
