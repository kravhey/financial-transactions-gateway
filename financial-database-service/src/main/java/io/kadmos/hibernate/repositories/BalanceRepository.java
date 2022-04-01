package io.kadmos.hibernate.repositories;

import io.kadmos.database.model.BalanceBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface BalanceRepository extends CrudRepository<BalanceBean, String> {

}
