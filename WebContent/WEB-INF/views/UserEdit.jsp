<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/fragments/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Management</title>
</head>
<body>
	<h3>Up-to-date data:</h3>
	<table border="1">
				<tr>
					<th>User ID</th>
					<th>Group ID</th>
					<th>Username</th>
					<th>Password</th>
					<th>Email</th>
				</tr>
				<tr>
					<td>${user.id}</td>			
					<td>${user.group}</td>
					<td>${user.username}</td>
					<td>${user.password}</td>
					<td>${user.email}</td>
				</tr>
	</table>
	<h3 style="color:red">Edit form:</h3>
	<form action="UserMgmt" method="post">
		<label>User ID (uneditable)<br>
			<input type="number" name="id" value="${user.id}" readonly> 
		</label><br>
		<label>Assign user group<br>
			<input type="number" name="editedGroupId" value="${user.group}"> <!-- przydalby sie min=1 max=ilość grup -->
		</label><br>
		<label>Type the new username<br>
			<input type="text" name="editedUsername" value="${user.username}">
		</label><br>
		<label>Type the new password<br>
			<input type="text" name="editedPassword" value="${user.password}">
		</label><br>
		<label>Type the new email<br>
			<input type="text" name="editedEmail" value="${user.email}">
		</label><br>
		<input type="submit" name="editUserSubmit" value="Submit edition">	
	</form>
	<h3 style="color:green">Add a new user form:</h3>
	<form action="UserEdit" method="post">
		<label>Assign user group<br>
			<input type="number" name="newGroupId" placeholder="user group ID"> <!-- przydalby sie min=1 max=ilość grup -->
		</label><br>
		<label>Type the new username<br>
			<input type="text" name="newUsername" placeholder="username">
		</label><br>
		<label>Type the new password<br>
			<input type="text" name="newPassword" placeholder="password">
		</label><br>
		<label>Type the new email<br>
			<input type="text" name="newEmail" placeholder="email">
		</label><br>
		<input type="submit" name="newUserSubmit" value="Submit new user">
	</form>
</body>
</html>
<%@ include file="/WEB-INF/fragments/footer.jsp" %>