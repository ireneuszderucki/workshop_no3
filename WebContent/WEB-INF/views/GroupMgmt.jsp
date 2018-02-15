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
	<c:choose>
  		<c:when test="${not empty groups}">
		    <table border="1">
				<tr>
					<th>Group ID</th>
					<th>Name</th>
					<th>Edit</th>
				</tr>
				<c:forEach items="${groups}" var="group">
					<tr>
						<td>${group.id}</td>			
						<td>${group.name}</td>
						<td><a href="GroupEdit?id=${group.id}">Edit the group</a></td>
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