/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.constants.SQLQueriesConstanst;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtil;

/**
 * @author prafu
 *
 */
public class UserDAOImpl implements UserDAOInterface{

	@Override
	public boolean verifyCredentials(String userName, String password) throws UserNotFoundException {
		Connection connection = DBUtil.getConnection();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstanst.VERIFY_CREDENTIALS);
			
			preparedStatement.setString(1,userName);
			preparedStatement.setString(2,password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				System.out.println("Current user is -> " + resultSet.getString("userName"));
				return true;
			}
			else{
				throw new UserNotFoundException(userName);
			}
			
		}
		catch(SQLException ex){
			System.out.println("SQL Exception Thrown : "+ ex.getMessage());
		}
		return false;
	}

	@Override
	public String getUserRole(String userName) throws UserNotFoundException {
		Connection connection = DBUtil.getConnection();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstanst.GET_USER_ROLE);
			
			preparedStatement.setString(1,userName);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				String role = resultSet.getString("role");
				
				System.out.println("User Role is -> " + role);
				return role;
			}
			else{
				throw new UserNotFoundException(userName);
			}
			
		}
		catch(SQLException ex){
			System.out.println("SQL Exception Thrown : "+ ex.getMessage());
		}
		
		return null;
	}

	@Override
	public boolean updatePassword(String userName, String newPassword) throws UserNotFoundException {
		Connection connection = DBUtil.getConnection();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstanst.UPDATE_USER_PASSWORD);
			
			preparedStatement.setString(1,newPassword);
			preparedStatement.setString(2,userName);
			
			int rows = preparedStatement.executeUpdate();
			
			if(rows > 0) {
				System.out.println("Password updated for user -> " + userName);
				return true;
			}
			else{
				throw new UserNotFoundException(userName);
			}
			
		}
		catch(SQLException ex){
			System.out.println("SQL Exception Thrown : "+ ex.getMessage());
		}
		return false;
	}
	
	

}
