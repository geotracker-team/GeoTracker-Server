<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/validateLogin.js"></script>
<style>
form {
    border: 3px solid #f1f1f1;
    width:40%;
    margin:auto;padding:0px;
    text-align:center;
    align:center;
}
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}
p {background-color:blue;color:white;width:40%;margin:auto}

button {
    background-color: blue;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}

img.logo {
    width: 20%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}

</style>
</head>
<body>

<p align="center"><strong><font size="6" face="Times New Roman, Times, serif">
      	GeoTracker Login</font></strong>

<form action="LoginServlet" method="get" name="loginForm" onSubmit="validateForm()">
  <div class="imgcontainer">
    <img src="img/logo.png" alt="logo" class="logo">
  </div>
<%
	String strLogin = (String) session.getAttribute("name");
	if(strLogin == null)
		strLogin = "";
	String strPassword = (String) session.getAttribute("password");
	if(strPassword == null)
		strPassword = "";

%>
  <div class="container">
    <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" value="<%=strLogin %>" required>

    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" value="<%=strPassword %>" required>
        
    <button type="submit">Login</button>
    <input type="checkbox" checked="checked"> Remember me
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <button type="button" class="cancelbtn">Cancel</button>
    <span class="psw">Forgot <a href="#">password?</a></span>
  </div>
  
  <%
  	String err = (String) session.getAttribute("error");
  	if(err == null)
  		err = "";
  
  %>
  	<p><%=err %></p>
  <div>
  	
  </div>
</form>

</body>
</html>
