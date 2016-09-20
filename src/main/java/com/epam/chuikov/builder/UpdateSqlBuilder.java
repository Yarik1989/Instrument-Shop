package com.epam.chuikov.builder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.epam.chuikov.builder.api.ConditionSQLEntity;
import com.epam.chuikov.builder.api.ParamSqlEntity;
import com.epam.chuikov.builder.api.TableSqlEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateSqlBuilder extends AbstractSqlBuilder implements TableSqlEntity, ParamSqlEntity, ConditionSQLEntity {
    public static final Logger LOG = Logger.getLogger(UpdateSqlBuilder.class);
    public static final String UPDATE = "UPDATE ";
    public static final String SET = " SET ";

    private StringBuilder query;
    private List<String> paramNames;
    private List<Object> params;

    private List<String> conditionNames;
    private List<Object> conditionParams;

    public UpdateSqlBuilder() {
        query = new StringBuilder(UPDATE);
        paramNames = new ArrayList<>();
        params = new ArrayList<>();

        conditionNames = new ArrayList<>();
        conditionParams = new ArrayList<>();
    }

    @Override
    public PreparedStatement build(Connection connection) {
        query.append(SET);
        for (String name : paramNames) {
            query.append(name).append(EQUAL).append(QUESTION_SIGN);
            query.append(COMA);
        }
        query.deleteCharAt(query.length() - 1);
        query.append(WHERE);
        for (int i = 0; i < conditionNames.size(); i++) {
            query.append(conditionNames.get(i)).append(EQUAL).append(conditionParams.get(i));
        }
        try {
            PreparedStatement stm = connection.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.size(); i++) {
                stm.setObject(i + 1, params.get(i));
            }
            return stm;
        } catch (SQLException ex) {
            LOG.error("Exception while building update prepared statement");
            throw new IllegalStateException(ex.getMessage());
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
        if (StringUtils.isBlank(tableName)) {
            return this;
        }
        query.append(tableName);
        return this;
    }

    @Override
    public ParamSqlEntity setCondition(String paramName, Object param) {
        if (paramName.isEmpty() || param == null) {
            return this;
        }
        conditionNames.add(paramName);
        conditionParams.add(param);
        return this;
    }
}
