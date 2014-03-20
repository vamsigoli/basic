<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<title>Setup and Load Data to jTable using Servlets and JSP</title>
<meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

<c:url value="/updateusers/allusersupdatelist?action=list" var="listurl"/>

<!-- Include one of jTable styles. -->
<link href="<c:url value='/resources/css/metro/crimson/jtable.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/jquery-ui-1.10.4.custom.css'/>" rel="stylesheet" type="text/css" />
<!-- Include jTable script file. -->
<script src="<c:url value='/resources/js/jquery-1.10.2.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/jquery-ui-1.10.4.custom.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/jquery.jtable.js'/>" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
	  var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	  $(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	  });
	});


    $(document).ready(function () {
        //initialize jTable
        $('#PersonTableContainer').jtable({
            title: 'List of Users ',
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)  
            actions: {
                listAction:'${listurl}',
                createAction:'UpdateUserController?action=create',
                updateAction: 'UpdateUserController?action=update',
                deleteAction: 'UpdateUserController?action=delete'
            },
            fields: {
                id: {
                    key: true,
                    list: false
                },
                username : {
                    title: 'User Name',
                    width: '30%'
                },
                email: {
                    title: 'Email',
                    width: '30%'
                },
                lastName: {
                    title: 'Last Name',
                    width: '20%',
                    create: false,
                    edit: false
                },
                firstName: {
                    title: 'First Name',
                    width: '20%',
                    
                }
                
            }
        });        
       $('#PersonTableContainer').jtable('load');
    });
</script>
</head>
<body>
<div style="width:60%;margin-right:20%;margin-left:20%;text-align:center;">
<h1>Update Users in Grid</h1>
<h4>Users Details </h4>
<div id="PersonTableContainer"></div>
</div>
</body>
</html>
