package com.pichincha.codechallenge.presenter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterestTablePresenter {
    private Integer paymentNumber;
    private String periodFee;
    private String capital;
    private String amount;
    private String pendingAmount;
}
