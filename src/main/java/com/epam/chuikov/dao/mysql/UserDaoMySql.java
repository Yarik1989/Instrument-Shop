package com.epam.chuikov.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.chuikov.dao.UserDao;
import com.epam.chuikov.dao.exception.DAOException;
import com.epam.chuikov.entity.User;

public class UserDaoMySql implements UserDao {

	private static final Logger LOGGERMYSQLUSERDAO = Logger.getLogger(UserDaoMySql.class);
	private static final String SQL_REGISTRATION_OF_USER = "INSERT INTO `users`( `firstName`,  `lastName`, `email`,"
			+ "`password`, `subscribe`, `photoPath`) VALUES (?,?,?,?,?,?)";
	private static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM instrumentshop.users where email = ?";

	@Override
	public User getUserByEmail(Connection con, String email) {
		User user = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_GET_USER_BY_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			rs.close();
		} catch (SQLException ex) {
			LOGGERMYSQLUSERDAO.error("Users with this email already exsists.", ex);
			throw new DAOException("Users with this email already exsists.", ex);
		}
		return user;
	}

	@Override
	public boolean create(Connection con, User user) {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int generatedId = 0;

		try {
			pstmt = con.prepareStatement(SQL_REGISTRATION_OF_USER, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setString(k++, user.getFirstName());
			pstmt.setString(k++, user.getLastName());
			pstmt.setString(k++, user.getEmail());
			pstmt.setString(k++, user.getPassword());
			pstmt.setBoolean(k++, user.isSubscribe());
			pstmt.setString(k++, user.getPhotoPath());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int id = (int) rs.getLong(1);
					user.setId(id);
					result = true;
				}
			}

		} catch (SQLException ex) {
			LOGGERMYSQLUSERDAO.error("Cannot create a user", ex);
			throw new DAOException("Cannot create a user", ex);
		}
		return result;
	}

	@Override
	public boolean update(Connection con, User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Connection con, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getById(Connection con, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setSubscribe(rs.getBoolean("subscribe"));
		user.setPhotoPath(rs.getString("photoPath"));
		return user;
	}

}
