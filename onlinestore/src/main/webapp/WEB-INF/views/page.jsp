<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Store - ${title}</title>

<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot= '${contextRoot}';

	
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap DataTables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


<!-- Add custom CSS here -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">
			<!-- Loading the Home Content -->
			<c:if test="${userClickHome==true}">
				<%@ include file="home.jsp"%>

			</c:if>
			<!-- Load only when user clicks About -->
			<c:if test="${userClickAbout==true}">
				<%@ include file="about.jsp"%>

			</c:if>
			<!-- Load only when user clicks Contact -->
			<c:if test="${userClickContact==true}">
				<%@ include file="contact.jsp"%>

			</c:if>
			<!-- Load only when user clicks All products or Category Products -->
			<c:if test="${userClickAllProducts==true or userClickCategoryProducts == true}">
				<%@ include file="listProducts.jsp"%>

			</c:if>
			<!-- Load only when user clicks Show Product -->
			<c:if test="${userClickShowProduct==true}">
				<%@ include file="singleProduct.jsp"%>

			</c:if>
			<!-- Load only when user clicks Manage Product -->
			<c:if test="${userClickManageProducts==true}">
				<%@ include file="manageProducts.jsp"%>

			</c:if>
		</div>
		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- JavaScript -->
		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>
		
		<!-- jQuery Validator -->
		<script src="${js}/jquery.validate.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>
		
		<!-- DataTable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
	
	 	<!-- DataTable Bootstrap Script -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- BootBox  -->
		<script src="${js}/bootbox.min.js"></script>
	
		<!-- Self coded java Script -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>
