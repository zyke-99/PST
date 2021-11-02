<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<p>Add doctor</p>
<form:form method="post" modelAttribute="diagnosis">

	<form:label path="patientId">Patient ID</form:label>
	<form:input path="patientId" type="text" required="required" />
	<form:errors path="patientId" />

	<form:label path="diagnosis">Diagnosis</form:label>
	<form:input path="diagnosis" type="text" required="required" />
	<form:errors path="diagnosis" />

	<form:label path="date">Date</form:label>
    <form:input path="date" type="date" required="required" />
    <form:errors path="date" />

	<form:select path="doctor">
	    <form:option value="NONE"> --SELECT--</form:option>
	    <c:forEach items="${doctors}" var="doctor">
	        <form:option value="${doctor}" label="${doctor.name}"/>
	    </c:forEach>
	</form:select>

	<button type="submit">Add</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>