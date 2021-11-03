<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <table border="1">
        <thead>
        <tr>
            <th>Id</th>
            <th>Vardas</th>
            <th>Telefono numeris</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vartotojai}" var="vartotojas">
            <tr>
                <td>${vartotojas.id}</td>
                <td>${vartotojas.vardas}</td>
                <td>${vartotojas.telNr}</td>
                <td><a type="button" href="/update-vartotojas/${vartotojas.id}">UPDATE</a></td>
                <td><a type="button" href="/delete-vartotojas/${vartotojas.id}">DELETE</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>
        <a type="button" href="add-vartotojas">Prideti vartotoja</a>
    </div>
</div>
<%@ include file="common/footer.jspf" %>