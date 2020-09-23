<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<% if (session.getAttribute("userSession") == null) {
    response.sendRedirect("Welcome.jsp");
}
%>
<html lang="en" >
<head>
    <title>Title</title>
    <jsp:include page="nav.jsp"></jsp:include>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>

<div class="container">

    <h1>Edit Item</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <div class="col-md-3">
            <div class="text-center">
                <img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
                <h6>Upload a different photo...</h6>

                <input type="file" class="form-control">
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">
            <div class="alert alert-info alert-dismissable">
                <a class="panel-close close" data-dismiss="alert">×</a>
                <i class="fa fa-coffee"></i>
                This is an <strong>.alert</strong>. Use this to show important messages to the user.
            </div>

            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-lg-3 control-label">Name:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" value="Jane">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-lg-3 control-label"> Choose category :</label>
                    <div class="col-lg-8">
                        <div class="ui-select">
                            <select id="category" class="form-control">
                                <option value="sport">Sport</option>
                                <option value="technology">Technology</option>
                                <option value="lazer">Lazer</option>
                                <option value="health"> Health</option>
                                <option value="clothes">Colthes</option>

                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Origin Country:</label>
                    <div class="col-lg-8">
                        <div class="ui-select">
                            <select id="user_time_zone" class="form-control">
                                <option value="portugal">Portugal</option>
                                <option value="espanha">Espanha</option>
                                <option value="itália">Itália</option>
                                <option value="frança"> França</option>
                                <option value="alemanha">Aleamanha</option>

                            </select>
                        </div>
                    </div>
                 </div>
                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <input type="button" class="btn btn-primary" value="Edit">
                        <span></span>
                        <input type="reset" class="btn btn-default" value="Cancel">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<hr>
</body>
</html>
