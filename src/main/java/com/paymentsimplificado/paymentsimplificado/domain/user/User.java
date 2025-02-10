package com.paymentsimplificado.paymentsimplificado.domain.user;

import com.paymentsimplificado.paymentsimplificado.dtos.UserDTO;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@AllArgsConstructor
@EqualsAndHashCode(of="id") //indentifca a chave primária dessa Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera os id's de forma automatica na sequencia de 1,2,3...
  private Long id;
  private String firstName;
  private String lastName;

  @Column(unique = true)
  private String document;

  @Column(unique = true)
  private String email;

  private String password;

  private BigDecimal balance;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  public User() {
  }

  public User(UserDTO data) {
    this.firstName = data.firstName();
    this.lastName = data.lastName();
    this.balance = data.balance();
    this.userType = data.userType();
    this.password = data.password();
    this.document = data.document();
    this.email = data.email();
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getDocument() {
    return document;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
