<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <form:form method="post" modelAttribute="vartotojas">
        <c:if test="${vartotojas.id == null}">
            <p>Prideti nauja vartotoja:</p>
        </c:if>
        <c:if test="${vartotojas.id != null}">
            <p>Atnaujinti vartotojas su id ${vartotojas.id}:</p>
        </c:if>
        <form:input path="id" type="hidden" required="required" />
        <form:errors path="id" />

        <form:label path="vardas">Vardas</form:label>
        <form:input path="vardas" type="text" required="required" />
        <form:errors path="vardas" />

        <form:label path="telNr">Telefono numeris</form:label>
        <form:input path="telNr" type="text" required="required" />
        <form:errors path="telNr" />
        <button type="submit">OK</button>
    </form:form>
</div>
<%@ include file="common/footer.jspf"%>