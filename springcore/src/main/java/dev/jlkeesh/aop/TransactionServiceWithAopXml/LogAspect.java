package dev.jlkeesh.aop.TransactionServiceWithAopXml;

public class LogAspect {

    public void logRequest() {
        System.out.println("Transaction Service perform Transaction Method called with params:");
    }

    public void logResponse() {

        System.out.println("Transaction Service create Transaction Method response:");
    }

    public void logError() {
        System.err.println("Transaction Service create Transaction Method error");
    }

}
