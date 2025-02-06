package com.example.hibernate.util;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@EnableTransactionManagement
public class HibernateUtil implements MetadataBuilderContributor {

    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        // Register custom SQL function
        metadataBuilder.applySqlFunction(
            "lpad",
            new StandardSQLFunction("lpad", StringType.INSTANCE)
        );
    }
}