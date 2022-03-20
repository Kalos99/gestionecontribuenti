<!doctype html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Visualizza Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio</h5>
					    </div>
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nome</dt>
							  <dd class="col-sm-9">${ show_contribuente_attr.nome }</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Cognome:</dt>
							  <dd class="col-sm-9">${ show_contribuente_attr.cognome  }</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di nascita:</dt>
							  <fmt:formatDate value="${show_contribuente_attr.dataDiNascita}" type="date" pattern="dd/MM/yyyy" var="theFormattedDate" />
							  <dd class="col-sm-9">${ theFormattedDate }</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Codice fiscale:</dt>
							  <dd class="col-sm-9">${ show_contribuente_attr.codiceFiscale }</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Indirizzo:</dt>
							  <dd class="col-sm-9">${ show_contribuente_attr.indirizzo }</dd>
					    	</dl>
					    	
					    	<dl class="row">
					  			<dt class="col-sm-3 text-right">Importo totale cartelle(euro ):</dt>
					  			<dd class="col-sm-9">${show_contribuente_attr.calcolaImportoTotaleCartelle() }</dd>
			    			</dl>
			    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Importo pagato(euro ):</dt>
							  <dd class="col-sm-9">${show_contribuente_attr.calcolaImportoPagato() }</dd>
					    	</dl>
					    	
					    	<c:if test="${show_contribuente_attr.isInContenzioso() }">
					    		<dl class="row">
								  <dt class="col-sm-3 text-right text-danger">Importo(euro ):</dt>
								  <dd class="col-sm-9 text-danger">${show_contribuente_attr.calcolaImportoInContenzioso() }</dd>
						    	</dl>
					    	</c:if>
					    	
					    </div>
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath}/contribuente" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>