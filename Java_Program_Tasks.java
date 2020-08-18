//Importing the necessary Packages
import java.util.Scanner;
import java.sql.Connection; 
import java.sql.Statement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.DriverManager;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.InputStreamReader;
import java.io.IOException;

public class sample {
	public static void main(String[] args) throws SQLException { 
		// Connect to database 
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		final String hostName = "kann0002-sql-server.database.windows.net"; 
		final String dbName = "cs-dsa-4513-sql-db"; 
		final String user = "kann0002"; 
		final String password = "*****"; 
		final String url =
String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;host "
+ "NameInCertificate=*.database.windows.net;loginTimeout=30;",
		hostName, dbName, user, password);
	
		try (final Connection connection = DriverManager.getConnection(url)) {
			final String schema = connection.getSchema(); 
			System.out.println("Successful connection - Schema:" + schema);  
			System.out.println("========================================="); 
			
			

			//Scanner myScan = new Scanner(System.in);
			int Choice = 0;
			while(Choice != 18) 
			{
			
			// printing out the available choices for the User to choose.
			// It is assumed that user will have prior knowledge of the problem	
			System.out.println("You have the Following Options to Choose:");
			System.out.println("1. Enter a new Customer (Option 1)"); 
			System.out.println("2. Enter a new Department (Option 2)"); 
			System.out.println("3. Enter a new assembly (Option 3)"); 
			System.out.println("4. Enter a new process and information related to type (Option 4)");
			System.out.println("5. Create a new account and associate it with the one to which it is applicable (Option 5)");
			System.out.println("6. Enter a new Job (Option 6)");
			System.out.println("7. Enter date job is completed and information related to type of job (Option 7)");
			System.out.println("8. Enter a transaction details and update all of the affected accounts (Option 8)");
			System.out.println("9. Retrieve the cost incurred on an assembly-id (Option 9)");
			System.out.println("10. Retrieve the total labor time within a department for jobs completed during a given date (Option 10)");
			System.out.println("11. Retrieve the processes through which a given assembly-id has passed so far and the department responsible for the process (Option 11)");
			System.out.println("12. Retrieve all jobs completed during the given date in a given department (Option 12)");
			System.out.println("13. Retrieve the customers whose category is in a given range (Option 13)");
			System.out.println("14. Delete all cut-jobs whose job-no is in a given range (Option 14)");
			System.out.println("15. Change the color of a given paint job (Option 15)");
			System.out.println("16. Import: enter new customers from a data file until the file is empty (Option 16).");
			System.out.println("17. Export: Retrieve the customers whose category is in a given range and output them to a data file(Option 17).");
			System.out.println("18. QUIT (Option 18)"); 
			System.out.println("=========================================");
			
			 
			// initializing Scanner object
			Scanner myScan = new Scanner(System.in);
			//reading the input given by the user
			Choice = myScan.nextInt();
			
			//Depending on the choice made by the user, we are defining the operations to be done
			
			switch (Choice) {
			
				case 1:
					// try and catch are used to not terminate loop in case of error.
					try {
						
					//Declaring the variables
					int category;
					String cname, caddress;
					
					//Taking customer name from user
					System.out.println("Enter the Customer Name");
					cname = myScan.next();
					
					// Taking Customer Address from the user
					System.out.println("Enter the Customer Address"); 
					caddress = myScan.next();
					
					// Taking customer category from the user
					System.out.println("Enter the Customer Category"); 
					category = myScan.nextInt();
					
					// Executing procedure for Query 1.
					final String Sql1 = "EXEC proc_1 @cname = '"+cname+"', @caddress = '"+caddress+"', @category = '"+category+"';";
					
					final Statement statement1 = connection.createStatement(); 
						statement1.executeUpdate(Sql1);
				
						System.out.println("Customer record inserted successfully.");
						System.out.println("=========================================");
					} catch (Exception e) {
						System.out.println("You got an error!. Returning to main menu");
					}
					break;
					
				case 2:
					// try and catch are used to not terminate loop in case of error.
					try {
						
					// Declaring variables
					String dept_no, dept_data;
					
					// Taking Department Number from user.
					System.out.println("Enter the Department Number\n");
					dept_no = myScan.next();
					
					// Taking Department data from user.
					System.out.println("Enter the Department Data\n");
					dept_data = myScan.next();
					
					// Executing Procedure for Query 2.
					final String Sql2 = "EXEC proc_2 @dept_no = '"+dept_no+"', @dept_data = '"+dept_data+"';";
					
					final Statement statement2 = connection.createStatement(); 
						statement2.executeUpdate(Sql2);
				
						System.out.println("Department record inserted successfully.");
						System.out.println("=========================================");
					} catch (Exception e) {
						System.out.println("You got an error!. Returning to main menu");
					}
					break;
					
				case 3:
					// try and catch are used to not terminate loop in case of error.
					try {
						// Declaring Variables
					String assembly_id, assembly_details, cname1;
					String date_ordered;
					
					// Taking Assembly-id from user
					System.out.println("Enter Assembly ID");
					assembly_id = myScan.next();
					
					// Taking date ordered from user
					System.out.println("Enter Date Ordered in yyyy-mm-dd format");
					date_ordered = myScan.next();
					
					// Taking Assembly details from user
					System.out.println("Enter Assembly Details");
					assembly_details = myScan.next();
					
					// Taking customer name from user
					System.out.println("Enter the Customer Name");
					cname1 = myScan.next();
					
					// Executing procedure for Query 3
					final String Sql3 = "EXEC proc_3 @assembly_id = '"+assembly_id+"', @data_ordered = '"+date_ordered+"'," + 
								"@assembly_details = '"+assembly_details+"', @cname1 = '"+cname1+"';";
					
					final Statement statement3 = connection.createStatement(); 
						statement3.executeUpdate(Sql3);
				
						System.out.println("Assembly record inserted successfully.");
						System.out.println("=========================================");
						
					} catch (Exception e) {
						System.out.println("You got an error!. Returning to main menu");
					}
					break;
					
				case 4: 
					// try and catch are used to not terminate loop in case of error.
					try {
						
					// Declaring Variables
					String process_id, process_data, dept_no1;
					
					// Taking process-id from the user
					System.out.println("Enter Process ID");
					process_id = myScan.next();
					
					// Taking process data from the user
					System.out.println("Enter Process Data");
					process_data = myScan.next();
					
					// Taking Department-no from the user
					System.out.println("Enter Department No");
					dept_no1 = myScan.next();
					
					//Executing Procedure for Query 4
					final String Sql4 = "EXEC proc_4 @process_id = '"+process_id+"'," + 
							" @process_data = '"+process_data+"', @dept_no1 = '"+dept_no1+"';";
					
					final Statement statement4 = connection.createStatement(); 
						statement4.executeUpdate(Sql4);
				
						System.out.println("Process record inserted successfully.");
						System.out.println("=========================================");
					
					// Asking user to enter the type of procedure
					System.out.println("Choose one of the following type of process:\n 1.Fit\n 2. Paint\n 3.Cut\n");
					int choice1;
					// Taking the type of process from the user
					choice1 = myScan.nextInt();
					
					if (choice1 ==1) {
						
						// Declaring Variables
						String fit_type;
						
						// Taking fit type from user
						System.out.println("Enter fit type\n");
						fit_type = myScan.next();
						
						// Executing Procedure to insert data in Process_fit table
						final String Sql4_1 = "EXEC proc_4_1 @process_id = '"+process_id+"',  @fit_type = '"+fit_type+"';";

						final Statement statement4_1 = connection.createStatement();
							statement4_1.executeUpdate(Sql4_1);
							
							System.out.println("Process_fit record inserted successfully.");
							System.out.println("=========================================");
					}
					
					if (choice1 ==2) {
						
						// Declaring Variables
						String paint_type, paint_method;
						
						//Taking paint type from the user
						System.out.println("Enter paint type");
						paint_type = myScan.next();
						
						// Taking paint method from the user
						System.out.println("Enter paint method");
						paint_method = myScan.next();
						
						// Executing procedure to insert data into Process_paint table
						final String Sql4_2 = "EXEC proc_4_2 @process_id = '"+process_id+"', " +
								" @paint_type = '"+paint_type+"', @paint_method = '"+paint_method+"';";

						final Statement statement4_2 = connection.createStatement();
							statement4_2.executeUpdate(Sql4_2);
							
							System.out.println("Process_paint record inserted successfully.");
							System.out.println("=========================================");
					}
					
					if (choice1 ==3) {
						
						// Declaring Variables
						String cutting_type, machine_type;
						
						//Taking cut type from the user
						System.out.println("Enter cutting type");
						cutting_type = myScan.next();
						
						// Take machine type from user
						System.out.println("Enter machine type");
						machine_type = myScan.next();
						
						//Executing Procedure to insert data into Process_cut table.
						final String Sql4_3 = "EXEC proc_4_3 @process_id = '"+process_id+"', " + 
								" @cutting_type = '"+cutting_type+"', @machine_type = '"+machine_type+"';";

						final Statement statement4_3 = connection.createStatement();
							statement4_3.executeUpdate(Sql4_3);
							
							System.out.println("Process_cut record inserted successfully.");
							System.out.println("=========================================");
					}
					} catch (Exception e) {
						System.out.println("You got an error!. Returning to main menu");
					}
					break;
					
				case 5: 
					// try and catch are used to not terminate loop in case of error.
					try {
					
						// Declaring Variables
					int acc_no, choice2;
					String date_established;
					
					// Taking account number from user
					System.out.println("Enter account number");
					acc_no = myScan.nextInt();
					
					// Taking date account established from user
					System.out.println("Enter date account established");
					date_established = myScan.next();
					
					// Asking user to provide the tye of account
					System.out.println("Choose one of the following type of account:\n 1. Department Account.\n 2. Assembly Account\n 3.Process Account\n");
					choice2 = myScan.nextInt();
					
					
					if (choice2 == 1) {
						// Declaring Variables
						float details2;
						String dept_no2;
						
						// Taking details-2 from the user
						System.out.println("Enter account details");
						details2 = myScan.nextFloat();
						
						// Taking department no from the user
						System.out.println("Enter Department Number of the account");
						dept_no2 = myScan.next();
						
						// Executing procedure to insert data into Department Account Table
						final String Sql5_1 = "EXEC proc_5_1 @acc_no = '"+acc_no+"',  @date_established = '"+date_established+"',"+
								" @details_2 = '"+details2+"', @dept_no = '"+dept_no2+"';";

						final Statement statement5_1 = connection.createStatement();
							statement5_1.executeUpdate(Sql5_1);
							
							System.out.println("Dept_acc record inserted successfully.");
							System.out.println("=========================================");
					}
					
					if (choice2 == 2) {
						
						// Declaring Variables
						float details1;
						String assembly_id1;
						
						// Taking details1 from user
						System.out.println("Enter account details");
						details1 = myScan.nextFloat();
						
						// Taking assembly-id from user
						System.out.println("Enter Assembly id of the account");
						assembly_id1 = myScan.next();
						
						// Executing procedure to insert data into Assembly account
						final String Sql5_2 = "EXEC proc_5_2 @acc_no = '"+acc_no+"',  @date_established = '"+date_established+"',"+
								" @details_1 = '"+details1+"',  @assembly_id = '"+assembly_id1+"';";

						final Statement statement5_2 = connection.createStatement();
							statement5_2.executeUpdate(Sql5_2);
							
							System.out.println("Assembly_acc record inserted successfully.");
							System.out.println("=========================================");
					}
					
					if (choice2 == 3) {
						
						// Declaring Variables
						float details3;
						String process_id1;
						
						// Taking details from the user
						System.out.println("Enter account details");
						details3 = myScan.nextFloat();
						
						// Taking process-id from the user
						System.out.println("Enter Process id of the account");
						process_id1 = myScan.next();
						
						// Executing the procedure to insert data into Process account
						final String Sql5_3 = "EXEC proc_5_3 @acc_no = '"+acc_no+"',  @date_established = '"+date_established+"',"+
								" @details_3 = '"+details3+"',  @process_id = '"+process_id1+"';";

						final Statement statement5_3 = connection.createStatement();
							statement5_3.executeUpdate(Sql5_3);
							
							System.out.println("Process_acc record inserted successfully.");
							System.out.println("=========================================");
					}
					} catch (Exception e) {
						System.out.println("You got an error!. Returning to main menu");
					}
					break;
					
				case 6:
					// try and catch are used to not terminate loop in case of error.
					try {
						
						// Declaring Variables
					String date_commenced, assembly_id2, process_id2;
					int job_no;
					
					// Taking Job-no from the user
					System.out.println("Enter Job-no for a new job");
					job_no = myScan.nextInt();
					
					// Taking assembly-id from the user
					System.out.println("Enter assembly-id for the job");
					assembly_id2 = myScan.next();
					
					// Taking process-id from user
					System.out.println("Enter process-id for the job");
					process_id2 = myScan.next();
					
					// Taking job commenced from user
					System.out.println("Enter date commenced for the job");
					date_commenced = myScan.next();
					
					// Executing Procedure for Query 6
					final String Sql6 = "EXEC proc_6 @job_no = '"+job_no+"', @date_commenced = '"+date_commenced+"',"+
							" @assembly_id = '"+assembly_id2+"', @process_id = '"+process_id2+"';";
					
					final Statement statement6 = connection.createStatement(); 
						statement6.executeUpdate(Sql6);
				
						System.out.println("Job record inserted successfully.");
						System.out.println("=========================================");
						
					} catch (Exception e) {
						System.out.println("You got an error!. Returning to main menu");
					}
					break;
					
				case 7:
					// try and catch are used to not terminate loop in case of error.
					try {
						// Declaring Variables
					String date_completed;
					int job_no1;
					
					// Taking Job-no from user
					System.out.println("Enter Job-no for for the completed job");
					job_no1 = myScan.nextInt();
					
					// Taking date completed from user
					System.out.println("Enter job completed date in yyyy-mm-dd format");
					date_completed = myScan.next();
					
					// Executing the Procedure for Query 7
					final String Sql7 = "EXEC proc_7 @job_no = '"+job_no1+"', @date_completed = '"+date_completed+"';";
					
					final Statement statement7 = connection.createStatement(); 
						statement7.executeUpdate(Sql7);
				
						System.out.println("Job record Updated successfully.");
						System.out.println("=========================================");
					
						// Declaring Variables
					int choice3;
					
					// Asking the type of job from the user
					System.out.println("Choose one of the following type of job:\n 1. Fit Job.\n 2. Paint Job\n 3.Cut Job\n");
					choice3 = myScan.nextInt();
					
					
					if (choice3 == 1) {
					  	
						// Declaring Variables
						String fit_labor_time;
						
						// Taking fit labor time from user
						System.out.println("Enter the fit job labor time in HH:MM:SS format.");
						fit_labor_time = myScan.next();
						
						// Executing procedure to insert data into Job_fit table
						final String Sql7_1 = "EXEC proc_7_1 @job_no = '"+job_no1+"', @fit_labor_time = '"+fit_labor_time+"';";
						
						final Statement statement7_1 = connection.createStatement(); 
							statement7_1.executeUpdate(Sql7_1);
							
							System.out.println("Fit Job record inserted successfully.");
							System.out.println("=========================================");
					}
					
					if (choice3 == 2) {
						
						// Declaring Variables
						String color, paint_labor_time;
						int volume;
						
						// Taking color from user
						System.out.println("Enter the paint color.");
						color = myScan.next();
						
						// Taking labor time from user
						System.out.println("Enter the paint job labor time in HH:MM:SS format.");
						paint_labor_time = myScan.next();
						
						// Taking volumme of paint from user
						System.out.println("Enter the volume of paint.");
						volume = myScan.nextInt();
						
						// Executing the procedure to insert data into Job paint table
						final String Sql7_2 = "EXEC proc_7_2 @job_no = '"+job_no1+"', @color = '"+color+"', "+
								" @volume = '"+volume+"', @paint_labor_time = '"+paint_labor_time+"';";
						
						final Statement statement7_2 = connection.createStatement(); 
							statement7_2.executeUpdate(Sql7_2);
							
							System.out.println("Paint Job record inserted successfully.");
							System.out.println("=========================================");
					}
					
					if (choice3 == 3) {
						
						// Declaring Variables
						String job_machine_type, machine_time, material_used, cut_labor_time;
						
						// Taking machine type from user
						System.out.println("Enter the Job Machine Type.");
						job_machine_type = myScan.next();
						
						// Taking machine time from user 
						System.out.println("Enter the cut job machine time in HH:MM:SS format.");
						machine_time = myScan.next();
						
						// Taking material used from the user
						System.out.println("Enter the material used.");
						material_used = myScan.next();
						
						// Taking labor time from the user
						System.out.println("Enter the cut job labor time in HH:MM:SS format.");
						cut_labor_time = myScan.next();
						
						// Executing Procedure to insert data into Job_cut table
						final String Sql7_3 = "EXEC proc_7_3 @job_no = '"+job_no1+"', @job_machine_type= '"+job_machine_type+"', "+
						" @machine_time = '"+machine_time+"', @material_used = '"+material_used+"', @cut_labor_time = '"+cut_labor_time+"';";
						
						final Statement statement7_3 = connection.createStatement(); 
							statement7_3.executeUpdate(Sql7_3);
							
							System.out.println("Cut Job record inserted successfully.");
							System.out.println("=========================================");
					}
					} catch (Exception e) {
						System.out.println("You got an error!. Returning to main menu");
					}
					break;
			case 8:
				// try and catch are used to not terminate loop in case of error.
				try {
					
					// Declaring Variables
				String t_no;
				int job_no2;
				float sup_cost;
				
				// Taking transaction number from the user
				System.out.println("Enter the Transaction Number.");
				t_no = myScan.next();
				
				// Taking sup-cost from the user
				System.out.println("Enter the cost of the transaction.");
				sup_cost = myScan.nextFloat();
				
				// Taking job-no from the user
				System.out.println("Enter the Job number related to transaction.");
				job_no2 = myScan.nextInt();
				
				// Executing procedure for Query 8.
				final String Sql8 = "EXEC proc_8 @t_no = '"+t_no+"', @sup_cost = '"+sup_cost+"', @job_no = '"+job_no2+"';";
				
				final Statement statement8 = connection.createStatement(); 
					statement8.executeUpdate(Sql8);
			
					System.out.println("Transaction record inserted and related accounts updated successfully.");
					System.out.println("=========================================");
					
				} catch (Exception e) {
					System.out.println("You got an error!. Returning to main menu");
				}
				break;
			
			case 9:
				// try and catch are used to not terminate loop in case of error.
				try {
					// Declaring Variables
				String assembly_id3;
				
				// Taking assembly-id from the user
				System.out.println("Enter Assembly Id to retrieve the cost.");
				assembly_id3 = myScan.next();
				
				try {
					
				// Executing procedure for Query 9
				final String Sql9 = "EXEC proc_9 @assembly_id = '"+assembly_id3+"';";
				
				try (final Statement statement9 = connection.createStatement(); 
						final ResultSet resultSet1 = statement9.executeQuery(Sql9)) {
					
						System.out.println(String.format("Cost incurred on assembly-id %s:", assembly_id3)); 
						while (resultSet1.next()) {
							System.out.println(String.format("%f",
								resultSet1.getFloat(1)));
								
							}
						}
				} catch (Exception e) {
					System.out.println("You got into an error!. Please try again.");
				}
				
				} catch (Exception e) {
					System.out.println("You got an error!. Returning to main menu");
				}
				break;
				
			case 10:
				// try and catch are used to not terminate loop in case of error.
				try {
					
					// Declaring Variables
				String dept_no2,date_completed1;
				
				// Taking department-no from user
				System.out.println("Enter Department Number to get the total labor time.");
				dept_no2 = myScan.next();
				
				// Taking job-completed date from user
				System.out.println("Enter Job completed date to get the total labor time.");
				date_completed1 = myScan.next();
				
				// Executing the procedure for Query 10
				final String Sql10 = "EXEC proc_10 @dept_no = '"+dept_no2+"', @date_completed = '"+date_completed1+"';";
				
				try (final Statement statement10 = connection.createStatement(); 
						final ResultSet resultSet2 = statement10.executeQuery(Sql10)) {
					
						System.out.println(String.format("Total labor-time in minutes for department number %s and date" +
								" job completed %s:", dept_no2, date_completed1)); 
						while (resultSet2.next()) {
							System.out.println(String.format("%s",
								resultSet2.getInt(1)));
								
							}
						}
				} catch (Exception e) {
					System.out.println("You got an error!. Returning to main menu");
				}
				break;
			case 11:
				// try and catch are used to not terminate loop in case of error.
				try {
					
					// Declaring Variables
				String assembly_id4;
				
				// Taking assembly-id from user
				System.out.println("Enter the Assembly Id to retrieve the processes.");
				assembly_id4 = myScan.next();
				
				// Executing procedure for Query 11d
				final String Sql11 = "EXEC proc_11 @assembly_id = '"+assembly_id4+"';";
				
				try (final Statement statement11 = connection.createStatement(); 
						final ResultSet resultSet3 = statement11.executeQuery(Sql11)) {
					
						System.out.println(String.format("The processes through which Assembly Id %s passed so far:", assembly_id4)); 
						while (resultSet3.next()) {
							System.out.println(String.format("%s | %s | %s",
								resultSet3.getString(1),
								resultSet3.getString(2),
								resultSet3.getString(3)));
								
							}
						}
				} catch (Exception e) {
					System.out.println("You got an error!. Returning to main menu");
				}
				break;
			
			case 12:
				// try and catch are used to not terminate loop in case of error.
				try {
				
					// Declaring Variables
				String date_completed2, dept_no3;
				
				// Taking date completed from user
				System.out.println("Enter the date job is completed.");
				date_completed2 = myScan.next();
				
				// Taking department number from user
				System.out.println("Enter the department to retrieve the jobs.");
				dept_no3 = myScan.next();
				
				// Executing Procedure to retrieve fit jobs 
				final String Sql12_1 = "EXEC proc_12_1 @date_completed = '"+date_completed2+"', @dept_no = '"+dept_no3+"';";
				
				try (final Statement statement12_1 = connection.createStatement(); 
						final ResultSet resultSet5 = statement12_1.executeQuery(Sql12_1)) {
					
						System.out.println(String.format("The fit jobs completed on date %s in department %s:", date_completed2, dept_no3)); 
						while (resultSet5.next()) {
							System.out.println(String.format("%s | %s | %s",
								resultSet5.getString(1),
								resultSet5.getString(2),
								resultSet5.getString(3)));
								
							}
						}
				// Executing procedure to retrieve paint jobs 
				final String Sql12_2 = "EXEC proc_12_2 @date_completed = '"+date_completed2+"', @dept_no = '"+dept_no3+"';";
				
				try (final Statement statement12_2 = connection.createStatement(); 
						final ResultSet resultSet6 = statement12_2.executeQuery(Sql12_2)) {
					
						System.out.println(String.format("The Paint jobs completed on date %s in department %s:", date_completed2, dept_no3)); 
						while (resultSet6.next()) {
							System.out.println(String.format("%s | %s | %s | %s | %s",
								resultSet6.getString(1),
								resultSet6.getString(2),
								resultSet6.getString(3),
								resultSet6.getString(4),
								resultSet6.getString(5)));
								
							}
						}
				
				
				// Executing procedure to retrieve cut jobs
				final String Sql12_3 = "EXEC proc_12_3 @date_completed = '"+date_completed2+"', @dept_no = '"+dept_no3+"';";
				
				try (final Statement statement12_3 = connection.createStatement(); 
						final ResultSet resultSet7 = statement12_3.executeQuery(Sql12_3)) {
					
						System.out.println(String.format("The Cut jobs completed on date %s in department %s:", date_completed2, dept_no3)); 
						while (resultSet7.next()) {
							System.out.println(String.format("%s | %s | %s | %s | %s | %s",
								resultSet7.getString(1),
								resultSet7.getString(2),
								resultSet7.getString(3),
								resultSet7.getString(4),
								resultSet7.getString(5),
								resultSet7.getString(6)));
								
							}
						}
				} catch (Exception e) {
					System.out.println("You got an error!. Returning to main menu");
				}
				
				break;
				
			case 13:
				// try and catch are used to not terminate loop in case of error.
				try {
					
					// Declaring Variables
				int lower_b, upper_b;
				
				// Taking lower bound of category from user
				System.out.println("Enter the lower bound of the category.");
				lower_b = myScan.nextInt();
				
				// Taking upper bound of category from user
				System.out.println("Enter the upper bound of the category.");
				upper_b = myScan.nextInt();
				
				// Executing procedure for Query 13
				final String Sql13 = "EXEC proc_13 @lower_b = '"+lower_b+"', @upper_b = '"+upper_b+"';";
				
				try (final Statement statement13 = connection.createStatement(); 
						final ResultSet resultSet4 = statement13.executeQuery(Sql13)) {
					
						System.out.println("The customers in the given range of category are"); 
						while (resultSet4.next()) {
							System.out.println(String.format("%s | %s",
								resultSet4.getString(1),
								resultSet4.getString(2)));
								
							}
						}
				} catch (Exception e) {
					System.out.println("You got an error!. Returning to main menu");
				}
				break;
				
			case 14:
				// try and catch are used to not terminate loop in case of error.
				try {
					
					// Declaring Variables
				int lower_b1, upper_b1;
				
				// Taking lower bound job-no from user
				System.out.println("Enter the lower bound of the cut job-no.");
				lower_b1 = myScan.nextInt();
				
				// Taking upper bound job-no from user
				System.out.println("Enter the upper bound of the cut job-no.");
				upper_b1 = myScan.nextInt();
				
				// Executing procedure for Query 14
				final String Sql14 = "EXEC proc_14 @lower_b = '"+lower_b1+"', @upper_b = '"+upper_b1+"';";
				
				final Statement statement14 = connection.createStatement(); 
					statement14.executeUpdate(Sql14);
			
					System.out.println("Deleted all cut jobs in the given range of job-no.");
					System.out.println("=========================================");
					
				} catch (Exception e) {
					System.out.println("You got an error!. Returning to main menu");
				}
				break;
				
			case 15:
				// try and catch are used to not terminate loop in case of error.
				try {
					
					// Declaring Variables
				String color;
				int job_no3;
				
				// Taking job-no from user
				System.out.println("Enter the paint job-no.");
				job_no3 = myScan.nextInt();

				// Taking color from user
				System.out.println("Enter the new color for the job-no.");
				color = myScan.next();
				
				// Executing procedure for Query 15
				final String Sql15 = "EXEC proc_15 @job_no = '"+job_no3+"', @color = '"+color+"';";
				
				final Statement statement15 = connection.createStatement(); 
					statement15.executeUpdate(Sql15);
			
					System.out.println("Changed the color of a given paint job.");
					System.out.println("=========================================");
					
				} catch (Exception e) {
					System.out.println("You got an error!. Returning to main menu");
				}
				break;
				
			case 16:
				// try and catch are used to not terminate loop in case of error.
				try {
					// Declaring Variables
				String file_name, line;
				
				// Taking Input file name from user
				System.out.println("Enter the file-name to Import data.");
				file_name = myScan.next();
				
				// Creating new Fiel object
				File file = new File(file_name);
				
				// Creating new Scanner Object
				Scanner sc = new Scanner(file);
				
				// While loop to read all lines in the input file
				while(sc.hasNextLine()) {
					line = sc.nextLine();
					
					// Dividing line to parts separated by Delimiter (",")
					String[] parts = line.split(",");
					
					String cname2 = parts[0];
					String caddress1 = parts[1];
					int category1 = Integer.parseInt(parts[2]);
					
					// Execute procedure for Query 16
					final String Sql16 = "EXEC proc_1 @cname = '"+cname2+"', @caddress = '"+caddress1+"', @category = '"+category1+"';";
					
					final Statement statement16 = connection.createStatement(); 
						statement16.executeUpdate(Sql16);
					
				}
				
				} catch (Exception e) {
					System.out.print("You got an error!. Returning to main menu");
				}
				break;
			case 17:
				// try and catch are used to not terminate loop in case of error.
				try {
					
					// Declaring Variables
					String file_name1, lower_b2, upper_b2;
					
					// Enter the filename to output result
					System.out.println("Enter the file-name to Export data.");
					file_name1 = myScan.next();
					
					// Taking lower bound of category from user
					System.out.println("Enter the lower bound of category.");
					lower_b2 = myScan.next();
					
					// Taking upper bound of category from user
					System.out.println("Enter the upper bound of category.");
					upper_b2 = myScan.next();
					
					// Creating new file writer Object
					FileWriter fw = new FileWriter(file_name1);
					
					// Executing Procedure(for Query 13) to get output
					final String Sql17 = "EXEC proc_13 @lower_b = '"+lower_b2+"', @upper_b = '"+upper_b2+"';";
					
					
					try (final Statement statement17 = connection.createStatement(); 
							final ResultSet resultSet4 = statement17.executeQuery(Sql17)) {
						
							//System.out.println("The customers in the given range of category are"); 
							while (resultSet4.next()) {
								
								//fw.write(String.format("%s,%s", resultSet4.getString(1), resultSet4.getString(2)));
								fw.write(resultSet4.getString(1) + "," + resultSet4.getString(2) + "\n");	
								}
							fw.close();
							} catch (SQLException e) {
								e.getCause().getMessage();
							}
					
					
					} catch (Exception e) {
						System.out.println("You got an error!. Returning to main menu");
					}
				break;
				
			case 18:
				System.out.println("You choose to Quit! Bye!");
		
	  		}
		}
	}
	}
}