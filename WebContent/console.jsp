<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,gestioneProdotti.*" %>
    
<%
	Collection<?> consoleArticolo = (Collection<?>)request.getAttribute("Console");

	String error = (String)request.getAttribute("error");
	
	if(consoleArticolo == null && error == null){
		response.sendRedirect(response.encodeRedirectURL("./ConsoleControl"));
		return;
	}
	
	
%> 
  
<!DOCTYPE html>
<html>

<head>
	<link href="style.css" rel="stylesheet" type="text/css">
	<link href="styleNavBar.css" rel="stylesheet" type="text/css">
	<link rel="shortcut icon" type="image/x-icon" href="image/Logo/logo-sito.gif">
	<title>Console| Gametop</title>
	
	      <meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="pragma" content="no-cache" />
	
	<%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);

	%>
</head>

<body>

<%@ include file ="/header.jsp" %>
    
    <section class="banner-console">
	    <div class="text-banner">
	        <a class="button" href="#t-shirt"><b>Le migliori Console</b></a>
	    </div> 
	</section>

	

	<section class="vetrina">
	
	<%
		if(consoleArticolo != null && consoleArticolo.size() > 0){
		Iterator<?> it = consoleArticolo.iterator();
		%>
		
		<%	
			while(it.hasNext()){
	%>
		
	<%
				//for(int i=0;it.hasNext();i++){
				ShopBean bean = (ShopBean)it.next();
				
	%>
			<%if(bean.getQuantitaProdotto()>0){ %>
		   	<div class="vetrina-item">
		   		<a href="prodotto.jsp?copertina=<%=bean.getCopertina()%>&titolo=<%= bean.getTitolo()%>&codice=<%=bean.getCodiceProdotto()%>&descrizione=<%=bean.getDescrizione()%>&prezzo=<%=bean.getPrezzo()%>" > 
		    	<img src="<%= bean.getCopertina()%>" onerror="this.src='./image/noimage.png'" alt="user-1"></a>
		        <h4><%= bean.getTitolo()%></h4>
		        <p><%= bean.getDescrizione()%></p>
		        <p><b>&euro;<%= bean.getPrezzo()%></b></p>
		         <a class="button" href="<%=response.encodeURL("CarrelloControl?action=addCart&id="+bean.getCodiceProdotto()) %>"  ><b>Aggiungi al carrello</b></a>
		      		
          	
		    </div>
		    <%}else if(bean.getQuantitaProdotto()==0){ %>
		    <div class="vetrina-item">
		   		
		    	<img src="<%= bean.getCopertina()%>" onerror="this.src='./image/noimage.png'" alt="user-1">
		        <h4><%= bean.getTitolo()%></h4>
		        <p><%= bean.getDescrizione()%></p>
		        <p><b>&euro;<%= bean.getPrezzo()%></b></p>
		         <p style="color:red;">Prodotto Esaurito</p>
		    </div>
		    
		    <%} %> 
				<%
				}
				%>
		
	 	<%
			}
		
		%>
	</section>	
	
	   <%@ include file ="footer.jsp" %>
	   

</body>

</html>