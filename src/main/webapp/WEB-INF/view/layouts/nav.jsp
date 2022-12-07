<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form action="${pageContext.request.contextPath}/logout" method="POST" name="frmLogout"></form:form>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}"><spring:message code="page.home" /></a></li>
        <security:authorize access="hasRole('MANAGER')">
            <li><a href="${pageContext.request.contextPath}/product/list">สินค้า</a></li>
        <li><a href="${pageContext.request.contextPath}/shop/list">ร้านค้า</a></li>
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <li><a href="${pageContext.request.contextPath}/member/list">ผู้ใช้</a></li>
            <li><a href="${pageContext.request.contextPath}/category/list">ประเภทสินค้า</a></li>
        </security:authorize>
        <security:authorize access="!isAuthenticated()">
            <li><a href="${pageContext.request.contextPath}/login">ลงชื่อเข้าสู่ระบบ</a></li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <li>ชื่อผู้ใช้:
                <a href="${pageContext.request.contextPath}/user/profile">
                    <security:authentication property="principal.username" />
                </a>
            </li>
            <li><a onclick="javascript: frmLogout.submit();">ลงชื่อออกจากระบบ</a></li>
        </security:authorize>
    </ul>
</nav>
<c:if test="${errors != null}" >
    <p class="error center">${errors}</p>
</c:if>
<c:if test="${status != null}" >
    <p class="info center">${status}</p>
</c:if>
<hr>