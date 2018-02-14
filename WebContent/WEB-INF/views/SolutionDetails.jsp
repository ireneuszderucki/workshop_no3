<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/fragments/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Solution Details</title>
</head>
<body>
	<h2>Solution ID: ${solution.id}</h2>
	<ul>
		<li>Exercise ID: ${solution.exercise}</li>
		<li>User ID: ${solution.user}</li>
		<li>Created: ${solution.created}</li>
		<li>Updated: ${solution.updated}</li>
		<li>Description: ${solution.description}</li>
	</ul>
</body>
</html>
<%@ include file="/WEB-INF/fragments/footer.jsp" %>