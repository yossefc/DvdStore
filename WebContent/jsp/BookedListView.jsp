<%@page import="in.co.dvd.store.controller.BookedListCtl"%>
<%@page import="in.co.dvd.store.bean.DvdBean"%>
<%@page import="in.co.dvd.store.model.DvdModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.dvd.store.bean.BookedBean"%>
<%@page import="in.co.dvd.store.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookedListView</title>
</head>
<body>
<%@ include file="Header.jsp" %>


<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Booked</h1>
    </div>
</section>
<form action="<%=OBSView.BOOKED_LIST_CTL%>" method="post">
<jsp:useBean id="ubean" class="in.co.dvd.store.bean.UserBean"
						scope="request"></jsp:useBean>
						
						<input type="hidden" name="id" value="<%=ubean.getId()%>"> 
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="<%=OBSView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item active"><a href="<%=OBSView.BOOKED_LIST_CTL%>">Booked List</a></li>
                  
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
         <table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>reservation Date</th>
						<th>Date to return</th>
						
					</tr>
                </thead>
				
				<tbody>
				
				
             <%           
             		DvdBean bean=null;
             		BookedBean reservD =null;
             		DvdBean d= new DvdBean();
             		
                    List list = ServletUtility.getList(request);
                    List listD = ServletUtility.getListD(request);
                    
                    Iterator<DvdBean> it = list.iterator();
                    Iterator<BookedBean> itD = listD.iterator();
                    
                    int count=0;
                    while (it.hasNext() && itD.hasNext()) {
                         bean = it.next();  
                        count++;
                         reservD = itD.next();
                %>
						<tr>
						 	<td><%=count%></td>
							<td><%=bean.getName()%></td>		
							<td><%=reservD.getCreatedDatetime() %></td>
							<td><%=reservD.getPlusDate() %></td> 	
 										<%if(userBean.getRoleId()==1){%>   
 												<%if (reservD.getRendu()==1){%>
														<td><a class="text-success" >return</a></td> 	
												<%}%>
												<%if((reservD.getRendu()==0)){ %>
													<td><a class="text-danger font-weight-bold"> not return</a></td> 
												<%} %>	
												<td><a  href="<%=OBSView.BOOKED_LIST_CTL%>?boolrend=<%=reservD.getId()%>" >return the command</a>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="hidden" name="IdUser" value="<%=bean.getId()%>"> 
										<%} %>
										<%if(userBean.getRoleId()==2){ %>
												<%if (reservD.getRendu()==1){%>
														<td><a class="text-success" >return</a></td> 	
												<%}%>
												<%if((reservD.getRendu()==0)){ %>
													<td><a class="text-danger font-weight-bold" > not return</a></td> 
												<%} %>
										<%} %>

						</tr>
					<%}%>
				</tbody>

			</table> 
        </div>

    </div>
</form>

</body>
</html>