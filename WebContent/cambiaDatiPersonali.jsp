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
        function tryUpdatePersonalData(){
            // Implementa la logica per l'aggiornamento dei dati personali
            // Puoi utilizzare AJAX o una semplice submit del form
            // Aggiorna l'URL e i parametri in base alle tue esigenze
            
            $.ajax(
                {
                    method: "POST",
                    url: "AccountModificaDatiControl",  // Assicurati che l'URL sia corretto
                    data: {
                        // Aggiungi i parametri necessari per l'aggiornamento dei dati personali
                        nome: $("#nuovoNome").val(),
                        cognome: $("#nuovoCognome").val(),
                        // Aggiungi altri campi se necessario
                    },
                    success: (data) => {
                        window.location.replace("http://localhost:8080/GametopFV/successoCambioDati.jsp");
                    },
                    error: (data) => {
                        $("#update-message").html("Errore durante l'aggiornamento dei dati personali");
                        $("#update-message").css("color", "red");
                    }
                }
            );
        }
    </script>
    
</head>

<body>

<!-- ------------------------------------ Header ------------------------------------  -->

<%@ include file ="header.jsp" %>

<!-- ------------------------------------ Modifica dati personali ----------------------------------------  -->

<section class="container-account">
    <div class="login-box">
        <h2>Modifica Dati Personali</h2>
        <form name='modifica-dati' onsubmit="tryUpdatePersonalData(); return false">
            <div class="user-box">
                <input type="text" id="nuovoNome" required="">
                <label>Nuovo Nome</label>
            </div>
            <div class="user-box">
                <input type="text" id="nuovoCognome" required="">
                <label>Nuovo Cognome</label>
            </div>
            <!-- Aggiungi altri campi per la modifica dei dati personali -->
            <div id="update-message"></div>
            <input class="button" type="submit" id="Update-submit" value="Conferma Modifica">
        </form>
    </div>
</section>

<!-- ------------------------------------ Footer ----------------------------------------  -->

<%@ include file="/footer.jsp" %>

</body>
</html>
