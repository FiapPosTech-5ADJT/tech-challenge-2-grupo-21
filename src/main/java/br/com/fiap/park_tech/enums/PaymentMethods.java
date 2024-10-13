package br.com.fiap.park_tech.enums;

import lombok.Getter;

@Getter
public enum PaymentMethods {
    CREDIT_CARD("Crédito"),
    DEBIT_CARD("Débito"),
    PIX("PIX"),
    CASH("Dinheiro");

    private final String description;

    PaymentMethods(String description) {
        this.description = description;
    }

}
