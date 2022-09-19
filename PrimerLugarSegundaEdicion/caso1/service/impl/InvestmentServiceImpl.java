package com.pichincha.codechallenge.service.impl;

import com.pichincha.codechallenge.presenter.InterestDistributionPresenter;
import com.pichincha.codechallenge.presenter.InvestmentSimulationPresenter;
import com.pichincha.codechallenge.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class InvestmentServiceImpl implements InvestmentService {
    @Autowired
    private List<InterestDistributionPresenter> interestDistributionPresenters;

    @Override
    public List<InvestmentSimulationPresenter> getSimulation(BigDecimal amount, Integer daysDuration) {
        validateInputs(amount, daysDuration);
        List<InvestmentSimulationPresenter> result = new ArrayList<>();
        if (Objects.isNull(daysDuration)) {
            for (InterestDistributionPresenter interestDistributionPresenter : interestDistributionPresenters) {
                result.add(generateSingleSimulation(amount, interestDistributionPresenter, interestDistributionPresenter.getEndDuration()));
            }
            return result;
        }
        InterestDistributionPresenter requestedDistribution = findDistributionByDay(daysDuration, interestDistributionPresenters);
        return Collections.singletonList(generateSingleSimulation(amount, requestedDistribution, daysDuration));
    }

    private InterestDistributionPresenter findDistributionByDay(Integer daysDuration, List<InterestDistributionPresenter> interestDistributionPresenters) {
        for (InterestDistributionPresenter interestDistributionPresenter : interestDistributionPresenters) {
            if (interestDistributionPresenter.getStartDuration().compareTo(daysDuration) <= 0 && interestDistributionPresenter.getEndDuration().compareTo(daysDuration) >= 0) {
                return interestDistributionPresenter;
            }
        }
        throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "El plazo máximo permitido es 360 días");
    }

    private InvestmentSimulationPresenter generateSingleSimulation(BigDecimal amount, InterestDistributionPresenter interestDistributionPresenter, Integer daysDuration) {
        Double profit = amount.doubleValue() * ((interestDistributionPresenter.getFee().divide(new BigDecimal("100")).multiply(BigDecimal.valueOf(daysDuration))).doubleValue() / 360);
        BigDecimal grandTotal = amount.add(new BigDecimal(profit)).setScale(2, RoundingMode.HALF_UP);
        return InvestmentSimulationPresenter.builder()
                .duration(String.valueOf(daysDuration))
                .fee(String.valueOf(interestDistributionPresenter.getFee()).concat("%"))
                .profit("$".concat(String.valueOf(profit)))
                .grandTotal("$".concat(String.valueOf(grandTotal)))
                .build();
    }

    private void validateInputs(BigDecimal amount, Integer daysDuration) {
        if (Objects.isNull(amount)) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Se requiere un monto positivo para su inversión");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Se requiere un monto positivo para su inversión");
        }

        if (Objects.nonNull(daysDuration) && daysDuration.compareTo(0) <= 0) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Se requiere un plazo positivo para su inversión");
        }
    }
}
