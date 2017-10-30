<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sait" uri="/WEB-INF/tlds/sait.tld" %>  
<c:import url="header.html"/>
<sait:DebugTag>
    Remote Host: ${pageContext.request.remoteHost}</br>
    Session ID: ${pageContext.session.id}</br>
</sait:DebugTag> 
<tags:login/>
<c:import url="footer.html"/>
