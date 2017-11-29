package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

import DAO.EmployeeDAO;
import DO.Employee;
import utils.LoginUtils;

public class tester {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Boolean clockedIn = false;
		
		LocalDateTime localTime = LocalDateTime.now();
		long clockInTime = 0;
		long clockOutTime = 0;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");
		
		System.out.println("Clocked in on " + LocalDateTime.now().format(format));	
		System.out.println("Clocked in on " + LocalDateTime.now().format(format));
		System.out.println("Clocked in on " + LocalDateTime.now().format(format));
		System.out.println("Clocked in on " + LocalDateTime.now().format(format));
		System.out.println("Clocked in on " + LocalDateTime.now().format(format));
		System.out.println("Clocked in on " + LocalDateTime.now().format(format));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Clocked in on " + LocalDateTime.now().format(format));
		System.out.println("Clocked in on " + LocalDateTime.now().format(format));
		
	}
}
