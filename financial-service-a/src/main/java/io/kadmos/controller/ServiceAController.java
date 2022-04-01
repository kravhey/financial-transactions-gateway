package io.kadmos.controller;


import io.kadmos.database.model.BalanceBean;
import io.kadmos.hibernate.repositories.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.function.Supplier;

@RestController
public class ServiceAController {

    @Autowired
    private BalanceRepository balanceRepository;

    @GetMapping("/balance")
    public BalanceBean postBalgetBalanceance() throws Throwable {
        return balanceRepository.findById("a").orElseThrow((Supplier<Throwable>) () -> new RuntimeException("Cannot find balance"));
    }

    @PostMapping("/balance")
    @Transactional
    public BalanceBean postBalance(@RequestBody BalanceBean balanceBean) throws Throwable {
        return balanceRepository.findById("a").map(balanceBean1 -> {
            balanceBean1.setAmount(balanceBean1.getAmount() - balanceBean.getAmount());
            final BalanceBean finalBalance = balanceRepository.save(balanceBean1);
            finalBalance.setId(null);
            return finalBalance;
        }).orElseThrow((Supplier<Throwable>) () -> new RuntimeException("Cannot find balance"));
    }
}
