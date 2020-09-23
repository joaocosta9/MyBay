<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="data.Item" %>
<!DOCTYPE html>
<% if ((session.getAttribute("userSession") == null) || (session.getAttribute("item") == null)) {
    response.sendRedirect("Welcome.jsp");
}
%>
<html>
<head>
    <title>Title</title>
    <jsp:include page="nav.jsp"></jsp:include>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>

<div class="container">

    <%
        Item item = (Item) session.getAttribute("item");
    %>

    <h1>View Item</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <div class="col-md-3">
            <div class="text-center">
                <img src="http://127.0.0.1:8080/projeto2-web-0.0.1-SNAPSHOT/app/GetItemImage" width="100" height="100" class="avatar img-circle" alt="avatar">
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">

            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-lg-3 control-label">Name:</label>
                    <div class="col-lg-8">
                        <label><%= item.getName()%></label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-lg-3 control-label"> Category :</label>
                    <div class="col-lg-8">
                        <div class="ui-select">
                            <label><%= item.getCategory()%></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Origin Country:</label>
                    <div class="col-lg-8">
                        <div class="ui-select">
                            <label><%= item.getOriginCoiuntry()%></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Date Created:</label>
                    <div class="col-lg-8">
                        <div class="ui-select">
                            <label><%= item.getDate()%></label>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<hr>
</body>
</html>
