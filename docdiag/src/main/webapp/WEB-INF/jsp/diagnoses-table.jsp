<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Patient ID</th>
            <th>Doctor ID</th>
            <th>Doctor name</th>
            <th>Diagnosis</th>
            <th>Date</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${diagnoses}" var="diagnosis">
            <tr>
                <td>${diagnosis.id}</td>
                <td>${diagnosis.patientId}</td>
                <td>${diagnosis.doctor.id}</td>
                <td>${diagnosis.doctor.name}</td>
                <td>${diagnosis.diagnosis}</td>
                <td>${diagnosis.date}</td>
                <td><a type="button" href="/update-diagnosis/${diagnosis.id}">UPDATE</a></td>
                <td><a type="button" href="/delete-diagnosis/${diagnosis.id}">DELETE</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div>
<a href="add-diagnosis">Add</a>
</div>
<%@ include file="common/footer.jspf"%>