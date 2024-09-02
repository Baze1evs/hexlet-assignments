@import exercise.dto.users.UserPage
@param UserPage userPage

<html>
    <head>
        <title>User ${userPage.user().getFirstName()} ${userPage.user().getLastName()}</title>
    </head>
    <body>
        <table>
            <tr><td>ID</td><td>${userPage.user().getId()}</td></tr>
            <tr><td>First Name</td><td>${userPage.user().getFirstName()}</td></tr>
            <tr><td>Last Name</td><td>${userPage.user().getLastName()}</td></tr>
            <tr><td>E-mail</td><td>${userPage.user().getEmail()}</td></tr>
        </table>
    </body>
</html>