package com.duongprj.logistic_service.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class ParcelIdGenerator implements IdentifierGenerator {

    private static AtomicLong counter;

    public ParcelIdGenerator(DatabaseUtils dbUtils) {
        String lastId = dbUtils.getLastParcelId();
        if (lastId != null && lastId.startsWith("DXPRESS")) {
            long lastNumber = Long.parseLong(lastId.substring(7));
            counter = new AtomicLong(lastNumber + 1);
        } else {
            counter = new AtomicLong(1000000000L); // Default start
        }
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return "DXPRESS" + String.format("%010d", counter.getAndIncrement());
    }
}