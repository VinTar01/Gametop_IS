  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


    <!DOCTYPE html>
<html lang="en">
<head>
    <title>Account | Gametop</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/Logo/logo-sito.gif">
    <link rel="stylesheet" href="style.css">
    
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
	
   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
	<script>
	
	function trySignIn(){
		const password_regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{7,}$/;
		const email_regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if($("#password").val().match(password_regex)) {
			if($("#email").val().match(email_regex)){
			$.ajax(
				{
					method: "POST",
					url: "RegistrazioneControl",
					data: {
						nome: $("#nome").val(),
						cognome: $("#cognome").val(),
						indirizzo: $("#indirizzo").val(),
						password: $("#password").val(),
						email: $("#email").val()
					},
					success: (data) => {
						window.location.replace("http://localhost:8080/GametopFV/successoRegistrazione.jsp");
					},
					error: (data) => {
						$("#signin-message").html("OPS!!� stato commesso un errore Email gi� esistente riprovare");
						$("#signin-message").css("color", "red");
					}
				}
			);
		} else {
			$("#signin-message").css("color", "red");
			$("#signin-message").html(
				"OPS, Formato email non valido.<br/>"
			);
		}
	}else {
		$("#signin-message").css("color", "red");
		$("#signin-message").html(
			"OPS, La password non rispetta i criteri.<br/>" +
			"Sono necessari almeno sette caratteri,<br/>" +
			"e almeno una lettera e un numero."
		);
		
	}
}	
		
	</script>
	
</head>

<body>

<!-- ------------------------------------ Header ------------------------------------  -->

    <%@ include file ="header.jsp" %>

<!-- ------------------------------------ Accesso ----------------------------------------  -->

    <section class="container-account">
<div class="login-box">
	
  	<h2>Registrati</h2>
  	<h3> Usa una password di almeno 7 caratteri con almeno una lettera ed un numero</h3>
  <form name='registrazione' onsubmit="trySignIn(); return false" >
    <div class="user-box">
      <input type="text" id="nome" required="">
      <label>Nome</label>
    </div>
    <div class="user-box">
      <input type="text" id="cognome" required="">
      <label>Cognome</label>
    </div>
    <div class="user-box">
      <input type="email" id="email" required="">
      <label>Email</label>
    </div>
    <div class="user-box">
      <input type="text" id="indirizzo" required="">
      <label>Indirizzo</label>
    </div>
    <div class="user-box">
      <input type="password" id="password" required="">
      <label>Password</label>
    </div>
    <div id="signin-message"></div>
    <input class="button" type="submit"  id="Signin-submit" value="Iscriviti">
  </form>
  
</div>


    </section>

<!-- ------------------------------------ Footer ----------------------------------------  -->

   <%@ include file ="/footer.jsp" %>

</body>

</html>>