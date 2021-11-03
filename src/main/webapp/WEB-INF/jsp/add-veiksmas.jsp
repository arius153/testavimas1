<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <form:form method="post" modelAttribute="veiksmas">
        <c:if test="${veiksmas.id == null}">
            <p>Prideti nauja veiksma:</p>
        </c:if>
        <c:if test="${veiksmas.id != null}">
            <p>Atnaujinti veiksma su id ${veiksmas.id}:</p>
        </c:if>
        <form:input path="id" type="hidden" required="required" />
        <form:errors path="id" />

        <form:label path="veiksmas">Veiksmas</form:label>
        <form:input path="veiksmas" type="text" required="required" />
        <form:errors path="veiksmas" />

        <form:label path="data">data</form:label>
        <form:input path="data" type="date" required="required" />
        <form:errors path="data" />


        <form:label path="vartotojoId">Vartotojas</form:label>
        <form:select path="vartotojoId">
            <c:forEach items="${vartotojai}" var="vartotojas">
                <form:option value="${vartotojas.id}">${vartotojas.id}. ${vartotojas.vardas}</form:option>
            </c:forEach>
        </form:select>

        <button type="submit">OK</button>
    </form:form>
</div>
<%@ include file="common/footer.jspf"%>