<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.dvd.store.controller.*"%>
<%@page import="in.co.dvd.store.model.*"%>
<%@page import="in.co.dvd.store.util.DataUtility"%>
<%@page import="in.co.dvd.store.util.ServletUtility"%>
<%@page import="in.co.dvd.store.util.*"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dvd</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">DVD</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=OBSView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item"><a href="<%=OBSView.DVD_LIST_CTL%>">Dvd</a></li>
                    <li class="breadcrumb-item active"><a href="<%=OBSView.DVD_CTL%>">Add Product</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<form action="<%=OBSView.DVD_CTL%>" method="post" enctype="multipart/form-data" >
                   
                   
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
            <jsp:useBean id="bean" class="in.co.dvd.store.bean.DvdBean"
						scope="request"></jsp:useBean>
            
            	<%if(bean.getId()>0){%>
                <div class="card-header bg-primary text-white">Update Product
				<%}else{ %>
				<div class="card-header bg-primary text-white">Add Product
				<%} %>                
                </div>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
                <div class="card-body">
                   
						
			<input type="hidden" name="id" value="<%=bean.getId()%>"> 
                                    
                        <div class="form-group">
                            <label for="name">Name</label>
                             <input type="text" class="form-control" name="name" placeholder="Enter DVD Name..." 
                                value="<%=DataUtility.getStringData(bean.getName())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
                        </div>
                        
                        <%
							HashMap map = new HashMap();
							map.put("Free", "Free");
							map.put("Paid", "Paid");
						%>
						
						<div class="form-group">
                            <label for="name">SaleType</label>
                           <%=HTMLUtility.getList1("saleType",String.valueOf(bean.getSaleType()), map)%>
                                <font color="red"> <%=ServletUtility.getErrorMessage("saleType", request)%></font>
                        </div>
                        	<div class="form-group">
                            <label for="name">Categorie</label>
                             <%=HTMLUtility.getListcat("categorie", listcategorie)%>
                          
                        </div>
                        
                           <div class="form-group">
                            <label for="name">state</label>
                             <input type="text" class="form-control" name="state" placeholder="Enter State of the product..." 
                                value="<%=DataUtility.getStringData(bean.getState())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font>
                        </div>
                        <div class="form-group">
                            <label for="name">Year</label>
                             <input type="text" class="form-control" name="year" placeholder="Enter product year..." 
                                value="<%=DataUtility.getStringData(bean.getYear())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("year", request)%></font>
                        </div>
                        
                         <div class="form-group">
                            <label for="name">Duration</label>
                             <input type="text" class="form-control" name="duration" placeholder="Enter film Duration in minutes..." 
                                value="<%=DataUtility.getStringData(bean.getDuration())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("duration", request)%></font>
                        </div>
                        
                        
                        <div class="form-group">
                            <label for="name">Price</label>
                              <input type="text" id="input_price" class="form-control" name="price" placeholder="Enter Dvd Price..."
                              <%
											if(bean.getSaleType()!=null){
												if(bean.getSaleType().equalsIgnoreCase("Free") ){
												%>
												disabled="true"
												<% 	
												}
											}
										%>
                               
                                value="<%=(bean.getPrice()>0)?bean.getPrice():""%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("price", request)%></font>
                        </div>
                        
                        
                        <div class="form-group">
                            <label for="name">Image</label>
                           <input type="file" class="form-control" name="photo" placeholder="Upload Image Here..."
                             value="<%=OBSView.APP_CONTEXT%>/images/<%=bean.getImageName()%>">
                                <font color="red"> <%=ServletUtility.getErrorMessage("photo", request)%></font>
                        </div>
                        
                          <div class="form-group">
                            <label for="name">Pdf Name</label>
                           <input type="file" class="form-control" name="pdfName" placeholder="Upload PDF Here..."
                                value="<%=OBSView.APP_CONTEXT%>/pdfs/<%=bean.getPdfName()%>" >
                                <font color="red"> <%=ServletUtility.getErrorMessage("pdfName", request)%></font>
                        </div>
                       
                        
                      
                        
                        <div class="form-group">
                            <label for="message">Description</label>
                            <textarea rows="5" class="form-control" name="description" placeholder="Enter Dvd Description Here.." ><%=DataUtility.getStringData(bean.getDescription()) %></textarea>
                            	<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
                        </div>
                        <div class="mx-auto">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=DvdCtl.OP_SAVE%>">
                        <% if(bean.getId()>0){ %>
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=DvdCtl.OP_CANCEL%>">
                        <%}else{ %>
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=DvdCtl.OP_RESET%>">
                        <%} %>
                        </div>
                   
                </div>
            </div>
            
             </div>
        <div class="col-12 col-sm-4">
               

            </div>
        </div>
        </div>
        </form>
   

<br>

</body>
</html>