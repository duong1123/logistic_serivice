package com.duongprj.logistic_service.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class BatchIdGenerator implements IdentifierGenerator {

    private static AtomicLong counter;

    public BatchIdGenerator(DatabaseUtils dbUtils) {
        String lastId = dbUtils.getLastBatchId();
        if (lastId != null && lastId.startsWith("BATCH")) {
            long lastNumber = Long.parseLong(lastId.substring(5)); // Corrected to substring(5) to get the number part
            counter = new AtomicLong(lastNumber + 1);
        } else {
            counter = new AtomicLong(1000000000L); // Default start
        }
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return "BATCH" + String.format("%010d", counter.getAndIncrement());
    }
}