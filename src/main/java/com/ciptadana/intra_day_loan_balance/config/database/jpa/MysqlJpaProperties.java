package com.ciptadana.intra_day_loan_balance.config.database.jpa;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mysql.jpa")
public class MysqlJpaProperties {
    private HibernateProperties hibernateProperties;
    private boolean showSql;
    private boolean openInView;
}

