<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/head.jsp" %>
<!doctype html>
<html>
<head>
    <title>show name</title>
</head>
<body>

<%@include file="/WEB-INF/jsp/include/header.jsp" %>

<h2>test1</h2>
<form id="pageForm" name="pageForm" method="post" action="">
    <div>
        <table border="1">
            <c:forEach items="${pageInfo.list }" var="item" varStatus="vIndex">
                <tr>
                    <td><c:out value="${item.code }"></c:out></td>
                    <td><c:out value="${item.name }"></c:out></td>
                    <td><c:out value="${item.createTime }"></c:out></td>
                    <td><c:out value="${item.updateTime }"></c:out></td>
                </tr>
            </c:forEach>

            <c:forEach begin="${fn:length(pageInfo.list)}" end="9" varStatus="vIndex">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%@include file="/WEB-INF/jsp/include/page.jsp" %>
</form>
<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
</body>
</html>