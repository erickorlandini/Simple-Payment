package com.paymentsimplificado.paymentsimplificado.services;

import com.paymentsimplificado.paymentsimplificado.domain.user.User;
import com.paymentsimplificado.paymentsimplificado.domain.user.UserType;
import com.paymentsimplificado.paymentsimplificado.dtos.UserDTO;
import com.paymentsimplificado.paymentsimplificado.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
  @Autowired
  private userRepository repository;

  public void validateTransaction(User sender, BigDecimal amount) throws Exception {
    if(sender.getUserType() == UserType.MERCHANT) {
      throw new Exception("Usuário do tipo Lojista não esta autorizado a realizar transação");
    }

    if(sender.getBalance().compareTo(amount) < 0) {
      throw new Exception("Saldo insuficiente");
    }
  }

  public User findUserById(Long id) throws Exception {
    return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
  }

  public User createUser(UserDTO data) {
    User newUser = new User(data);
    this.saveUser(newUser);
    return newUser;
  }

  public List<User> getAllUsers() {
    return this.repository.findAll();
  }

  public void saveUser(User user) {
    this.repository.save(user);
  }

}
