package br.com.kenzie.learningSpring.repository;

import br.com.kenzie.learningSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByCpf(final String cpf);
    boolean existsUserByEmail(final String email);

}
