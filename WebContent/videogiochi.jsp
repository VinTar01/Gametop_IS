<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,gestioneProdotti.*" %>
    
<%
	Collection<?> videogiochiArticolo = (Collection<?>)request.getAttribute("Videogiochi");

	String error = (String)request.getAttribute("error");
	
	if(videogiochiArticolo == null && error == null){
		response.sendRedirect(response.encodeRedirectURL("./VideogiochiControl"));
		return;
	}
%> 
  
<!DOCTYPE html>
<html>

<head>
	<link href="style.css" rel="stylesheet" type="text/css">
	<link rel="shortcut icon" type="image/x-icon" href="image/Logo/logo-sito.gif">
	<title>Videogiochi| Gametop</title>
</head>

<body>


    <%@ include file ="header.jsp" %>

<!-- ------------------------------------ Banner Videogiochi ----------------------------------------  -->

<section class="banner-videogiochi">
    <div class="text-banner">
        
        <a class="button" href="#t-shirt"><b>Esplora ora VIDEOGIOCHI</b></a>
    </div> 
</section>

<!-- ------------------------------------ Nav-interna -------------------------------------  -->


	<section class="vetrina">
	<%
		if(videogiochiArticolo != null && videogiochiArticolo.size() > 0){
		Iterator<?> it = videogiochiArticolo.iterator();
		%>
	
		<%	
			while(it.hasNext()){
	%>
		
	<%
				
				ShopBean bean = (ShopBean)it.next();
	%>
		  <%if(bean.getQuantitaProdotto()>0){ %>
		   	<div class="vetrina-item">
		   		<a href="prodotto.jsp?copertina=<%=bean.getCopertina()%>&titolo=<%= bean.getTitolo()%>&codice=<%=bean.getCodiceProdotto()%>&descrizione=<%=bean.getDescrizione()%>&prezzo=<%=bean.getPrezzo() %>" 
> 
		    	<img src="<%= bean.getCopertina()%>" onerror="this.src='./image/noimage.png'" alt="user-1"></a>
		        <h4><%= bean.getTitolo()%></h4>
		        <p><%= bean.getDescrizione()%></p>
		        <p><b>&euro;<%= bean.getPrezzo()%></b></p>
		         <a class="button" href="<%=response.encodeURL("CarrelloControl?action=addCart&id="+bean.getCodiceProdotto()) %>"  ><b>Aggiungi al carrello</b></a>
		      		
          	
		    </div>
		    <%}else if(bean.getQuantitaProdotto()==0){ %>
		    <div class="vetrina-item">
		   		
		    	<img src="<%= bean.getCopertina()%>" onerror="this.src='./image/noimage.png'" alt="user-1"></a>
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