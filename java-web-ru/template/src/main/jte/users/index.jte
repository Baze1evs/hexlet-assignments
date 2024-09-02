@import exercise.dto.users.UsersPage
@param UsersPage usersPage

<html>
    <head>
        <title>Пользователи</title>
    </head>
    <body>
    @if(usersPage.users().isEmpty())
        <p>Пользователей нет</p>
    @else
        @for(var user : usersPage.users())
            <div>
                <h2><a href="/users/${user.getId()}">${user.toString()}</a></h2>
            </div>
        @endfor
    @endif
    </body>
</html>