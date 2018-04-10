<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org">

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
table, th, td {
	border: 1px solid black;
}
</style>

<body>
	<h3>Some of our Books</h3>

	<a href="/">Home</a>

	<h2>All Books</h2>

	<table id="customerTable"
		class="table table-bordered table-hover table-responsive">
		<thead>
			<tr>
				<th class="id">Id</th>
			
				<th class="author">Author</th>

				<th class="title">Title</th>
				
				<th class="genre">Genre</th>
				
				<th class="price">Price</th>

			</tr>


	<c:forEach var="o" items="${listOfBooks}" varStatus="loopStatus">
		<tr>
			<td id="id" width="3.5%"><c:out
					value="${listOfBooks[loopStatus.index].id}" /></td>

			<td width="3.5%" height="50"><c:out value="${o.author}" /></td>

			<td width="3.5%" height="50"><c:out value="${o.title}" /></td>

			<td width="3.5%" height="50"><c:out value="${o.genre}" /></td>

			<td width="3.5%" height="50"><c:out value="${o.price}" /></td>


		</tr>
	</c:forEach>
</table>
</body>
</html>