package com.epam.chuikov.builder;

import org.apache.log4j.Logger;

import com.epam.chuikov.builder.api.ParamSqlEntity;
import com.epam.chuikov.builder.api.TableSqlEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DeleteSqlBuilder extends AbstractSqlBuilder implements ParamSqlEntity, TableSqlEntity {
    public static final String DELETE_FROM = "DELETE FROM ";
    private static final Logger LOG = Logger.getLogger(DeleteSqlBuilder.class);
    private StringBuilder query;
    private List<String> paramNames;
    private List<Object> params;

    public DeleteSqlBuilder() {
        query = new StringBuilder(DELETE_FROM);
        paramNames = new ArrayList<>();
        params = new ArrayList<>();
    }


    @Override
    public PreparedStatement build(Connection connection) {
        query.append(WHERE);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < paramNames.size(); i++) {
            builder.append(paramNames.get(i)).append(EQUAL).append(" ? ").append(COMA);
        }
        query.append(builder.toString()).deleteCharAt(query.length() - 1);
        try {
            PreparedStatement pstmt = connection.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            return pstmt;
        } catch (SQLException e) {
            LOG.error("Exception occurred while building delete prepared statement");
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public ParamSqlEntity param(String paramName, Object param) {
        if (paramName.isEmpty() || param == null) {
            return this;
        }
        paramNames.add(paramName);
        params.add(param);
        return this;
    }

    @Override
    public ParamSqlEntity table(String tableName) {
        if (tableName.isEmpty()) {
            return this;
        }
        query.append(tableName);
        return this;
    }
}
