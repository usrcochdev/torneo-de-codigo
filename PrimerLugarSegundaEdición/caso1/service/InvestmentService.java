package com.pichincha.codechallenge.service;

import com.pichincha.codechallenge.presenter.InvestmentSimulationPresenter;

import java.math.BigDecimal;
import java.util.List;

public interface InvestmentService {
    List<InvestmentSimulationPresenter> getSimulation(BigDecimal amount, Integer daysDuration);
}
