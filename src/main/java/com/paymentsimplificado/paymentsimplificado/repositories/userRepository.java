package com.paymentsimplificado.paymentsimplificado.repositories;

import com.paymentsimplificado.paymentsimplificado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<User, Long> { // o parametro Long se da pelo fato da chave primária da class user ser do tipo Long
  Optional<User> findUserByDocument(String document);

  Optional<User> findUserById(Long id);
}
