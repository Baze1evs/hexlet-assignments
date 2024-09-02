@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${page.term()}" />
        <input type="submit" value="Search" />
    </form>
    @for(var user : page.users())
        <div>
            <h2><a href="/users/${user.getId()}">${user.toString()}</a></h2>
        </div>
    @endfor
`
)