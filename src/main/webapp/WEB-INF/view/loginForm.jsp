<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--velmi dolezite bez toho elignore to nebude fungovat!!!!!--%>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Login</title>

<%--================================================================================================================--%>
    <style>
        .failed{
            color:red;
        }
        .logout{
            color: green;
        }
    </style>
<%--================================================================================================================--%>

</head>
<h3>Login</h3>
<body>

<%--authenticateUser je z triedy ConfigurationUserRole.class metoda configuration--%>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">

<%--================================================================================================================--%>
<%--    to error je to co sa mi objavi hore v url adrese--%>
    <c:if test="${param.error != null}">
        <i class="failed">Mas nespravny username alebo password!!!</i>
    </c:if>

    <%--    to logout je to co sa mi objavi hore v url adrese
    ale neviem preco prave logout nemozem to inak nazvat to iste hore neviem preco prave error.......
    ma tonieco s tym ze to je odkazovane na defaultnu url adresu logout a preto musim dat rovnaku
    adresu aj v home.jsp cize zase len /logout--%>
    <c:if test="${param.logout != null}">
        <i class="logout">Bol si uspesne odhlaseny</i>
    </c:if>
<%--================================================================================================================--%>

    <p>
        User name: <input type="text" name="username"/>
    </p>
    <p>
        Password:  <input type="text" name="password"/>
    </p>

    <input type="submit" value="Login">


<%--================================================================================================================--%>
<%--    ak z nejakeho dovodu nechcem pouzivat zdvojeny form akoze form:form tak musim pridat
anualen nejaky druh tokenu ktory sa inak automaticky pridava na stranke pretoze ta jsp preklada do html
kde existuje len jeden form cize ak pridam kod nizsie mozem alebo muusim odstranit ten jeden form--%>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--================================================================================================================--%>

</form:form>
</body>
</html>
