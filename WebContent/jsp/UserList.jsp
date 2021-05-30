<%@page import="in.co.dvd.store.model.UserModel"%>
<%@page import="in.co.dvd.store.controller.BookedListCtl"%>
<%@page import="in.co.dvd.store.bean.DvdBean"%>
<%@page import="in.co.dvd.store.model.DvdModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.dvd.store.util.DataUtility"%>
<%@page import="in.co.dvd.store.bean.BookedBean"%>
<%@page import="in.co.dvd.store.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UserListView</title>
</head>
<body>
<%@ include file="Header.jsp" %>


<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">USER LIST</h1>
    </div>
</section>
<form action="<%=OBSView.USER_LIST_CTL%>" method="post">
<div class="container">
    <div class="row">
        <div class="col">
        <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="<%=OBSView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item active"><a href="<%=OBSView.USER_LIST_VIEW%>">Booked List</a></li>
                  
                </ol>
            </nav>
            
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="row">
            <table class="table table-bordered">
				<thead>
					<tr>
						<th>First name</th>
						<th>Last Name</th>
						<th>Login</th>
						<th>Password</th>
						<th>mobile number</th>
						<th>Email</th>
	
					</tr>
                </thead>
				<br>
				<tbody>
				
             <%               
                   UserBean bean=null;
                    List list = ServletUtility.getList(request);
                    Iterator<UserBean> it = list.iterator();
                    
                    while (it.hasNext()) {
                         bean = it.next();                
                %>
                
				

						<tr>
					
							<td><%=bean.getFirstName() %></td>
							<td><%=bean.getLastName()%></td>
							<td><%=bean.getLogin() %></td>
							<td><%=bean.getPassword() %></td>
							<td><%=bean.getMobileNo() %></td>
							<td><%=bean.getEmailId() %></td>
							<td><a href="<%=OBSView.BOOKED_LIST_CTL%>?IdUser=<%=bean.getId()%>" >card</a>
								<a href="<%=OBSView.USER_REGISTRATION_CTL%>?IdUser=<%=bean.getId()%>" >Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="<%=OBSView.USER_LIST_CTL%>?IdUserDelete=<%=bean.getId()%>" >Delete</a></td>
						</tr>
					<%} %>
				</tbody>

			</table>
                
                
              
 
               
     
            </div>
        </div>

    </div>
</div>
</form>

</body>
</html>  