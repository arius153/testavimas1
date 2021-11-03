<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Veiksmas</th>
            <th>data</th>
            <th>Vartotojo id</th>
            <th>Vartotojo vardas</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${veiksmai}" var="veiksmas">
            <tr>
                <td>${veiksmas.id}</td>
                <td>${veiksmas.veiksmas}</td>
                <td>${veiksmas.data}</td>
                <td>${veiksmas.vartotojas.id}</td>
                <td>${veiksmas.vartotojas.vardas}</td>
                <td><a type="button" href="/update-veiksmas/${veiksmas.id}">UPDATE</a></td>
                <td><a type="button" href="/delete-veiksmas/${veiksmas.id}">DELETE</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>
        <a href="add-veiksmas">Prideti veiksma</a>
    </div>
</div>
<%@ include file="common/footer.jspf" %>