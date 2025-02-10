package com.paymentsimplificado.paymentsimplificado.dtos;

import com.paymentsimplificado.paymentsimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
