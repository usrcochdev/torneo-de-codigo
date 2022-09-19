package com.pichincha.codechallenge;

import com.pichincha.codechallenge.service.InterestTableService;
import com.pichincha.codechallenge.service.impl.InterestTableServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class CodeChallengeApplication {

    public static void main(String[] args) throws IOException {
        InterestTableService interestTableService = new InterestTableServiceImpl();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        Integer years;
        Integer paymentsPerYear;
        Double amount;
        Double yearlyFee;
        String source;
        do {
            System.out.println("Ingrese la cantidad de años: ");
            source = reader.readLine();
        } while (!isInteger(source));
        years = Integer.parseInt(source);
        do {
            System.out.println("Ingrese la cantidad de pagos por año: ");
            source = reader.readLine();
        } while (!isInteger(source));
        paymentsPerYear = Integer.parseInt(source);
        interestTableService.generateInterestTable(12.0, 85000.0, paymentsPerYear, years);
        SpringApplication.run(CodeChallengeApplication.class, args);
    }

    public static boolean isInteger(String source) {
        try {
            Integer.parseInt(source);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("El valor no es entero");
            return false;
        }
    }

    public static boolean isDouble(String source) {
        try {
            Double.parseDouble(source);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("El valor no es un número");
            return false;
        }
    }

}
