package com.lambdashane.piggybank.controllers;

import com.lambdashane.piggybank.model.Coin;
import com.lambdashane.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController
{
    @Autowired
    CoinRepository coinRepo;

    private List<Coin> findCoins(
        List<Coin> myList,
        CheckCoin tester)
    {
        List<Coin> tempList = new ArrayList<>();

        for (Coin c : myList)
        {
            if (tester.test(c))
            {
                tempList.add(c);
            }
        }
        return tempList;
    }
    //    http://localhost:2019/total
    @GetMapping(value = "/total",
        produces = {"application/json"})
    public ResponseEntity<?> listAllEmployees()
    {
        List<Coin> myList = new ArrayList<>();
        coinRepo.findAll().iterator().forEachRemaining(myList::add);

        double total = 0;
        for(Coin c : myList)
        {
            total = total + (c.getValue() * c.getQuantity());
            System.out.println(c.getQuantity() + " " +
                (c.getQuantity() == 1 ? c.getName() : c.getNamepural()));
        }

        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
