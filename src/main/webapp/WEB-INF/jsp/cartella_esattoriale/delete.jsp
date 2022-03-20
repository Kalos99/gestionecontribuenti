<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Elimina cartella</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
	  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  		${errorMessage}
				 		 <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Sei sicuro di voler eliminare questa cartella?</h5>
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${delete_cartella_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Descrizione:</dt>
					  <dd class="col-sm-9">${delete_cartella_attr.descrizione}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Importo(euro ):</dt>
					  <dd class="col-sm-9">${delete_cartella_attr.importo}</dd>
			    	</dl>
			    	
			    	<dl class="row">
						  <dt class="col-sm-3 text-right">Stato:</dt>
						  <dd class="col-sm-9">${delete_cartella_attr.stato}</dd>
					   	</dl>
			    	
			    	<!-- info Regista -->
			    	<p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Info Contribuente
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  	<dl class="row">
						  <dt class="col-sm-3 text-right">Nome:</dt>
						  <dd class="col-sm-9">${delete_cartella_attr.contribuente.nome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Cognome:</dt>
						  <dd class="col-sm-9">${delete_cartella_attr.contribuente.cognome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Codice fiscale:</dt>
						  <dd class="col-sm-9">${delete_cartella_attr.contribuente.codiceFiscale}</dd>
					   	</dl>
					    
					  </div>
					<!-- end info Regista -->
					</div>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <form method="post" action="${pageContext.request.contextPath }/cartella_esattoriale/remove" class="row g-3" novalidate="novalidate">
					     	<div class='card-footer'>
					        	<input type="hidden" name="idCartella" value="${delete_cartella_attr.id}">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<a href="${pageContext.request.contextPath }/cartella_esattoriale" class='btn btn-outline-secondary' style='width:80px'>
					            	<i class='fa fa-chevron-left'></i> Back
					        	</a>
							
					  	  </div>
						<!-- end card -->			  
			    	</form>
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>