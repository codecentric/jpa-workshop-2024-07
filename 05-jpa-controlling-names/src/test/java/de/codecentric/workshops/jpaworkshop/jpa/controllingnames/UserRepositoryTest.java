package de.codecentric.workshops.jpaworkshop.jpa.controllingnames;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	void test() {
		final var user = new User(new UserPK(42L, "foo"), UserLevel.USER);
		userRepository.saveAndFlush(user);

		Optional<User> result = userRepository.findById(new UserPK(42L, "foo"));

		assertThat(result).isPresent();
		assertThat(result.get().getName()).isEqualTo("foo");
	}
}