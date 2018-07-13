<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>

<div class='header_right links_content'>
    <a class="exit" href="/logout">退出</a>
    <div class="client">
        您好 <shiro:principal property="name"/>
    </div>
</div>
	




