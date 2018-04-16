<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<h3>Welcome, Enter The Book Details</h3>
  <a href="/">Home</a><br>
  <br>
<form method="POST" action="/addBook" id="addBookPage" name ="addBookPage">
  Book Title : <input type="text" name="title" id="title"/><br>
  Author: <input type="text" name="author" id="author"/><br>
  Genre: <input type="text" name="genre" id="genre"/><br>
  Price: <input type="text" name="price" id="price"/><br>
<!-- <!--   Image: <input type="file" name="image" id="image"/><br>
 --> -->  <input type="submit" value="Submit">
</form>

</body>
</html>