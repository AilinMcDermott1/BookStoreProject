<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script>
function addBook(){
	var id =  document.getElementById("id").value;
	alert("id:" + id);
	
	$.ajax({
		  type: "POST",
		  url: "/shoppingCart",
		  data: { id: id
		 
			        }, // parameters
		   datatype: 'json'
		});
		}
$(document).ready(function() {
		
		// DO GET
		$.ajax({
			type : "GET",
			url : "/searchfunction",
			success: function(result){
				$.each(result, function(i, book){
					
					var customerRow = '<tr>' +
					                    '<td width="2%"><img height="200" width="150"src=resources/images/' + book.image + '/></td>'  +
										'<td width="3.5%" height="50">' + book.author + '</td>' +
										'<td width="3.5%" height="50">' + book.title + '</td>' +
										'<td width="3.5%" height="50">' + book.price + '</td>' +
										'<td width="3.5%" height="50">' + book.genre + '</td>' +
										'<td width="3.5%" height="50"><a href="/book/'+ book.id +'"> View Book</a></td>' + 	  						 
								        '<td width="3.5%" height="50"><a href="/shoppingcart/' + book.id +'">  Add to Cart</a></td>' +  	  						 
								        '<td width="3.5%" height="50"><a href="/shoppingcart/' + book.id +'"> Update Quantity</a></td>' + '</tr>';
								        	
								        
				 				
									
					$('#customerTable tbody').append(customerRow);
				
		        });
				
				$( "#customerTable tbody tr:odd" ).addClass("info");
				$( "#customerTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
		
		// do Filter on View
		 $("#inputFilter").on("keyup", function() {
		    var inputValue = $(this).val().toLowerCase();
		    $("#customerTable tr").filter(function() {
		    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
		    });
		}); 
	})
</script>

<body>
 <h3>Some of our Books</h3>
 
 <a href = "/">Home</a>
	
			<h2>All Books</h2>
		<div class="row col-md-7 ">
		 
		  	<input class="form-control" id="inputFilter" type="text" placeholder="Search for Title, Author, or Category.." /><br>
		  	</div>
		  	
			<table id="customerTable" class="table table-bordered table-hover table-responsive">
				<thead>
					<tr>
					<th  class="Cover">Book Cover</th>
				    <th class="Author">Author</th>
			        <th class="Title">Title</th>
			        <th class="Price">Price</th>
			        <th  class="Category">Category</th>
			        <th  class="tg-bars"> </th>	
			        <th  class="tg-uiversity"> </th>	
			        <th></th>				
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
</body>
</html>