<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>List Todos Page</title>
     </head>
     <body>
        <h1>Welcome, ${name}</h1>
        <p>Add new TODO</p>

        <form:form method="post" modelAttribute="todo">
            <form:input type="text" name="description" required="required"
                            path="description"/>
            <form:errors path="description" />
            <form:input type="hidden" name="id" path="id"/>
            <form:input type="hidden" name="done" path="done"/>
            <br/>
            <input type="submit"/>
        </form:form>
     </body>
</html>