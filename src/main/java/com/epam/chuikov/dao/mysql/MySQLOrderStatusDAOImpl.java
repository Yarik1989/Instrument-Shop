package com.epam.chuikov.dao.mysql;

import com.epam.chuikov.builder.DeleteSqlBuilder;
import com.epam.chuikov.builder.InsertSqlBuilder;
import com.epam.chuikov.builder.SelectSqlBuilder;
import com.epam.chuikov.builder.UpdateSqlBuilder;
import com.epam.chuikov.constant.Fields;
import com.epam.chuikov.constant.Tables;
import com.epam.chuikov.dao.OrderStatusDAO;
import com.epam.chuikov.dao.exception.DAOException;
import com.epam.chuikov.db.ConnectionPool;
import com.epam.chuikov.entity.OrderStatus;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySQLOrderStatusDAOImpl implements OrderStatusDAO {
    private static final Logger LOG = Logger.getLogger(MySQLOrderStatusDAOImpl.class);
    @Override
    public List<OrderStatus> readAll() {
        ArrayList<OrderStatus> statuses = new ArrayList<>();
        OrderStatus orderStatus;
        ResultSet rs;
        Connection connection = ConnectionPool.get();
        try {
            SelectSqlBuilder builder = new SelectSqlBuilder();
            builder.table(Tables.ORDER_STATUS);
            PreparedStatement pstmt = builder.build(connection);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                orderStatus = extractOrderStatus(rs);
                statuses.add(orderStatus);
            }
        } catch (SQLException ex) {
            LOG.error("Cannot find order's statuses");
            throw new DAOException("Cannot find order's statuses", ex);
        }
        return statuses;
    }

    @Override
    public OrderStatus create(OrderStatus orderStatus) {
        ResultSet rs = null;
        Connection connection = ConnectionPool.get();
        int id = 0;
        InsertSqlBuilder builder = new InsertSqlBuilder();
        builder.table(Tables.ORDER_STATUS).param(Fields.STATUS, orderStatus.getStatus());
        PreparedStatement pstmt = builder.build(connection);
        try {
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
                orderStatus.setId(id);
            }
        } catch (SQLException ex) {
            LOG.error("Cannot create order_status");
            throw new DAOException("Cannot create order_status", ex);
        } finally {
            close(rs);
        }
        if (id > 0) {
            return orderStatus;
        }
        return null;
    }

    @Override
    public OrderStatus read(Integer id) {
        ResultSet rs = null;
        OrderStatus orderStatus = null;
        Connection connection = ConnectionPool.get();
        try {
            SelectSqlBuilder builder = new SelectSqlBuilder();
            builder.table(Tables.ORDER_STATUS).param(Fields.ID, id);
            PreparedStatement pstmt = builder.build(connection);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                orderStatus = extractOrderStatus(rs);
            }
        } catch (SQLException ex) {
            LOG.error("Cannot find status by given id");
            throw new DAOException("Cannot find status by given id", ex);
        } finally {
            close(rs);
        }
        return orderStatus;
    }

    @Override
    public boolean update(OrderStatus orderStatus) {
        if (isNotValid(orderStatus)) {
            return false;
        }
        Connection connection = ConnectionPool.get();
        try {
            UpdateSqlBuilder builder = new UpdateSqlBuilder();
            builder.table(Tables.ORDER_STATUS).param(Fields.STATUS, orderStatus.getStatus());
            builder.setCondition(Fields.ID, orderStatus.getId());
            PreparedStatement pstmt = builder.build(connection);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOG.error("Cannot update order's status by given object");
            throw new DAOException("Cannot update order's status by given object", ex);
        }
    }

    @Override
    public boolean delete(OrderStatus orderStatus) {
        if (isNotValid(orderStatus)) {
            return false;
        }
        Connection connection = ConnectionPool.get();
        int id = orderStatus.getId();
        try {
            DeleteSqlBuilder builder = new DeleteSqlBuilder();
            builder.table(Tables.ORDER_STATUS).param(Fields.ID, id);
            PreparedStatement pstmt = builder.build(connection);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOG.error("Cannot delete order_status by given id");
            throw new DAOException("Cannot delete order_status by given id", ex);
        }
    }

    private OrderStatus extractOrderStatus(final ResultSet rs) throws SQLException {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(rs.getInt(Fields.ID));
        orderStatus.setStatus(rs.getString(Fields.STATUS));
        return orderStatus;
    }

    private void close(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DAOException("Cannot close result set", ex);
            }
        }
    }

    private boolean isNotValid(OrderStatus orderStatus) {
        return orderStatus == null || orderStatus.getId() == null || orderStatus.getId() < 0;
    }
}
