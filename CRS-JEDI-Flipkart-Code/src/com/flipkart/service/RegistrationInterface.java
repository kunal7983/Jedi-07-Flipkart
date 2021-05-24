/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;

/**
 * @author adityasuraj
 *
 */
public interface RegistrationInterface {
	
	/**
	 * @param cCode
	 * @param sRollNo
	 * @param courseList
	 * @return boolean if Course added successfully
	 */
	public boolean addCourse(String cCode, int sRollNo, List<Course> courseList);
	
	/**
	 * @param cCode
	 * @param sRollNo
	 * @param registeredCourseList
	 * @return boolean if Course deleted successfully
	 */
	public boolean dropCourse(String cCode, int sRollNo, List<Course> registeredCourseList);
	
	/**
	 * @param sRollNo
	 * @return List of courses
	 */
	public List<Course> viewCourses(int sRollNo);
	
	/**
	 * @param sRollNo
	 * @return List of courses that student has registered in
	 */
	public List<Course> viewRegisteredCourses(int sRollNo);
	
	/**
	 * @param sRollNo
	 * @return List of RegisteredCourse as it has grade and semester
	 */
	public List<RegisteredCourse> viewReportCard(int sRollNo);
	
	/**
	 * @param sRollNo
	 * @return calculated fee
	 */
	public double calculateFee(int sRollNo);
	
	/**
	 * @param sRollNo
	 * @return boolean whether Registration was approved successfully
	 */
	public boolean getRegistrationStatus(int sRollNo);
	
	/**
	 * @param sRollNo
	 */
	public void setRegistrationStatus(int sRollNo);
	
	
}