/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Notification;
import com.flipkart.bean.RegisteredCourse;
//import com.flipkart.constant.Grade;
//import com.flipkart.constant.ModeOfPayment;
//import com.flipkart.constant.NotificationType;
import com.flipkart.constants.SQLQueriesConstanst;
import com.flipkart.exception.RegisteredCourseLimitExceeded;
import com.flipkart.exception.CourseNotOfferedException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.utils.DBUtil;

/**
 * @author JEDI-7
 * Class to implement Registration Dao Operations
 * This class communicates with the database.
 */
public class RegistrationDaoOperation implements RegistrationDaoInterface{
	
	private static volatile RegistrationDaoOperation instance=null;
	private PreparedStatement stmt = null;
	
	/**
	 * Default Constructor
	 */
	private RegistrationDaoOperation() {
		
	}
	
	/**
	 * Method to make RegistrationDaoOperation Singleton
	 * @return singleton instance
	 */
	public static RegistrationDaoOperation getInstance()
	{
		if(instance==null)
		{
			synchronized(RegistrationDaoOperation.class){
				instance=new RegistrationDaoOperation();
			}
		}
		return instance;
	}

	/**
	 * DONE
	 * Method to add course in database
	 * @param courseCode
	 * @param studentId
	 * @return boolean whether course got added successfully
	 * @throws SQLException
	 */
	@Override
	public boolean addCourse( String studentId,int semester, String courseCode) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstanst.ADD_COURSE);
			stmt.setString(1, studentId);
			stmt.setInt(2, semester);
			stmt.setString(3, courseCode);

			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SQLQueriesConstanst.DECREMENT_COURSE_SEATS);
			stmt.setString(1, courseCode);
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
//			conn.close();
		}
		return false;

	}

	/**
	 * 
	 * Drop Course selected by student
	 * @param courseCode
	 * @param studentId
	 * @param semester
	 * @return boolean whether course got added successfully
	 * @throws SQLException
	 */
	@Override
	public boolean dropCourse(String studentId,int semester, String courseCode) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		
		
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstanst.DROP_COURSE_QUERY);
			stmt.setString(1, courseCode);
			stmt.setString(2, studentId);
			stmt.setInt(3, semester);
			stmt.execute();
			
			stmt = conn.prepareStatement(SQLQueriesConstanst.INCREMENT_SEAT_QUERY);
			stmt.setString(1, courseCode);
			stmt.execute();
			
			stmt.close();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception found" + e.getMessage());
		}
		finally
		{

			stmt.close();
//			conn.close();
		}
		
		return false;
	}

	@Override
	public List<Course> viewCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		List<Course> availableCourseList = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		
		try 
		{
//			stmt = conn.prepareStatement(SQLQueriesConstanst.VIEW_AVAILABLE_COURSES);
//			stmt.setInt(1, studentId);
//			stmt.setBoolean(2, true);
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				availableCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
//						rs.getString("professorId"), rs.getInt("seats")));
//
//			}
			

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
//			conn.close();
		}
		
		return availableCourseList;
	}

	
	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisteredCourse> viewReportCard(String studentId,int semester) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		List<RegisteredCourse> grade_List = new ArrayList<>();
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstanst.VIEW_GRADE);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String cCode = rs.getString("cCode");
				String studId = rs.getString("studentId");
				String courseName = rs.getString("cName");
				Grade grade = new Grade(rs.getString("grade"));
				RegisteredCourse obj = new RegisteredCourse(cCode, studId, semester, grade);
				grade_List.add(obj);
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
//			conn.close();
			
		}
		
		return grade_List;
	}

	@Override
	public double calculateFee(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean seatAvailable(String courseCode) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int numOfRegisteredCourses(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isRegistered(String courseCode, String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRegistrationStatus(String studentId) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

	
	
}
