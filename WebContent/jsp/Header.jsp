

<%@page import="in.co.dvd.store.controller.LoginCtl"%>
<%@page import="in.co.dvd.store.controller.OBSView"%>
<%@page import="in.co.dvd.store.bean.*"%>
<%@page import="in.co.dvd.store.model.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/OnlineDvdStore/css/style.css" rel="stylesheet" >
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!------ Include the above in your HEAD tag ---------->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<script type="text/javascript">
function myMode() { 
	var mode = document.getElementById("saleType").value;
	
	
	if("Free".trim() === mode.trim()){
	  document.getElementById("input_price").disabled = true;
	  
	}
	if("Paid".trim() === mode.trim()){
		  document.getElementById("input_price").disabled = false;
		  
		}
	
	}
</script>
</head>
<body>
 <%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;
    DvdModel beanDvd = new DvdModel();
	List listcategorie = beanDvd.listCategorie();
    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">OnlineDvdStore</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
            
            <%if(userLoggedIn){ %>
 					<%if(userBean.getRoleId()==1){%>              
                <li class="nav-item active">
                    <a class="nav-link" href="<%=OBSView.WELCOME_VIEW%>">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="<%=OBSView.DVD_CTL%>">Add Dvd</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="<%=OBSView.DVD_LIST_CTL%>">DVD</a>
                </li>
                 <li class="nav-item ">
                    <a class="nav-link" href="<%=OBSView.USER_LIST_CTL%>">User List</a>
                </li>
  				
               <%}else if(userBean.getRoleId()==2){%>
               <li class="nav-item active">
                    <a class="nav-link" href="<%=OBSView.WELCOME_VIEW%>">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="<%=OBSView.DVD_LIST_CTL%>">dvd List</a>
                </li>
                 <li class="nav-item ">
                    <a class="nav-link" href="<%=OBSView.BOOKED_LIST_CTL%>">list dvd rent</a>
                </li>
               <%} %>
               <li class="text-white bg-dark nav-item">
                   <a class="text-white bg-dark nav-link"><a>Hi !<%=userBean.getFirstName()%></a>
                </li>
               
              <%}else{%>
              	<li class="nav-item active">
                    <a class="nav-link" href="<%=OBSView.WELCOME_VIEW%>">Home <span class="sr-only">(current)</span> </a>
                </li>
                
                 <li class="nav-item">
                    <a class="nav-link" href="<%=OBSView.DVD_LIST_CTL%>">DVDS</a>
                </li>
               <%}%>
                          <li class="dropdown nav-item">
      <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">Categorie <b class="caret"></b></a>
     <ul class="dropdown-menu">
      <%
      	String nCAt;
      	Iterator<String> it2 = listcategorie.iterator();
      	while (it2.hasNext() ) {
      		nCAt = it2.next();
      %>
       
        <li class="dropdown-item" ><a href="<%=OBSView.DVD_LIST_CTL%>?nameCat=<%=nCAt%>" ?><%=nCAt %></a></li>
        <%} %>
      </ul>
      
    </li>
         </div>
          
    
                    

               <!--  <li class="nav-item">
                    <a class="nav-link" href="">ContectUs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">AboutUs</a>
                </li> -->
                
              

                
            </ul>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


			<form class="example" action="dvdList">
  				<input type="text" placeholder="Search.." name="search">
  					<button type="submit">
  					<a href="<%=OBSView.DVD_LIST_CTL%>" ></a>
  					<i class="fa fa-search"></i></button>
			</form>
            <%if(userLoggedIn){%>  	
               
                <a class="btn btn-success btn-sm ml-3" href="<%=OBSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">
                    <i class="fa fa-sign-out"></i> Logout
                </a>  
                <%}else{%>
                <a class="btn btn-success btn-sm ml-3" href="<%=OBSView.LOGIN_CTL%>">
                    <i class="fa fa-sign-in"></i>Login
                </a>
                
                <%} %> 
            
        </div>
    </div>
</nav>



</body>
</html>