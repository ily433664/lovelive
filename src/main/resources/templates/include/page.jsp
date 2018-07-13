<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <div>
        <c:if test="${pageInfo.hasPreviousPage }">
            <button onclick="javascript:toPage('${pageInfo.prePage }')">
                <i>上一页</i>
            </button>
        </c:if>

        <c:forEach begin="${pageInfo.pageNum - 3 > 0 ? pageInfo.pageNum - 3 : 1}"
                   end="${pageInfo.pageNum + 3 < pageInfo.pages ? pageInfo.pageNum + 3 : pageInfo.pages}" var="item"
                   varStatus="status">
            <c:if test="${status.count == 1 &&  item != 1}"><span>...</span></c:if>
            <button onclick="toPage('${item}')">${item}</button>
            <c:if test="${status.last && item != pageInfo.pages}"><span>...</span></c:if>
        </c:forEach>


        <c:if test="${pageInfo.hasNextPage }">
            <button onclick="javascript:toPage('${pageInfo.nextPage }')">
                <i>下一页</i>
            </button>
        </c:if>
    </div>
    <div>
        共 <span><fmt:formatNumber value="${pageInfo.pages}" pattern="#"/></span>
        页，每页显示<input id="pageSize" value='${pageInfo.pageSize }'> 条，共 <span><fmt:formatNumber value="${pageInfo.total }"
                                                                                              pattern="#"/></span> 条，到第
        <input id="pageIndex" value='${pageInfo.pageNum }'> 页
        <button onclick="javascript:toPageIndex()">确定</button>
    </div>
    <input type="hidden" id="pageNo" name="pageNo" value="${pageInfo.pageNum}"/>
    <input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize}"/>
    <script>
        function toPage(p) {
            $("#pageNo").val(p);
            $("#pageSize").val($("#pageSize").val());
            doAction('pageForm', '', '');
        }

        function toPageIndex() {
            var ival = parseInt($("#pageIndex").val());
            if (!isNaN(ival)) {
                $("input[name='pageNo']").val(ival);
            }
            ival = parseInt($("#pageSize").val());
            if (!isNaN(ival)) {
                $("input[name='pageSize']").val(ival);
            }
            doAction('pageForm', '', '');
        }
    </script>
</div>