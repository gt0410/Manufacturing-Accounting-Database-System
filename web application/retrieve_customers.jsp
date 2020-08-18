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
	// Get the attribute values passed from the input form.
		String lower_b = request.getParameter("lower_b");
		String upper_b = request.getParameter("upper_b");
		
		
		if (lower_b.equals("") || upper_b.equals("")) {
		response.sendRedirect("retrieve_customer_form.jsp");
		} else {
		int lower_b1 = Integer.parseInt(lower_b);
		int upper_b1 = Integer.parseInt(upper_b);
		// Now perform the query with the data from the form.		
		final ResultSet customers = handler.retrieveCustomers(lower_b1, upper_b1);
	%>
	<!-- The table for displaying all the Customer records --> 
	<table cellspacing="2" cellpadding="2" border="1">
		<tr> <!-- The table headers row -->
			<td align="center">
				<h4>Customer</h4>
			</td>
			<td align="center">
				<h4>category</h4>
			</td>
		<%
			while(customers.next()) { // For each Customer record returned...
				// Extract the attribute values for every row returned
				final String cname = customers.getString("name");
				final String category = customers.getString("category");
				out.println("<tr>"); // Start printing out the new table row
				out.println( // Print each attribute value
					"<td align=\"center\">" + cname + 
					"</td><td align=\"center\"> " + category + "</td>");
				out.println("</tr>");
			}
		}
			%>
		</table>
	</body>
</html>