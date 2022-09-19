package com.pichincha.codechallenge.presenter;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvestmentSimulationPresenter {
    private String duration;
    private String fee;
    private String profit;
    private String grandTotal;
}
