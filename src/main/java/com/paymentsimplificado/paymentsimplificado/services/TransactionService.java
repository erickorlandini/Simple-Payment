package com.paymentsimplificado.paymentsimplificado.services;

import com.paymentsimplificado.paymentsimplificado.domain.transaction.Transaction;
import com.paymentsimplificado.paymentsimplificado.domain.user.User;
import com.paymentsimplificado.paymentsimplificado.dtos.TransactionDTO;
import com.paymentsimplificado.paymentsimplificado.repositories.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
  @Autowired
  private UserService userService;

  @Autowired
  private transactionRepository repository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private NotificationService notificationService;

  public Transaction createTransaction(TransactionDTO transaction) throws Exception {
    User sender = this.userService.findUserById(transaction.senderId());
    User receiver = this.userService.findUserById(transaction.receiverId());

    userService.validateTransaction(sender, transaction.value());

    boolean isAuthorizade = this.authorizeTransaction(sender, transaction.value());
    if(!isAuthorizade){
      throw new Exception("Transação não autorizada");
    }

    Transaction newTransaction = new Transaction();
    newTransaction.setAmount(transaction.value());
    newTransaction.setSender(sender);
    newTransaction.setReceiver(receiver);
    newTransaction.setTimestamp(LocalDateTime.now());

    sender.setBalance(sender.getBalance().subtract(transaction.value()));
    receiver.setBalance(receiver.getBalance().add(transaction.value()));

    this.repository.save(newTransaction);
    this.userService.saveUser(sender);
    this.userService.saveUser(receiver);

    this.notificationService.sendNotification(sender, "Transação realizada com sucesso");

    this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

    return newTransaction;
  }

  public boolean authorizeTransaction(User sender, BigDecimal value) {
       ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

       if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
         String message = (String) authorizationResponse.getBody().get("message");
         return "Authorizade".equalsIgnoreCase(message);
       }else return false;
  }
}
