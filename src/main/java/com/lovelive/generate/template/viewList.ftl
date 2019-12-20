<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>${functionName}管理</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${r"${ctxStatic}"}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${r"${ctxStatic}"}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="${r"${ctxStatic}"}/css/plugins/dataTables/dataTables.bootstrap.min.css">
    <link href="${r"${ctxStatic}"}/css/AdminLTE.min.css" rel="stylesheet">
    <link href="${r"${ctxStatic}"}/css/common.css" rel="stylesheet">
    <%--<link href="${r"${ctxStatic}"}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">--%>

    <!-- jQuery 2 -->
    <script type="text/javascript" src="${r"${ctxStatic}"}/js/jquery-2.2.3.min.js"></script>
</head>
<body class="inner inner2">
<div class="content-header">
    <h1>
        <i class="fa  fa-address-card sign"></i>
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="javascript:void(0);" onclick="return top.updateIframe('${r"${ctx}"}/index')">首页</a></li>
    </ol>
</div>
<div class="list">
    <form:form id="searchForm" modelAttribute="${className}" action="${r"${ctx}"}/${urlPrefix}/" method="post"
               class="msgForm">
        <input id="pageNo" name="pageNo" type="hidden" value="${r"${page.pageNo}"}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${r"${page.pageSize}"}"/>
        <div class="msgInputBox">
            <div>
                <div class="hang clearfix">
                    <div class="form-group">
                        <label>名称 ：</label>
                        <form:input path="name" htmlEscape="false" maxlength="50" class="input-small form-control"/>
                    </div>
                </div>
                <div class="hang clearfix">
                    <label>下拉框：</label>
                    <form:select path="invisible" cssClass="form-control" maxlength="20">
                        <form:option value="" label="-全部-"/>
                        <form:option value="1" label="选项1"/>
                        <form:option value="2" label="选项2"/>
                    </form:select>
                </div>
            </div>
        </div>
        <tags:message content="${r"${message}"}"/>
        <div class="fixTop0">
            <div class="btnGroup fixTop">
                <button id="btnSubmit" class="btn btn-primary btn-sm" type="submit">查询</button>
                <shiro:hasPermission name="${permissionPrefix}:edit">
                    <button value="${r"${ctx}"}/${urlPrefix}/form" class="btn btn-primary btn-sm" type="button"
                       onclick="top.updateIframe(this.value);">添加</button>
                </shiro:hasPermission>
                <div class="right-btn-list">
                    <div class="btn-list btn-group" style="display: inline-block;float: right;">
                        <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"
                                aria-expanded="true"><i class="fa fa-list"></i></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" id="columnSelect">
                        </ul>
                    </div>
                    <button type="button" class="btn btn-default btn-sm btn-page" <c:if test="${r"${page.pageNo * page.pageSize >= page.count }"}">disabled</c:if> onclick="page(${r"${page.next}"}, ${r"${page.pageSize}"})">下一页</button>
                    <button type="button" class="btn btn-default btn-sm btn-page" <c:if test="${r"${page.pageNo eq 1}"}">disabled</c:if> onclick="page(${r"${page.prev}"}, ${r"${page.pageSize}"})">上一页</button>
                </div>
            </div>
        </div>

        <div class="box">
            <div class="box-body">
                <div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="contentTable" class="table table-bordered table-responsive mailbox-messages dataTable table_hover" role="grid">
                                <thead>
                                <tr role="row">
                                    <%--不可变动的列添加data-switchable="false"--%>
                                    <th class="text-center" data-switchable="false">
                                        <input type="checkbox" name="allids" class="checkbox-toggle i-checks"/>
                                    </th>
                                    <jsp:include flush="true" page="/static/template/sortColumns.jsp">
                                        <jsp:param name="formId" value="searchForm"/>
                                        <jsp:param name="columns" value="name,createDate,remarks"/>
                                        <jsp:param name="columnLabels" value="名称,创建时间,备注"/>
                                        <jsp:param name="sortColumnId" value="sort.sortColumn"/>
                                        <jsp:param name="ascendingId" value="sort.ascending"/>
                                        <jsp:param name="sortColumn" value="${r"${page.sortColumn}"}"/>
                                        <jsp:param name="ascending" value="${r"${page.ascending}"}"/>
                                    </jsp:include>
                                    <%--不可见的列添加data-visible="false"--%>
                                    <th data-visible="false">
                                        不可见
                                    </th>
                                    <shiro:hasPermission name="${permissionPrefix}:edit">
                                        <th data-switchable="false">操作</th>
                                    </shiro:hasPermission>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${r"${page.list}"}" var="${className}">
                                    <tr>
                                        <td class="text-center">
                                            <input type="checkbox" name="ids" class="check i-checks" value="<c:out value='${"${"+className+".id}"}'/>"/>
                                        </td>
                                        <td><a href="${r"${ctx}"}/${urlPrefix}/form?id=<c:out value='${"${"+className+".id}"}'/>"
                                               onclick="return top.updateIframe(this.href)"><c:out value='${"${"+className+".name}"}'/></a></td>
                                        <td><fmt:formatDate value="${"${"+className+".createDate}"}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td><c:out value="${"${"+className+".remarks}"}"/></td>
                                        <td><c:out value="${"${"+className+".invisible}"}"/></td>
                                        <shiro:hasPermission name="${permissionPrefix}:edit">
                                            <td>
                                                <a href="${r"${ctx}"}/${urlPrefix}/form?id=<c:out value='${"${"+className+".id}"}'/>"
                                                   title="修改" class="a-normal a-primary"
                                                   onclick="return top.updateIframe(this.href)">修改</a>
                                                <a href="${r"${ctx}"}/${urlPrefix}/delete?id=<c:out value='${"${"+className+".id}"}'/>"
                                                   title="删除" class="a-normal a-warning"
                                                   onclick="return top.sysConfirm('确认要删除该${functionName}吗？', this.href)">删除</a>
                                            </td>
                                        </shiro:hasPermission>
                                    </tr>
                                </c:forEach>
                                <c:forEach begin="${r"${fn:length(page.list)}"}" end="9">
                                    <tr>
                                        <c:forEach begin="1" end="5">
                                            <td>&nbsp;</td>
                                        </c:forEach>
                                        <shiro:hasPermission name="${permissionPrefix}:edit">
                                            <td>&nbsp;</td>
                                        </shiro:hasPermission>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1%">
                        <div class="form-inline">${r"${page}"}</div>
                    </div>
                </div>
            </div>
        </div>

    </form:form>
