<!DOCTYPE html>
<%@page import="it.gestionearticolijspservletjpamaven.model.Articolo"%>
<html>
<head>
<jsp:include page="../header.jsp" />
<title>Insert title here</title>
<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<%
			Articolo articoloInPagina = (Articolo) request.getAttribute("elimina_articolo_attr");
		%>

		<div class="card">
			<h5 class="card-header">Elimina</h5>
			<div class="card-body">
				<form method="post"
					action="ExecuteDeleteArticoloServlet?idArticolo=<%=articoloInPagina.getId()%>">
					<h5 class="card-title">
						Stai procedendo all'eliminazione dell'articolo <%=articoloInPagina.getId()%></h5>
					<p class="card-text">Vuoi procedere nell'eliminazione?</p>
					<button type="submit" name="submit" id="submit"
						class="btn btn-danger">Delete</button>
				</form>
			</div>
		</div>

	</main>

	<jsp:include page="../footer.jsp" />
</body>
</html>