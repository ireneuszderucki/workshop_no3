<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/fragments/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User details and solutions</title>
</head>
<body>
	<h2>User details:</h2>
	<h3>User ID: ${user.id}</h3>
	<h3>Group ID: ${user.group}</h3>
	<h3>Username: ${user.username}</h3>
	<h3>Password: ${user.password}</h3>
	<h3>Email: ${user.email}</h3>
	<h2>User's solutions details:</h2>
	<ul>
		<c:forEach items="${solutions}" var="solution">
			<li>${solution}</li>
		</c:forEach>
	</ul>
</body>
</html>
<%@ include file="/WEB-INF/fragments/footer.jsp" %>