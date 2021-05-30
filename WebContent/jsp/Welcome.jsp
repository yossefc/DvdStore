<%@page import="in.co.dvd.store.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">

        <h1 class="jumbotron-heading">Welcome Online Dvd Store</h1>
         <p class="lead text-muted mb-0">
         </p>
             <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
    </div>
    
</section>
    

<div style="margin-top:259px;">
</div>
</body>
</html>