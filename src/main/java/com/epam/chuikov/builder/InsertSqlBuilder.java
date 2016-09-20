package com.epam.chuikov.builder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.epam.chuikov.builder.api.ParamSqlEntity;
import com.epam.chuikov.builder.api.TableSqlEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class InsertSqlBuilder extends AbstractSqlBuilder implements TableSqlEntity, ParamSqlEntity {
    public static final Logger LOG = Logger.getLogger(InsertSqlBuilder.class);
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String VALUES = ") VALUES (";
    public static final String OPEN_SCOPE = " (";
    public static final String CLOSE_SCOPE = ")";

    private StringBuilder query;
    private List<String> paramNames;
    private List<Object> params;

    public InsertSqlBuilder() {
        query = new StringBuilder(INSERT_INTO);
        paramNames = new ArrayList<>();
        params = new ArrayList<>();
    }

    public ParamSqlEntity table(String table) {
        if (StringUtils.isBlank(table)) {
            return this;
        }
        query.append("`").append(table).append("`");
        return this;
    }

    public ParamSqlEntity param(String paramName, Object param) {
        if (StringUtils.isBlank(paramName)) {
            return this;
        }
        paramNames.add(paramName);
        params.add(param);
        return this;
    }

    @Override
    public PreparedStatement build(Connection connection) {
        query.append(OPEN_SCOPE);
        StringBuilder paramsString = new StringBuilder();
        for (String paramName : paramNames) {
            query.append(paramName).append(COMA);
            paramsString.append(QUESTION_SIGN).append(COMA);
        }
        paramsString.deleteCharAt(paramsString.length() - 1);
        query.deleteCharAt(query.length() - 1)
                .append(VALUES)
                .append(paramsString)
                .append(CLOSE_SCOPE);

        try {
            PreparedStatement stm = connection.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.size(); i++) {
                stm.setObject(i + 1, params.get(i));
            }
            return stm;
        } catch (SQLException ex) {
            LOG.error("Exception occurred while building insert sql statement");
            throw new IllegalStateException(ex.getMessage());
        }
    }
}
