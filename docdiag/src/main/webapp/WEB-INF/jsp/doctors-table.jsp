<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone number</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${doctors}" var="doctor">
            <tr>
                <td>${doctor.id}</td>
                <td>${doctor.name}</td>
                <td>${doctor.phoneNumber}</td>
                <td><a type="button" href="/update-doctor/${doctor.id}">UPDATE</a></td>
                <td><a type="button" href="/delete-doctor/${doctor.id}">DELETE</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div>
<a href="add-doctor">Add</a>
</div>
<%@ include file="common/footer.jspf"%>