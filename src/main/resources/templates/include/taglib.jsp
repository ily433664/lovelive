<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- <c:set var="ctxAdmin" value="${pageContext.request.contextPath}${fns:getAdminPath()}"/>
<c:set var="ctxStaff" value="${pageContext.request.contextPath}${fns:getStaffPath()}"/>
<c:set var="ctxMember1" value="${pageContext.request.contextPath}${fns:getMember1Path()}"/>
<c:set var="ctxMember2" value="${pageContext.request.contextPath}${fns:getMember2Path()}"/>
<c:set var="ctxPrincipal" value="${pageContext.request.contextPath}${fns:getPrincipalPath()}"/> --%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctxImages" value="${pageContext.request.contextPath}/images"/>