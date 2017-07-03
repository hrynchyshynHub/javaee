<%--
  Created by IntelliJ IDEA.
  User: ivan.hrynchyshyn
  Date: 03.07.2017
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://bootstrapjsp.org/" prefix="b" %>
<b:kickstart>
    <b:panel>
        <form action="login" method="post">
            Username<input type="text"  name = "username"><br>
            Password<input type="password"  name = "password"><br>
            <input type="submit" value="log In">
        </form>
    </b:panel>
</b:kickstart>