<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />

		<title>Ricerca cartelle</title>
	    
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
		<!-- Begin page content -->
		<main class="flex-shrink-0">
			<div class="container">
				
					<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					
					<div class='card'>
					    <div class='card-header'>
					        <h5>Inserisci i dati per la ricerca</h5> 
					    </div>
					    <div class='card-body'>
			
								<form method="post" action="list" class="row g-3">
								
									<div class="col-md-6">
										<label for="descrizione" class="form-label">Descrizione</label>
										<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione">
									</div>
										
									<div class="col-md-6">
										<label for="importo" class="form-label">Importo (euro)</label>
										<input type="number" class="form-control" name="importo" id="importo" placeholder="Inserire l'importo">
									</div>
									
									<div class="col-md-6">
										<label for="stato" class="form-label">Stato </label>
										<select class="form-select" id="stato" name="stato">
									    	<option value="" selected> - Selezionare - </option>
									   		<option value="CREATA" >CREATA</option>
									   		<option value="IN_VERIFICA" >IN VERIFICA</option>
									   		<option value="CONCLUSA" >CONCLUSA</option>
									    	<option value="IN_CONTENZIOSO" >IN CONTENZIOSO</option>
										</select>
									</div>
							
									<div class="col-md-6">
										<label for="contribuenteSearchInput" class="form-label">Contribuente:</label>
											<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="contribuenteSearchInput"
												name="contribuenteInput" value="${insert_cartella_attr.contribuente.nome}${empty insert_cartella_attr.contribuente.nome?'':' '}${insert_cartella_attr.contribuente.cognome}">
										<input type="hidden" name="contribuente.id" id="contribuenteId" value="${insert_cartella_attr.contribuente.id}">
									</div>
								
									<div class="col-12">	
										<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									</div>
										
									
								</form>
								
								<%-- FUNZIONE JQUERY UI PER AUTOCOMPLETE --%>
								<script>
									$("#contribuenteSearchInput").autocomplete({
										 source: function(request, response) {
										        $.ajax({
										            url: "../contribuente/searchContribuentiAjax",
										            datatype: "json",
										            data: {
										                term: request.term,   
										            },
										            success: function(data) {
										                response($.map(data, function(item) {
										                    return {
											                    label: item.label,
											                    value: item.value
										                    }
										                }))
										            }
										        })
										    },
										//quando seleziono la voce nel campo deve valorizzarsi la descrizione
									    focus: function(event, ui) {
									        $("#contribuenteSearchInput").val(ui.item.label)
									        return false
									    },
									    minLength: 2,
									    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
									    select: function( event, ui ) {
									    	$('#contribuenteId').val(ui.item.value);
									    	//console.log($('#contribuenteId').val())
									        return false;
									    }
									});
								</script>
								<!-- end script autocomplete -->	
					    
						<!-- end card-body -->			   
					    </div>
					<!-- end card -->
					</div>
				<!-- end container -->
				</div>	
		
		<!-- end main -->	
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>