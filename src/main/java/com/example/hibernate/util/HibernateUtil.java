package com.example.hibernate.util;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@EnableTransactionManagement
public class HibernateUtil implements FunctionContributor {

    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        // Register custom SQL function
        functionContributions.getFunctionRegistry().registerPattern(
            "lpad",
            "lpad(?1, ?2, ?3)",
            functionContributions.getTypeConfiguration().getBasicTypeRegistry()
                .resolve(StandardBasicTypes.STRING)
        );
    }
}