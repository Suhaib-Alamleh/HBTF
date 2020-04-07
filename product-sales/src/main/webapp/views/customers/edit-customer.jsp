<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Customer details</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	color: #566787;
	background: #f5f5f5;
	font-family: 'Roboto', sans-serif;
}

.table-wrapper {
	background: #fff;
	padding: 20px;
	margin: 30px 0;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
		// Animate select box length
		var searchInput = $(".search-box input");
		var inputGroup = $(".search-box .input-group");
		var boxWidth = inputGroup.width();
		searchInput.focus(function() {
			inputGroup.animate({
				width : "300"
			});
		}).blur(function() {
			inputGroup.animate({
				width : boxWidth
			});
		});
	});
</script>
</head>
<body>
	<div class="container">
	
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
	
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-12">
						<h2>
							<a href="${pageContext.request.contextPath}/secure/customers">customers</a>/<b>Edit
								Customer</b>
						</h2>
					</div>
					<div class="col-sm-12">
						<hr>
					</div>
				</div>
			</div>
			<form action="${pageContext.request.contextPath}/secure/customers/edit" method="post">
				<input type="hidden" name="customerId" value="${customer.customerId}">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="firstName">FirstName</label> 
						<input type="text" name="firstName" id="firstName" value="${customer.firstName}"
							class="form-control" placeholder="FirstName">
					</div>
					<div class="form-group col-md-6">
						<label for="lastName">LastName</label> 
						<input type="text" name="lastName" id="lastName" value="${customer.lastName}"
							class="form-control" placeholder="lastName">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="phone">Phone</label> 
						<input type="text" name="phone" id="phone" value="${customer.phone}"
							class="form-control" placeholder="phone">
					</div>
					<div class="form-group col-md-6">
						<label for="city">City</label> 
						<input type="text" name="city" id="city" value="${customer.city}"
							class="form-control" placeholder="city">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="state">State</label> 
						<input type="text" name="state" id="state" value="${customer.state}"
							class="form-control" placeholder="state" >
					</div>
					<div class="form-group col-md-6">
						<label for="state">Points</label> 
						<input type="number" name="points" id="points" value="${customer.points}"
							class="form-control" placeholder="points">
					</div>
				</div>
				<div class="form-group">
					<label for="address">Address</label> <input type="text" value="${customer.address}"
						class="form-control" id="address" name="address" placeholder="1234 Main St">
				</div>
				<div class="form-group">
					<label for="comments">Comments</label> <input type="text" value="${customer.comments}"
						class="form-control" id="comments" name="comments"
						placeholder="comment...">
				</div>
				<button type="submit" class="btn btn-primary">Update Customer</button>
			</form>

		</div>
	</div>
</body>
</html>
