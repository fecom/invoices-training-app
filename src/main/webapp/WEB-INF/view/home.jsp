<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--security sa dava ak riesim role jednotlivych uzivatelov--%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<%--velmi dolezite bez toho elignore to nebude fungovat!!!!!--%>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>

<%--================================================================================================================--%>
<p>
    User: <security:authentication property="principal.username"/>
    <br><br>
    Role: <security:authentication property="principal.authorities"/>
</p>
<%--================================================================================================================--%>


<%--================================================================================================================--%>
<%--secure authorize s tym access sa pouziva ked chcem aby to videl len ten co je prihlaseny ako manager alebo
ako dolu ze admin--%>
<security:authorize access="hasRole('MANAGER')">
    <p>
        <a href="${pageContext.request.contextPath}/managerisVymysleny">miesto pre managerov</a>
    </p>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
    <p>
        <a href="${pageContext.request.contextPath}/admin">miesto pre adminov</a>
    </p>
</security:authorize>
<%--================================================================================================================--%>



<%--================================================================================================================--%>
<%--ak dam inu adresu ako logout tak mi to nebude fungovat--%>
<form:form action="${pageContext.request.contextPath}/logout" method="post">

    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>
<%--================================================================================================================--%>
