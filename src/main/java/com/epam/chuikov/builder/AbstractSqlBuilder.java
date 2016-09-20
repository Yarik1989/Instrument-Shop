package com.epam.chuikov.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;


public abstract class AbstractSqlBuilder {
    public static final String COMA = ",";
    public static final String EQUAL = " =";
    public static final String QUESTION_SIGN = " ? ";
    public static final String WHERE = " WHERE ";

    public abstract PreparedStatement build(Connection connection);
}
