package com.epam.chuikov.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.chuikov.builder.DeleteSqlBuilder;
import com.epam.chuikov.builder.InsertSqlBuilder;
import com.epam.chuikov.builder.SelectSqlBuilder;
import com.epam.chuikov.builder.UpdateSqlBuilder;
import com.epam.chuikov.constant.Fields;
import com.epam.chuikov.constant.Tables;
import com.epam.chuikov.dao.OrderDAO;
import com.epam.chuikov.dao.ProductDao;
import com.epam.chuikov.dao.ProductOrderDAO;
import com.epam.chuikov.dao.ProductsDAO;
import com.epam.chuikov.dao.exception.DAOException;
import com.epam.chuikov.db.ConnectionPool;
import com.epam.chuikov.entity.ProductOrder;

public class MySQLGoodOrderDAOImpl implements ProductOrderDAO {
    private static final Logger LOG = Logger.getLogger(MySQLGoodOrderDAOImpl.class);
    private ProductsDAO productsDao;
    private OrderDAO orderDAO;
   
    public MySQLGoodOrderDAOImpl(ProductsDAO productDao, OrderDAO orderDAO) {
        this.productsDao = productDao;
        this.orderDAO = orderDAO;
    }

    @Override
    public ProductOrder read(Integer product_id, Integer order_id) {
        ResultSet rs = null;
        ProductOrder goodOrder = null;
        Connection connection = ConnectionPool.get();
        try {
            SelectSqlBuilder builder = new SelectSqlBuilder();
            builder.table(Tables.PRODUCT_ORDER)
                    .param(Fields.PRODUCT_ID, product_id)
                    .param(Fields.ORDER_ID, order_id);
            PreparedStatement pstmt = builder.build(connection);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                goodOrder = extractGoodOrder(rs);
            }
        } catch (SQLException ex) {
            LOG.error("Cannot find good_order by given id");
            throw new DAOException("Cannot find product_order by given id", ex);
        } finally {
            close(rs);
        }
        return goodOrder;
    }

    @Override
    public ProductOrder create(ProductOrder productOrder) {
        ResultSet rs = null;
        Connection connection = ConnectionPool.get();
        int id = 0;
        InsertSqlBuilder builder = new InsertSqlBuilder();
        builder.table(Tables.PRODUCT_ORDER)
                .param(Fields.PRODUCT_ID, productOrder.getProduct().getId())
                .param(Fields.ORDER_ID, productOrder.getOrder().getId())
                .param(Fields.AMOUNT, productOrder.getAmount())
                .param(Fields.FACT_PRICE, productOrder.getFactPrice());
        PreparedStatement pstmt = builder.build(connection);
        try {
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
                productOrder.setId(id);
            }
        } catch (SQLException ex) {
            LOG.error("Cannot create GoodOrder");
            throw new DAOException("Cannot create GoodOrder", ex);
        } finally {
            close(rs);
        }
        if (id > 0) {
            return productOrder;
        }
        return null;
    }

    @Override
    public ProductOrder read(Integer integer) {
        throw new UnsupportedOperationException("Temporary unavailable operation");
    }

    @Override
    public boolean update(ProductOrder goodOrder) {
        if (isNotValid(goodOrder)) {
            return false;
        }
        Connection connection = ConnectionPool.get();
        try {
            UpdateSqlBuilder builder = new UpdateSqlBuilder();
            builder.table(Tables.PRODUCT_ORDER)
                    .param(Fields.PRODUCT_ID, goodOrder.getProduct().getId())
                    .param(Fields.ORDER_ID, goodOrder.getOrder().getId())
                    .param(Fields.AMOUNT, goodOrder.getAmount())
                    .param(Fields.FACT_PRICE, goodOrder.getFactPrice());
            builder.setCondition(Fields.ID, goodOrder.getId());
            PreparedStatement pstmt = builder.build(connection);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOG.error("Cannot update good_order by given object");
            throw new DAOException("Cannot update product_order by given object", ex);
        }
    }

    @Override
    public boolean delete(ProductOrder goodOrder) {
        if (isNotValid(goodOrder)) {
            return false;
        }
        Connection connection = ConnectionPool.get();
        int id = goodOrder.getId();
        try {
            DeleteSqlBuilder builder = new DeleteSqlBuilder();
            builder.table(Tables.PRODUCT_ORDER).param(Fields.ID, id);
            PreparedStatement pstmt = builder.build(connection);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DAOException("Cannot delete order by given id", ex);
        }
    }

    private boolean isNotValid(ProductOrder productOrder) {
        return productOrder == null || productOrder.getId() == null || productOrder.getId() < 0;
    }

    private ProductOrder extractGoodOrder(final ResultSet rs) throws SQLException {
        ProductOrder productOrder = new ProductOrder(productsDao.read(rs.getInt(Fields.PRODUCT_ID)),
                orderDAO.read(rs.getInt(Fields.ORDER_ID)), rs.getInt(Fields.AMOUNT),
                rs.getDouble(Fields.FACT_PRICE));
        productOrder.setId(rs.getInt(Fields.ID));
        return productOrder;
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
