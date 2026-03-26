package com.example.hibernate.persistence;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.type.StandardBasicTypes;

/**
 * Registers LPAD so Hibernate can render it from JPQL using the function registry.
 */
public class LpadFunctionContributor implements FunctionContributor {

    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        functionContributions.getFunctionRegistry().registerPattern(
            "lpad",
            "lpad(?1, ?2, ?3)",
            functionContributions.getTypeConfiguration().getBasicTypeRegistry()
                .resolve(StandardBasicTypes.STRING)
        );
    }
}
