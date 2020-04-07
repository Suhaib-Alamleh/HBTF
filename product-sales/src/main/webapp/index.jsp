<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>product sales</title>
</head>
<body>
	
	<h1>Welcome to product sales web application</h1>
	<nav>
		<a href="${pageContext.request.contextPath}/">Home</a> |
		<c:if test="${sessionScope.user == null}">
			<a href="${pageContext.request.contextPath}/views/auth/login.jsp">Login</a> | 
			<a href="${pageContext.request.contextPath}/views/auth/signup.jsp">SignUp</a> | 
		</c:if>
		<c:if test="${sessionScope.user != null}">
			<a href="${pageContext.request.contextPath}/auth">Logout</a> | 
		</c:if>
		<a href="${pageContext.request.contextPath}/secure/customers">customers</a>
	</nav>

</body>
</html>