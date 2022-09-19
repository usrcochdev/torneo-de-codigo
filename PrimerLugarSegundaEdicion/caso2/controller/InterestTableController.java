package com.pichincha.codechallenge.controller;

import com.pichincha.codechallenge.presenter.InterestTablePresenter;
import com.pichincha.codechallenge.service.InterestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InterestTableController {
    @Autowired
    private InterestTableService interestTableService;

    @GetMapping("interestTable")
    public List<InterestTablePresenter> generateInterestTable(@RequestParam Double yearlyFee, @RequestParam Double amount, @RequestParam Integer yearlyPayments, @RequestParam Integer years) {
        return interestTableService.generateInterestTable(yearlyFee, amount, yearlyPayments, years);
    }
}
