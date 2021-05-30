<%@page import="in.co.dvd.store.controller.DvdListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.dvd.store.bean.DvdBean"%>
<%@page import="in.co.dvd.store.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dvd List</title>
</head>
<body>
<%@ include file="Header.jsp" %>


<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">DVD</h1>
    </div>
</section>
<form action="<%=OBSView.DVD_LIST_CTL%>" method="post">
<jsp:useBean id="ubean" class="in.co.dvd.store.bean.UserBean"
						scope="request"></jsp:useBean>
						
						<input type="hidden" name="id" value="<%=ubean.getId()%>"> 
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="<%=OBSView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item active"><a href="<%=OBSView.DVD_LIST_CTL%>">Dvd</a></li>
                  
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        
        <div class="col">
            <div class="row">
             <%

                    DvdBean bean=null;
                    List list = ServletUtility.getList(request);
                    Iterator<DvdBean> it = list.iterator();

                    while (it.hasNext()) {

                         bean = it.next();
                %>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="card">
                        <a href="<%=OBSView.DVD_CTL%>"><img class="card-img-top" src="<%=OBSView.APP_CONTEXT%>/images/<%=bean.getImageName()%>" alt="Card image cap"></a>
                        <div class="card-body">
                            <h4 class="card-title text-center"><a href="<%=OBSView.DVD_CTL%>" title="View Product"><%=bean.getName()%></a></h4>
                            <ul class="list-group">
  								<li class="list-group-item"><a class="font-weight-bold">Categorie:</a> <%=bean.getCategorie()%></li>
  								<li class="list-group-item"><a class="font-weight-bold">Origin: </a> <%=bean.getState()%></li>
  								<li class="list-group-item"><a class="font-weight-bold">Year: </a> <%=bean.getYear()%></li>
  								<li class="list-group-item"><a class="font-weight-bold">duration: </a> <%=bean.getDuration()%></li>
 								<li class="list-group-item"><a class="font-weight-bold">resume: </a> <%=bean.getDescription()%></li>
							</ul>
                          	<%if(bean.getSaleType().equalsIgnoreCase("Paid")){ %>
                            <p class="bloc_left_price font-weight-bold text-danger text-center"><%=bean.getPrice()%>  Shekel</p>
                            <%}else{ %>
                            <p class="bloc_left_price">Free</p>
                            <%} %>
                            
                       <div class="row">                               
                                <%if(userLoggedIn){ %>
                                 
                            
                                <div class="col">   
                                	<%if(userBean.getRoleId()==1){%>                              	
                                    <a href="<%=OBSView.DVD_LIST_CTL%>?dvdId=<%=bean.getId()%>" class="btn btn-danger btn-block">Delete</a>                                   
                                </div>
                                <div class="col">
                                    <a href="<%=OBSView.DVD_CTL%>?id=<%=bean.getId()%>" class="btn btn-success btn-block">Edit</a>
                                </div>
                                <div class="col">
                                    <a target="_blank"  href="<%=OBSView.APP_CONTEXT%>/pdfs/<%=bean.getPdfName()%>" class="btn btn-danger btn-block">View</a>
                                </div>
                                <%}else if(userBean.getRoleId()==2){%>
                                
                    				<a href="<%=OBSView.BOOKED_LIST_CTL%>?dvdId=<%=bean.getId()%>" class="btn btn-danger btn-block">Rent</a>
                				</div>
                                 <div class="col">
                                    <a target="_blank"  href="<%=OBSView.APP_CONTEXT%>/pdfs/<%=bean.getPdfName()%>" class="btn btn-danger btn-block">View</a>
                                </div>
                                <%} %>
                                <%} %>
                            </div>
                        </div>
                    </div>
                </div>
                
                <%} %>
            </div>
        </div>

    </div>
</div>
</form>

</body>
</html>