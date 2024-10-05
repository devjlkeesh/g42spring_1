package dev.jlkeesh.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.UUID;


public class TransactionService {

    private static final Log log = LogFactory.getLog(TransactionService.class);

    public UUID createTransaction(Map<String, Object> params) {
        log.info("Transaction Service create Transaction Method called with params:" + params);
        var response =  UUID.randomUUID();
        log.info("Transaction Service create Transaction Method response:" + response);
        return response;
    }

    public String performTransaction(Map<String, Object> params) {
        log.info("Transaction Service perform Transaction Method called with params:" + params);
        var response = "transaction succeeded ";
        log.info("Transaction Service perform Transaction Method response:" + response);
        return response;
    }

}
