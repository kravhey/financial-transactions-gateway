package io.kadmos.controllers;

import io.kadmos.database.BalanceBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {

    @PostMapping("/balance")
    public BalanceBean postBalance(@RequestBody BalanceBean balanceBean) {
        return balanceBean;
    }
}
