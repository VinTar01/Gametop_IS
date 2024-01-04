<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
 	<link href="style.css" rel="stylesheet" type="text/css">
 	<meta name="viewport" content="width=device-width, initial-scale=1"/>
 	<link rel="shortcut icon" type="image/x-icon" href="image/Logo/logo-sito.gif">
    <title>Homepage | Gametop HomePage</title>
    
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

<%@ include file ="headerNavBar.jsp" %>
    
<!-- ------------------------------------ Banner ----------------------------------------  -->


<!-- ------------------------------------ Categorie -------------------------------------  -->
    
<section class="categories">
    <h2>Le Nostre Proposte</h2>

    <div class="categories-row">

        <div class="categories-item">
            <img src="image/sfondo-co.jpg" alt="console">
            <a class="button" href="console.jsp"><b>COMPRA</b><br><b>CONSOLE</b></a>
        </div>
    
        <div class="categories-item">
            <img src="image/sfondo-vd.jpg"  alt="videogiochi">
            <a class="button" href="videogiochi.jsp"><b>COMPRA</b><br><b>VIDEOGIOCHI</b></a>
        </div>
    
        <div class="categories-item">
            <img src="image/sfondo-ac.jpg"  alt="accessori">
            <a class="button" href="accessori.jsp"><b>COMPRA</b><br><b>ACCESSORI</b></a>
        </div>

    </div>

</section>

<!-- ------------------------------------ Recensioni ------------------------------------  -->
	
    <section class="recensioni">
        <h1>Cosa dicono i nostri clienti</h1>
        <div class="recensioni-row">

            <div class="recensioni-item">
                <img src="image/user-1.png" alt="user-1">
                <h4>Asia Modello</h4>
                <p class>Mi sono trovata benissimo pagina eccellente.</p>
            </div>

            <div class="recensioni-item">
                <img src="image/user-2.png" alt="user-2">
                <h4>Mario Biondi</h4>
                <p class>Fantastica!! Sono veramente contento di aver scoperto questa incredibile pagina</p>
            </div>

            <div class="recensioni-item">
                <img src="image/user-3.png" alt="user-3">
                <h4>Lucia Bonetti</h4>
                <p class>La migliore pagina sul mercato</p>
            </div>

        </div>
    </section>

<!-- ------------------------------------ Newsletter ------------------------------------ -->

    <section class="newsletter">
        <div class="newsletter-row">

            <div class="newsletter-item">
                <h1>REGISTRATI SUBITO</h1>
                <h3>INSERISCI LA TUA E-MAIL PER RIMANERE AGGIORNATO SU TUTTE LE NOSTRE NOVITÀ E PROMOZIONI</h3>
            </div>

            <div class="newsletter-form">
                <a class="button" href="registrazione.jsp"><b>Iscriviti</b></a>
            </div>

        </div>
    </section>

<%@ include file ="footer.jsp" %>

</body>

</html>