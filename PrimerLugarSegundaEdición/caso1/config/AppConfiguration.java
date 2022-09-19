package com.pichincha.codechallenge.config;

import com.pichincha.codechallenge.presenter.InterestDistributionPresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfiguration {
    @Bean
    public List<InterestDistributionPresenter> interestDistributions() {
        return Arrays.asList(
                InterestDistributionPresenter.builder()
                        .startDuration(1)
                        .endDuration(30)
                        .fee(new BigDecimal("2"))
                        .build(),
                InterestDistributionPresenter.builder()
                        .startDuration(31)
                        .endDuration(60)
                        .fee(new BigDecimal("2.20"))
                        .build(),
                InterestDistributionPresenter.builder()
                        .startDuration(61)
                        .endDuration(90)
                        .fee(new BigDecimal("2.40"))
                        .build(),
                InterestDistributionPresenter.builder()
                        .startDuration(91)
                        .endDuration(120)
                        .fee(new BigDecimal("2.60"))
                        .build(),
                InterestDistributionPresenter.builder()
                        .startDuration(121)
                        .endDuration(180)
                        .fee(new BigDecimal("2.80"))
                        .build(),
                InterestDistributionPresenter.builder()
                        .startDuration(181)
                        .endDuration(240)
                        .fee(new BigDecimal("3"))
                        .build(),
                InterestDistributionPresenter.builder()
                        .startDuration(241)
                        .endDuration(300)
                        .fee(new BigDecimal("3.20"))
                        .build(),
                InterestDistributionPresenter.builder()
                        .startDuration(301)
                        .endDuration(360)
                        .fee(new BigDecimal("3.40"))
                        .build()

        );
    }
}
