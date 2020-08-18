<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Customers</title>
	</head>
	<body>
	<%@page import="jsp_azure_test.DataHandler1"%>
	<%@page import="java.sql.ResultSet"%>
	<%
		// We instantiate the data handler here, and get all the customers from the database
		final DataHandler1 handler = new DataHandler1();
		final ResultSet customers = handler.getAllCustomers();
	%>
	<!-- The table for displaying all the movie records --> 
	<table cellspacing="2" cellpadding="2" border="1">
		<tr> <!-- The table headers row -->
			<td align="center">
				<h4>Customer</h4>
			</td>
			<td align="center">
				<h4>Address</h4>
			</td>
			<td align="center">
				<h4>Category</h4>
		</tr>
		<%
			while(customers.next()) { // For each Customer record returned...
				// Extract the attribute values for every row returned
				final String cname = customers.getString("cname");
				final String caddress = customers.getString("caddress");
				final String category = customers.getString("category");
				out.println("<tr>"); // Start printing out the new table row
				out.println( // Print each attribute value
					"<td align=\"center\">" + cname +
					"</td><td align=\"center\"> " + caddress+
					"</td><td align=\"center\"> " + category + "</td>");
				out.println("</tr>");
			}
			%>
		</table>
	</body>
</html>