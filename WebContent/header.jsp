<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <!-- Favicon -->
  <link rel="shortcut icon" href="./images/favicon.ico" type="image/x-icon"> <!-- aggiunge l'immagine al browser -->
  <!-- Box icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />

  <!-- Custom StyleSheet -->
  <link rel="stylesheet" href="styleNavBar.css" />
  
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

<title>Insert title here</title>
</head>
<body>

  <!-- Header -->
  <header class="corpo">
    <!-- Navigation -->
    <nav class="nav">
      <div class="navigation container">
        <div class="logo">
          <h1>Gametop</h1>
        </div>

        <div class="menu">
          <div class="top-nav">
            <div class="logo">
              <h1>Gametop</h1>
            </div>
            <div class="close">
              <i class="bx bx-x"></i>
            </div>
          </div>
          <% Integer cont=0;
            if(session.getAttribute("conto")!=null){
            	String c = (String) request.getSession().getAttribute("conto");
            cont = Integer.valueOf(c);}
            else{
            cont=0;
            } %>
	         <%
	         String s= (String) session.getAttribute("email");
	if(session.getAttribute("nome") == null){
		%> 
             <ul class="nav-list , Opzioni">
            <li class="nav-item">
              <a href="index.jsp" class=" Opzioni-effect">Home</a>
            </li>
            <li class="nav-item">
              <a href="console.jsp" class="Opzioni-effect">Console</a>
            </li>
            <li class="nav-item">
              <a href="videogiochi.jsp" class="Opzioni-effect">Videogiochi</a>
            </li>
            <li class="nav-item">
              <a href="accessori.jsp" class="Opzioni-effect">Accessori</a>
            </li>
            <li class="nav-item">
              <a href="login.jsp" class="Opzioni-effect">Login</a>
            </li>
            <li class="nav-item">
               <!-- <div class="circle2"><%=cont%></div> --> 
              <a href="carrello.jsp" class="nav-link icon"><i class="bx bx-shopping-bag"></i></a>
            </li>
          </ul>
          <%}
	         
			else if(session.getAttribute("nome") != null && s.equals("admin@gmail.com")){
		%>
		<ul class="nav-list , Opzioni">
            <li class="nav-item">
              <a href="index.jsp" class=" Opzioni-effect">Home</a>
            </li>
           <li class="nav-item">
              <a href="console.jsp" class="Opzioni-effect">Console</a>
            </li>
            <li class="nav-item">
              <a href="videogiochi.jsp" class="Opzioni-effect">Videogiochi</a>
            </li>
            <li class="nav-item">
              <a href="accessori.jsp" class="Opzioni-effect">Accessori</a>
            </li>
            <li class="nav-item">
              <a class="Opzioni-effect" href="<%=response.encodeURL("LogoutControl") %>">Logout</a>
            <li class="nav-item">
              <a href="account.jsp" class="Opzioni-effect">Account</a>
            </li>
            <li class="nav-item">
               <!-- <div class="circle2"><%=cont%></div> --> 
              <a href="carrello.jsp" class="nav-link icon"><i class="bx bx-shopping-bag"></i></a>
            </li>
          </ul>
		
		<%} else if(session.getAttribute("nome") != null ){
			%>
			<ul class="nav-list , Opzioni">
	            <li class="nav-item">
	              <a href="index.jsp" class=" Opzioni-effect">Home</a>
	            </li>
	            <li class="nav-item">
              <a href="console.jsp" class="Opzioni-effect">Console</a>
            </li>
            <li class="nav-item">
              <a href="videogiochi.jsp" class="Opzioni-effect">Videogiochi</a>
            </li>
            <li class="nav-item">
              <a href="accessori.jsp" class="Opzioni-effect">Accessori</a>
            </li>
	            <li class="nav-item">
	             <a class="Opzioni-effect" href="<%=response.encodeURL("LogoutControl") %>">Logout</a>
	            </li>
	      
	            <li class="nav-item">
	              <a href="account.jsp" class="Opzioni-effect">Account</a>
	            </li>
	            <li class="nav-item">
	               <!-- <div class="circle2"><%=cont%></div> --> 
	              <a href="carrello.jsp" class="nav-link icon"><i class="bx bx-shopping-bag"></i></a>
	            </li>
	          </ul>
			
			<%}%>
        </div>
       
		
		
	

        <a href="carrello.jsp" class="cart-icon">
          <i class="bx bx-shopping-bag"></i>
        </a>

        <div class="hamburger">
          <i class="bx bx-menu"></i>
        </div>
      </div>
    </nav>
  </header>
<!-- GSAP -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.5.1/gsap.min.js"></script>
  <!-- Custom Script -->
  <script src="indexNavBar.js"></script>
</body>
</html>