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
	<c:choose>
  		<c:when test="${not empty users}">
		    <table border="1">
				<tr>
					<th>User ID</th>
					<th>Group ID</th>
					<th>Username</th>
					<th>Password</th>
					<th>Email</th>
					<th>Edit</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id}</td>			
						<td>${user.group}</td>
						<td>${user.username}</td>
						<td>${user.password}</td>
						<td>${user.email}</td>
						<td><a href="UserEdit?id=${user.id}">Edit the user</a></td>
					</tr>
				</c:forEach>
			</table>
  		</c:when>
  		<c:otherwise>
    <h1>${defaultMsg}</h1>
  		</c:otherwise>
  	</c:choose>
	

</body>
</html>
<%@ include file="/WEB-INF/fragments/footer.jsp" %>