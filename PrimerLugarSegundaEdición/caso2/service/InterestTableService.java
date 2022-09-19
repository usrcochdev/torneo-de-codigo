package com.pichincha.codechallenge.service;

import com.pichincha.codechallenge.presenter.InterestTablePresenter;

import java.util.List;

public interface InterestTableService {
    List<InterestTablePresenter> generateInterestTable(Double yearlyFee, Double amount, Integer yearlyPayments, Integer years);
}
