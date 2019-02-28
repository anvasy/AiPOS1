<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.02.2019
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <p>
    <%=session.getAttribute("q")%>
  </p>
  <p>
    <%=session.getAttribute("w")%>
  </p>
  <form action="/" method="POST">
    <button name="q" value="q">Ok</button>
  </form>
  </body>
</html>
