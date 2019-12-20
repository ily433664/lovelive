<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>${functionName}管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link href="${r"${ctxStatic}"}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${r"${ctxStatic}"}/css/font-awesome.min.css" rel="stylesheet">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${r"${ctxStatic}"}/css/plugins/Ionicons/css/ionicons.css">
    <!-- Theme style -->
    <link href="${r"${ctxStatic}"}/css/AdminLTE.min.css" rel="stylesheet">
    <link href="${r"${ctxStatic}"}/css/skins/_all-skins.min.css" rel="stylesheet">

    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${r"${ctxStatic}"}/css/plugins/iCheck/flat/blue.css">
    <link href="${r"${ctxStatic}"}/css/common.css" rel="stylesheet">
    <!-- jQuery 3 -->
    <script type="text/javascript" src="${r"${ctxStatic}"}/js/jquery-2.2.3.min.js"></script>
    <link href="${r"${ctxStatic}"}/jquery-validation/1.11.1/jquery.validate.min.css" type="text/css" rel="stylesheet" />
</head>
<body class="inner inner2 card">
<div class="content-header">
    <h1>
        <i class="fa  fa-address-card sign"></i>
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="javascript:void(0);" onclick="return top.updateIframe('${r"${ctx}"}/index')">首页</a></li>
    </ol>
</div>
<div class="row" style="padding: 10px">
    <div class="block">
        <div class="blockCenter">
            <form:form id="inputForm" modelAttribute="${className}" action="${r"${ctx}"}/${urlPrefix}/save" method="post"
                       class="form-horizontal">
                <form:hidden path="id"/>
                <tags:message content="${r"${message}"}"/>
                <div class="normalForm">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="name"><span class="required">*</span>名称:</label>
                        <div class="col-sm-6">
                            <form:input path="name" htmlEscape="true" maxlength="50" class="required form-control"/>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="remarks">备注:</label>
                        <div class="col-sm-6">
                            <form:input path="remarks" htmlEscape="true" maxlength="200" class="form-control"/>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="remarks">不可见:</label>
                        <div class="col-sm-6">
                            <form:input path="invisible" htmlEscape="true" maxlength="50" class="form-control digits"/>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>

                    <div class="btnGroup2">
                        <shiro:hasPermission name="${permissionPrefix}:edit">
                            <button class="btn btn-primary" type="submit">保 存</button>
                        </shiro:hasPermission>
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>


<!-- Bootstrap 3.3.6 -->
<script type="text/javascript" src="${r"${ctxStatic}"}/js/bootstrap.min.js"></script>
<%--jquery validation--%>
<script src="${r"${ctxStatic}"}/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${r"${ctxStatic}"}/jquery-validation/1.11.1/jquery.validate.method.min.js" type="text/javascript"></script>
<script src="${r"${ctxStatic}"}/js/validationMethods.js"></script>
<script src="${r"${ctxStatic}"}/js/crypto-js.js"></script>
<script src="${r"${ctxStatic}"}/js/breadcrumb.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#name").focus();
        $("#inputForm").validate({
            errorPlacement: function (error, element) {
                error.appendTo(element.closest('div').next());
                element.closest('.form-group').addClass('has-error');

            },
            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            }

        });
    });
</script>
</body>
</html>
