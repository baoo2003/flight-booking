<%@ page import="ejb.testinterface, javax.naming.*"%>
<%@ page import="java.math.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.ejb.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.rmi.RemoteException" %>
<%!
    private testinterface converter = null;

    public void jspInit() {
        try {
            InitialContext ic = new InitialContext();
            converter = (testinterface) ic.lookup(testinterface.class.getName());
        } catch (Exception ex) {
            System.out.println("Couldn't create converter bean." + ex.getMessage());
        }
    }

    public void jspDestroy() {
        converter = null;
    }
%>
<html>
    <head>
        <title>Temperature Converter</title>
    </head>
    <body bgcolor="white">
        <h1><b><center> Temperature Converter </center></b></h1>
        <hr>
        <p>Enter a degree to convert:</p>
        <form method="get">
            <input type="text" name="degree" size="25">
            <br>
            <p>
                <input type="submit" name="fToC"
                       value="Fahrenheit to Centigrade">
                <input type="submit" name="cToF"
                       value="Centigrade to Fahrenheit">
        </form>
        <%
            DecimalFormat twoDigits = new DecimalFormat("0.00");
            String degree = request.getParameter("degree");
            if (degree != null && degree.length() > 0) {
                double d = Double.parseDouble(degree);
        %>
        <%
            if (request.getParameter("fToC") != null) {
        %>
        <p>
            <%= degree%> fahrenheit degrees are <%= twoDigits.format(converter.fToC(d))%> centigrade degrees.
            <% }
            %>
            <% if (request.getParameter("cToF") != null) {
            %>
        <p>
            <%= degree%> centigrade degrees are
            <%= twoDigits.format(converter.cToF(d))%> fahrenheit degrees .
            <% }
            %>
            <% }
            %>
    </body>
</html>