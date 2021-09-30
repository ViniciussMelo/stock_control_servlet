<%@page import ="java.util.List"%>
<%@page import ="model.Product"%>
<style>
	#main {
		position: absolute;
		left: 15%;
		height: 95%;
		padding: 0px 20px 0 20px;
		width: 82%;
		background-color: #DCDCDC;
	}
	
	/* Add padding to containers */
	.container {
	  padding: 16px 100px 16px 16px;
	}
	
	/* Full-width input fields */
	input[type=text], input[type=number], input[type=password] {
	  width: 100%;
	  padding: 15px;
	  margin: 5px 0 22px 0;
	  display: inline-block;
	  border: none;
	  background: #f1f1f1;
	}
	
	input[type=text]:focus, input[type=number]:focus, input[type=password]:focus {
	  background-color: white;
	  outline: none;
	}
	
	/* Overwrite default styles of hr */
	hr {
	  border: 1px solid #f1f1f1;
	  margin-bottom: 25px;
	}
	
	/* Set a style for the submit button */
	.registerbtn {
	  background-color: #04AA6D;
	  color: white;
	  padding: 16px 20px;
	  margin: 8px 0;
	  border: none;
	  cursor: pointer;
	  width: 100%;
	  opacity: 0.9;
	}
	
	.registerbtn:hover {
	  opacity: 1;
	}
	
	/* Add a blue text color to links */
	a {
	  color: dodgerblue;
	}
	
	/* Set a grey background color and center the text of the "sign in" section */
	.signin {
	  background-color: #f1f1f1;
	  text-align: center;
	}
	
	select {
	  width: 1500px;
	  height: 45px;
	  max-width: 100%;
	
	  /* Reset Select */
	  appearance: none;
	  outline: 0;
	  border: 0;
	  box-shadow: none;
	  /* Personalize */
	  padding: 0 1em;
	  color: gray;
	  background: #f1f1f1;
	  background-image: none;
	  cursor: pointer;
	}
	
	/* Remove IE arrow */
	select::-ms-expand {
	  display: none;
	}
	
	/* Custom Select wrapper */
	.select {
	  position: relative;
	  display: flex;
	  width: 20em;
	  height: 3em;
	  border-radius: .25em;
	  overflow: hidden;
	}
	
	/* Arrow */
	.select::after {
	  content: '\25BC';
	  position: absolute;
	  top: 0;
	  right: 0;
	  padding: 1em;
	  background-color: #34495e;
	  transition: .25s all ease;
	  pointer-events: none;
	  height: 45px;
	}
	
	/* Transition */
	.select:hover::after {
	  color: #f39c12;
	}
	
	option{ 
       font-size: 20px;
     }
</style>
<div id="main">
	<form action="MovementController" method="POST">
		<div class="container">
			<h3>Movement</h3>
			<label><b>Product</b></label>
	   		<br>
	   		<select name="productName">
	   		<%
	   			List<Product> products = (List) request.getAttribute("products");
	   			if (products != null) {
	   				for (Product prod : products) {
	   		%>
	   			<option value="<%=prod.getName()%>"><%=prod.getName()%></option>
	   		<%
	   				}
	   			}
	   		%>
			</select>
			<br>
			<br>
			<label><b>Type</b></label>
			<br>
			<select name="type">
				<option value="entry">Entry</option>
				<option value="exit">Exit</option>
			</select>
			<br>
			<br>
			<label><b>Quantity</b></label>
	   		<input type="number" onkeypress="return onlyNumberKey(event)" placeholder="Enter quantity" name="quantity" id="quantity" required>
			<br>
			<button type="submit" class="registerbtn">Register</button>
		</div>
	</form>
</div>
<script>
	function onlyNumberKey(evt) {
	    
	    // Only ASCII character in that range allowed
	    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
	        return false;
	    return true;
	}
</script>