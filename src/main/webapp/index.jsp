<%--
  Created by IntelliJ IDEA.
  User: sadnan
  Date: 6/11/22
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String hostName = request.getServerName();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id='outer-frame'>
    <div id='inner-frame'>
        <div id='header'>
            <h1>SADI services at <%=hostName%></h1>
        </div>
        <div id='nav'>
            <ul>
                <li>Services</li>
            </ul>
        </div>
        <div id='content'>
            <h2>SADI Services</h2>
            <ul>   
                <li><a href="./getGreeting">getGreeting</a></li>
                <li><a href="./getAccommodation">getAccommodation</a></li>                
                <li><a href="./getAge">getAge</a></li>                
                <li><a href="./getConvertedCurrency">getConvertedCurrency</a></li>
                <li><a href="./getGreetingFail">getGreetingFail</a></li>
                <li><a href="./computeBMIFail">computeBMIFail</a></li>
                <li><a href="./computeBMISuccess">computeBMISuccess</a></li>
                <li><a href="./getFullNameFail">getFullNameFail</a></li>
                <li><a href="./getGenerationName">getGenerationName</a></li>
            </Ul>
        </div> <!-- content -->
        <div id='footer'>
        </div> <!-- footer -->
    </div> <!-- inner-frame -->
</div> <!-- outer-frame -->
</body>
</html>
