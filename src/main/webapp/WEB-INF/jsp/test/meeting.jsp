<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring 3 MVC Series - Contact Manager | viralpatel.net</title>
</head>
<body>
 
<h2>JSON Save</h2>
 
<form:form method="post"  action="../user/save" >
    <table>
    <tr>
        <td>JSON</td>
        <td>
        	<textarea id="json" id="json" name="json" rows="10" cols="100">{'id':4,'firstname':'My Name','lastName':'Last Name','email':'sthalluri@yhoo.com','phone':'07345345435'}</textarea>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="save"/>
        </td>
    </tr>
</table>
</form:form>


<form:form method="post"  action="../user/checkLogin" >
    <table>
    <tr>
        <td>JSON</td>
        <td>
        	<textarea id="json" id="json" name="json" rows="10" cols="100">{'userId':'sthalluri@yahoo.com','password':'123'}</textarea>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="save"/>
        </td>
    </tr>
</table>
</form:form>


