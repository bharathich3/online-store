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
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">


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
			
		</div>
		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- JavaScript -->
		<!-- jQuery -->
		<script src="${js}/jquery-1.10.2.js"></script>
		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>
		<!-- Self coded java Script -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>
