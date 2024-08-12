<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>List Todos Page</title>
     </head>
     <body>
        <h1>Welcome, ${name}</h1>
        <p>Your todos</p>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
     <a href="add-todo">Add TODO</a>
     </body>
</html>