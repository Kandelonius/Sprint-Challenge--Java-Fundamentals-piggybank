package com.lambdashane.piggybank.repositories;

import com.lambdashane.piggybank.model.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long>
{
}
