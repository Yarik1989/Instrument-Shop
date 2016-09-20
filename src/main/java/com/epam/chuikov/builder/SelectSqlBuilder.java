package com.epam.chuikov.builder;

import org.apache.log4j.Logger;

import com.epam.chuikov.builder.api.ParamSqlEntity;
import com.epam.chuikov.builder.api.TableSqlEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SelectSqlBuilder extends AbstractSqlBuilder implements TableSqlEntity, ParamSqlEntity{
    public static final Logger LOG = Logger.getLogger(SelectSqlBuilder.class);
    public static final String OR = " OR ";
    public static final String AND = " AND ";
    public static final String LIMIT = " LIMIT ";
    public static final String IN = " IN ";
    public static final String LESS = " <= ";
    public static final String GREATER = " >= ";
    public static final String LIKE = " LIKE ";
    public static final String PERCENT = "%";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String ASC = " ASC ";
    public static final String DESC = " DESC ";
    public static final String SELECT_ALL_FROM = "SELECT * FROM ";
    public static final String SELECT_COUNT_FROM = "SELECT COUNT(*) FROM ";

    private List<Object> paramValues;
    private StringBuilder query;
    private boolean isAndNeed = false;

    public SelectSqlBuilder() {
        paramValues = new ArrayList<>();
        query = new StringBuilder(SELECT_ALL_FROM);
    }

    @Override
    public PreparedStatement build(Connection connection) {
        try {
            String resultQuery = query.toString();
            if(resultQuery.endsWith(AND)){
                resultQuery = resultQuery.substring(0, resultQuery.length() - AND.length());
            }
            PreparedStatement pstmt = connection.prepareStatement(resultQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < paramValues.size(); i++) {
                pstmt.setObject(i + 1, paramValues.get(i));
            }
            return pstmt;
        } catch (SQLException e) {
            LOG.error("Exception occurred while building select prepared statement");
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public ParamSqlEntity param(String paramName, Object param) {
        paramValues.add(param);
        checkWhereCondition();
        query.append(paramName).append(EQUAL).append(QUESTION_SIGN);
        isAndNeed = true;
        return this;
    }

    public ParamSqlEntity in (String paramName, String[] ids){
        checkWhereCondition();
        query.append(paramName).append(IN).append(" (");
        for(String s : ids){
            query.append(s).append(COMA);
        }
        query = new StringBuilder(query.substring(0, query.length() - COMA.length()));
        query.append(")");
        isAndNeed = true;
        return this;
    }

    @Override
    public ParamSqlEntity table(String tableName) {
        query.append(tableName).append(" ");
        return this;
    }

    public ParamSqlEntity or(){
        query.append(OR);
        return this;
    }

    public ParamSqlEntity and(){
        if(isAndNeed){
            query.append(AND);
        }
        isAndNeed = false;
        return this;
    }

    public ParamSqlEntity lessThan(String paramName, Object value){
        checkWhereCondition();
        if(isAndNeed){
            and();
        }
        query.append(paramName).append(LESS).append(value);
        isAndNeed = true;
        return this;
    }

    public ParamSqlEntity greaterThan(String paramName, Object value){
        checkWhereCondition();
        if(isAndNeed){
            and();
        }
        query.append(paramName).append(GREATER).append(value);
        isAndNeed = true;
        return this;
    }

    public ParamSqlEntity orderBy(String paramName, boolean ascending){
        if(query.toString().endsWith(AND)){
            String tempQuery = query.toString();
            query = new StringBuilder(tempQuery.substring(0, tempQuery.length() - AND.length()));
        }
        query.append(ORDER_BY).append(paramName);
        if(ascending){
            query.append(ASC);
        } else {
            query.append(DESC);
        }
        return this;
    }

    public ParamSqlEntity setLimit(int pageNum, int pageSize){
        if(query.toString().endsWith(AND)){
            String tempQuery = query.toString();
            query = new StringBuilder(tempQuery.substring(0, tempQuery.length() - AND.length()));
        }
        query.append(LIMIT).append(pageSize * (pageNum - 1))
                .append(COMA).append(pageSize);
        return this;
    }

    public ParamSqlEntity setAlike(String paramName, String pattern){
        checkWhereCondition();
        StringBuilder sb = new StringBuilder();
        sb.append(PERCENT).append(pattern).append(PERCENT);
        paramValues.add(sb.toString());
        query.append(paramName).append(LIKE).append(QUESTION_SIGN);
        isAndNeed = true;
        return this;
    }

    private void checkWhereCondition(){
        if(!query.toString().contains(WHERE)) {
            query.append(WHERE);
        }
    }

    public void getCount(){
        query = new StringBuilder(SELECT_COUNT_FROM);
    }


}
