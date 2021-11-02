<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<p>Add doctor</p>
<form:form method="post" modelAttribute="doctor">

	<form:label path="name">Name</form:label>
	<form:input path="name" type="text" required="required" />
	<form:errors path="name" />

	<form:label path="phoneNumber">Phone number</form:label>
	<form:input path="phoneNumber" type="text" required="required" />
	<form:errors path="phoneNumber" />

	<button type="submit">Add</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>