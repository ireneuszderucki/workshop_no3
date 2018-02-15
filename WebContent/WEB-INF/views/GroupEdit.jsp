<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/fragments/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Group Management</title>
</head>
<body>
	<h3>Up-to-date data:</h3>
	<table border="1">
				<tr>
					<th>Group ID</th>
					<th>Name</th>
				</tr>
				<tr>
					<td>${group.id}</td>			
					<td>${group.name}</td>
				</tr>
	</table>
	<h3 style="color:red">Edit form:</h3>
	<form action="GroupMgmt" method="post">
		<label>ID (uneditable)<br>
			<input type="number" name="id" value="${group.id}" readonly> 
		</label><br>
		<label>Type the new name for user group<br>
			<input type="text" name="editedName" value="${group.name}"> 
		</label>
		<input type="submit" name="editGroupSubmit" value="Submit edition">	
	</form>
	<h3 style="color:green">Add a new group form:</h3>
	<form action="GroupEdit" method="post">
		<label>Type the name for a new user group
			<input type="text" name="newName" placeholder="new group">
		</label>
		<input type="submit" name="newGroupSubmit" value="Submit new group">
	</form>
</body>
</html>
<%@ include file="/WEB-INF/fragments/footer.jsp" %>