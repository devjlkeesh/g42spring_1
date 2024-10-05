package dev.jlkeesh.aop.TransactionServiceWithAopXml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        TransactionService transactionService = context.getBean(TransactionService.class);
        UUID trId = transactionService.createTransaction(Map.of(
                "pan", "8600909012122344",
                "amount", 10000l,
                "receiver", "9080808080808080"
        ));
       /* String performed = transactionService.performTransaction(Map.of(
                "otp", "901234",
                "trId", trId
        ));
        System.out.println("performed = " + performed);*/
    }
}