</div>

<div id="scrollToTop" class="scrollToTop" style="display: none;"><i class="fa fa-chevron-up fa-lg"></i></div>
<script src="${r"${ctxStatic}"}/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="${r"${ctxStatic}"}/js/bootstrap.min.js"></script>
<script src="${r"${ctxStatic}"}/js/plugins/dataTables/jquery.dataTables.min.js"></script>
<script src="${r"${ctxStatic}"}/js/iframe.js"></script>
<script src="${r"${ctxStatic}"}/js/tables.js"></script>
<script src="${r"${ctxStatic}"}/js/breadcrumb.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        <c:if test="${r"${not empty page.fieldBox}"}">
            Tables.show = [${r"${page.fieldBoxs}"}];
        </c:if>
        Tables.init("#contentTable", "#columnSelect");

        var table = $('#contentTable').DataTable({
//        "scrollY": "200px",
            "paging": false,        //分页
            "ordering": false,        //排序
            "info": false,        //控制表信息显示字段
            "searching": false         //搜索
        });

        $('input.toggle-vis').on('click', function (e) {

            // Get the column API object
            var column = table.column($(this).attr('data-column'));

            // Toggle the visibility
            column.visible(!column.visible());
        });

        $(".checkbox-toggle").on('click', function () {
            if (this.checked) {
                $(".check").prop("checked", true);
            } else {
                $(".check").prop('checked', false);
            }
        });

    });

    function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
</script>

</body>
</html>
