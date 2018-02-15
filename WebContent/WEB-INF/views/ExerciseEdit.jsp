<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/fragments/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exercise Management</title>
</head>
<body>
	<h3>Up-to-date data:</h3>
	<table border="1">
				<tr>
					<th>Exercise ID</th>
					<th>Title</th>
					<th>Description</th>
				</tr>
				<tr>
					<td>${exercise.id}</td>			
					<td>${exercise.title}</td>
					<td>${exercise.description}</td>
				</tr>
	</table>
	<h3 style="color:red">Edit form:</h3>
	<form action="ExerciseMgmt" method="post">
		<label>Exercise ID (uneditable)<br>
			<input type="number" name="id" value="${exercise.id}" readonly> 
		</label><br>
		<label>Type the new title<br>
			<input type="text" name="editedTitle" value="${exercise.title}">
		</label><br>
		<label>Type the new description<br>
			<input type="text" name="editedDescription" value="${exercise.description}">
		</label><br>
		<input type="submit" name="editExerciseSubmit" value="Submit edition">	
	</form>
	<h3 style="color:green">Add a new exercise form:</h3>
	<form action="ExerciseEdit" method="post">
		<label>Type the new title<br>
			<input type="text" name="newTitle" placeholder="title">
		</label><br>
		<label>Type the new desciption<br>
			<input type="text" name="newDescription" placeholder="description">
		</label><br>
		<input type="submit" name="newExerciseSubmit" value="Submit new exercise">
	</form>
</body>
</html>
<%@ include file="/WEB-INF/fragments/footer.jsp" %>