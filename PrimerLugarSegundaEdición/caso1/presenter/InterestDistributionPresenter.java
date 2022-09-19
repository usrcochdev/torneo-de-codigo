package com.pichincha.codechallenge.presenter;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestDistributionPresenter {
    private Integer startDuration;
    private Integer endDuration;
    private BigDecimal fee;
}
