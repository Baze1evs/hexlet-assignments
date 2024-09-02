@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
    title = @`Users`,
    content = @`
        @if(page.getUsers().isEmpty())
            <p>Пользователей нет</p>
        @else
            @for(var user : page.getUsers())
                <div>
                    <h2><a href="/users/${user.getId()}">${user.toString()}</a></h2>
                </div>
            @endfor
        @endif
    `
)
