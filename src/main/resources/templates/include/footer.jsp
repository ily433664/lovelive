<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="foot">
    <span><c:out value="${fns:getProductName()}"/>&nbsp;<c:out value="${fns:getVersion()}"/></span>
    <div class="copyRight">@ <c:out value="${fns:getUserUnit()}"/>版权所有 - Powered by <c:out
            value="${fns:getCompanyEnName()}"/></div>
    <div class="clear"></div>
</div>