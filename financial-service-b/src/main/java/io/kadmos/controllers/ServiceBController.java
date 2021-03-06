package io.kadmos.controllers;


import io.kadmos.database.model.BalanceBean;
import io.kadmos.hibernate.repositories.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

@RestController
public class ServiceBController {

    @Autowired
    private BalanceRepository balanceRepository;

    @GetMapping("/balance")
    public BalanceBean postBalgetBalanceance() throws Throwable {
        return balanceRepository.findById("b").orElseThrow((Supplier<Throwable>) () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find balance"));
    }

    @PostMapping("/balance")
    @Transactional
    public BalanceBean postBalance(@RequestBody BalanceBean balanceBean) throws Throwable {
        return balanceRepository.findById("b").map(databaseBalance -> {
            databaseBalance.setAmount(databaseBalance.getAmount() - balanceBean.getAmount());
            return balanceRepository.save(databaseBalance);
        }).orElseThrow((Supplier<Throwable>) () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find balance"));
    }
}
