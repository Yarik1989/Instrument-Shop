package com.epam.chuikov.dao.mysql;


import com.epam.chuikov.builder.DeleteSqlBuilder;
import com.epam.chuikov.builder.InsertSqlBuilder;
import com.epam.chuikov.builder.SelectSqlBuilder;
import com.epam.chuikov.builder.UpdateSqlBuilder;
import com.epam.chuikov.constant.Fields;
import com.epam.chuikov.constant.Tables;
import com.epam.chuikov.dao.OrderDAO;
import com.epam.chuikov.dao.OrderStatusDAO;
import com.epam.chuikov.dao.UserDao;
import com.epam.chuikov.dao.UserDaoForCart;
import com.epam.chuikov.dao.exception.DAOException;
import com.epam.chuikov.db.ConnectionPool;
import com.epam.chuikov.entity.Order;
import com.epam.chuikov.entity.OrderStatus;
import com.epam.chuikov.entity.User;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDAOImpl implements OrderDAO {
    private static final Logger LOG = Logger.getLogger(MySQLOrderDAOImpl.class);
    private OrderStatusDAO orderStatusDAO;
    private UserDaoForCart userDAO;


    public MySQLOrderDAOImpl(OrderStatusDAO orderStatusDAO, UserDaoForCart userDAO) {
        this.orderStatusDAO = orderStatusDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Order create(Order order) {
        ResultSet rs = null;
        Connection connection = ConnectionPool.get();
        int id = 0;
        InsertSqlBuilder builder = new InsertSqlBuilder();
        builder.table(Tables.ORDER)
                .param(Fields.DATE, order.getDate())
                .param(Fields.STATUS_ID, order.getStatus().getId())
                .param(Fields.DETAILS, order.getDetails())
                .param(Fields.USER_ID, order.getUser().getId())
                .param(Fields.PAY_WAY, order.getPayWay())
                .param(Fields.DELIVERY_ADDRESS, order.getDeliveryAddress());
        PreparedStatement pstmt = builder.build(connection);
        try {
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
                order.setId(id);
            }
        } catch (SQLException ex) {
            LOG.error("Cannot create order");
            throw new DAOException("Cannot create order", ex);
        } finally {
            close(rs);
        }
        if (id > 0) {
            return order;
        }
        return null;
    }

    @Override
    public Order read(Integer id) {
        ResultSet rs = null;
        Order order = null;
        Connection connection = ConnectionPool.get();
        try {
            SelectSqlBuilder builder = new SelectSqlBuilder();
            builder.table(Tables.ORDER).param(Fields.USER_ID, id);
            PreparedStatement pstmt = builder.build(connection);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = extractOrder(rs);
            }
        } catch (SQLException ex) {
            LOG.error("Cannot find order by given id");
            throw new DAOException("Cannot find order by given id", ex);
        } finally {
            close(rs);
        }
        return order;
    }

    @Override
    public boolean update(Order order) {
        if (isNotValid(order)) {
            return false;
        }
        Connection connection = ConnectionPool.get();
        try {
            UpdateSqlBuilder builder = new UpdateSqlBuilder();
            builder.table(Tables.ORDER)
                    .param(Fields.DATE, order.getDate())
                    .param(Fields.STATUS_ID, order.getStatus().getId())
                    .param(Fields.DETAILS, order.getDetails())
                    .param(Fields.USER_ID, order.getUser().getId())
                    .param(Fields.PAY_WAY, order.getPayWay())
                    .param(Fields.DELIVERY_ADDRESS, order.getDeliveryAddress());
            builder.setCondition(Fields.ID, order.getId());
            PreparedStatement pstmt = builder.build(connection);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOG.error("Cannot update order by given object");
            throw new DAOException("Cannot update order by given object", ex);
        }
    }

    @Override
    public boolean delete(Order order) {
        if (isNotValid(order)) {
            return false;
        }
        Connection connection = ConnectionPool.get();
        int id = order.getId();
        try {
            DeleteSqlBuilder builder = new DeleteSqlBuilder();
            builder.table(Tables.ORDER).param(Fields.ID, id);
            PreparedStatement pstmt = builder.build(connection);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DAOException("Cannot delete order by given id", ex);
        }
    }

    @Override
    public List<Order> readAll(Integer user_id) {
        ArrayList<Order> orders = new ArrayList<>();
        Order order;
        ResultSet rs;
        Connection connection = ConnectionPool.get();
        try {
            SelectSqlBuilder builder = new SelectSqlBuilder();
            builder.table(Tables.ORDER).param(Fields.USER_ID, user_id);
            PreparedStatement pstmt = builder.build(connection);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                order = extractOrder(rs);
                orders.add(order);
            }
        } catch (SQLException ex) {
            LOG.error("Cannot find categories");
            throw new DAOException("Cannot find categories", ex);
        }
        return orders;
    }

    private boolean isNotValid(Order order) {
        return order == null || order.getId() == null || order.getId() < 0;
    }

    private Order extractOrder(final ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt(Fields.ID));
        order.setDate(rs.getDate(Fields.DATE));
        OrderStatus status = orderStatusDAO.read(rs.getInt(Fields.STATUS_ID));
        order.setStatus(status);
        User user = userDAO.read(rs.getInt(Fields.USER_ID));
        order.setUser(user);
        order.setDetails(rs.getString(Fields.DETAILS));
        order.setPayWay(rs.getString(Fields.PAY_WAY));
        order.setDeliveryAddress(rs.getString(Fields.DELIVERY_ADDRESS));
        return order;
    }

    private void close(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close result set");
                throw new DAOException("Cannot close result set", ex);
            }
        }
    }
}
