<%--
  Created by IntelliJ IDEA.
  User: Double
  Date: 2020/7/22
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Spring Scope</h2>
<h4>\${user.name} : ${user.name}</h4>
<h4>\${userObject.name} : ${userObject.name}</h4>
<h4>\${applicationScope['scopedTarget.user'].name} : ${applicationScope['scopedTarget.user'].name}</h4>
</body>
</html>
