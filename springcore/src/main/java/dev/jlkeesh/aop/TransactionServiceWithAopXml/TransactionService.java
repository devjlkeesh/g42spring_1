package dev.jlkeesh.aop.TransactionServiceWithAopXml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class TransactionService {
    private static final Log log = LogFactory.getLog(TransactionService.class);

    public UUID createTransaction(Map<String, Object> params) {
        if (new Random().nextBoolean())
            throw new RuntimeException("This is a test");
        return UUID.randomUUID();
    }

    public String performTransaction(Map<String, Object> params) {
        var response = "transaction succeeded ";
        System.out.println("Transaction Service perform Transaction Method response:" + response);
        return response;
    }

}
