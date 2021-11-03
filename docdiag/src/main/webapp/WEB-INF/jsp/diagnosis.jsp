<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<p>Add doctor</p>
<form:form method="post" modelAttribute="diagnosis">

	<form:label path="patientId">Patient ID</form:label>
	<form:input path="patientId" type="text" required="required" />
	<form:errors path="patientId" />

	<form:label path="date">Date</form:label>
    <form:input path="date" type="date" required="required"/>
    <form:errors path="date" />

    <fmt:formatDate value="" pattern="yyyy-mm-dd" />
    <form:label path="diagnosisName">Diagnosis</form:label>
    <form:input path="diagnosisName" type="text" required="required"/>
    <form:errors path="diagnosisName" />

	<form:select path="doctorId">
	    <c:forEach items="${doctors}" var="doctor">
                <option value="${doctor.id}" ${doctor.id == diagnosis.doctorId ? 'selected="selected"' : ''}>${doctor.name}</option>
        </c:forEach>
	</form:select>

	<button type="submit">Add</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>