package de.codecentric.workshops.jpaworkshop.jpa.update;

import java.time.LocalDate;
import java.util.List;

//import de.codecentric.workshops.jpaworkshop.jpa.update.User.UsernameOnly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//	List<UsernameOnly> getByDateOfBirth(LocalDate dateOfBirth);

	<T> List<T> getByDateOfBirth(LocalDate dateOfBirth, Class<T> clazz);

	List<User> findAllByNameContains(String name);
}
