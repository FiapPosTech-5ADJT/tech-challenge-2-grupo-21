package br.com.fiap.park_tech.enums;

import lombok.Getter;

@Getter
public enum PaymentMethods {
    CREDIT_CARD("Credito"),
    DEBIT_CARD("Debito"),
    PIX("PIX"),
    CASH("Dinheiro");

    private final String description;

    PaymentMethods(String description) {
        this.description = description;
    }

    public static PaymentMethods fromDescription(String description) {
        for (PaymentMethods method : PaymentMethods.values()) {
            if (method.getDescription().equalsIgnoreCase(description)) {
                return method;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }

}
