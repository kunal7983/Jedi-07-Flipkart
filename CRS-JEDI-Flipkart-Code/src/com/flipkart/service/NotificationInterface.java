package com.flipkart.service;

import java.util.UUID;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;


/*
 * @author Harshwardhan Koushik
 * */

public interface NotificationInterface {
	
	/**
	 * Method to send notification
	 * @param referenceId: Payment Id
	 * @param studentId: student to be notified
	 * @param message: Message to be sent
	 * @return notification id for the record added in the database
	 */
	public String sendNotification(String referenceId,int studentId,String message);
	
	/**
	 * Method to return UUID for a transaction
	 * @param id: notification id added in the database
	 * @return transaction id of the payment
	 */
	public UUID getReferenceId(String id); 
}