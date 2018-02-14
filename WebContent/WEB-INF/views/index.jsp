<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/fragments/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>solution ID</th>
			<th>exercise ID</th>
			<th>user ID</th>
			<th>created</th>
			<th>updated</th>
			<th>description</th>
			<th>link to solution details</th>
			
		</tr>
		<c:forEach items="${theNewestSolutions}" var="solution">
			<tr>
				<td>${solution.id}</td>			
				<td>${solution.exercise}</td>
				<td>${solution.user}</td>
				<td>${solution.created}</td>
				<td>${solution.updated}</td>
				<td>${solution.description}</td>
				<td><a href="SolutionDetails?id=${solution.id}">Click here</a>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
<%@ include file="/WEB-INF/fragments/footer.jsp" %>