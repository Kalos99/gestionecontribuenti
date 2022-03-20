<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Modifica contribuente</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="insert_regista_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci i dati per l'aggiornamento del contribuente</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="edit_contribuente_attr" method="post" action="${pageContext.request.contextPath }/contribuente/update" novalidate="novalidate" class="row g-3">
								<form:hidden path="id"/>			
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome <span class="text-danger">*</span></label>
									<spring:bind path="nome">
									<input type="text" name="nome" id="nome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il nome" value="${edit_contribuente_attr.nome }" required>
									</spring:bind>
									<form:errors  path="nome" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome <span class="text-danger">*</span></label>
									<spring:bind path="cognome">
									<input type="text" name="cognome" id="cognome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il cognome" value="${edit_contribuente_attr.cognome }" required>
									</spring:bind>
									<form:errors  path="cognome" cssClass="error_field" />
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${edit_contribuente_attr.dataDiNascita}' />
								<div class="col-md-6">
									<label for="dataDiNascita" class="form-label">Data di Nascita <span class="text-danger">*</span></label>
                        			<spring:bind path="dataDiNascita">
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataDiNascita" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataDiNascita" required 
	                            		value="${parsedDate}" >
		                            </spring:bind>
	                            	<form:errors  path="dataDiNascita" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="codiceFiscale" class="form-label">Codice fiscale <span class="text-danger">*</span></label>
									<spring:bind path="codiceFiscale">
									<input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="codiceFiscale" id="codiceFiscale" placeholder="Inserire il codice fiscale" value="${edit_contribuente_attr.codiceFiscale }" required>
									</spring:bind>
									<form:errors  path="codiceFiscale" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="indirizzo" class="form-label">Indirizzo <span class="text-danger">*</span></label>
									<spring:bind path="indirizzo">
									<input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="indirizzo" id="indirizzo" placeholder="Inserire l'indirizzo" value="${edit_contribuente_attr.indirizzo }" required>
									</spring:bind>
									<form:errors  path="indirizzo" cssClass="error_field" />
								</div>
								
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							</div>
		
						</form:form>
  
				    
				    
					<!-- end card-body -->			   
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