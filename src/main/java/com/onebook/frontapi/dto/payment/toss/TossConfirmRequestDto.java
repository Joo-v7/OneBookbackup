package com.onebook.frontapi.dto.payment.toss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TossConfirmRequestDto {
    private String paymentKey;
    private String orderId;
    private String amount;
}