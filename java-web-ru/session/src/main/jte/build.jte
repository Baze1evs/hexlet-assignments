@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes

@param LoginPage page

@template.layout.page(
    content = @`
    <main>
        @if(page != null)
            @if(page.getError() != null)
                ${page.getError()}
            @endif
        @endif
        <form action="${NamedRoutes.loginPath()}" method="post">
            <input required type="text" placeholder="Name" name="name" value="${page != null ? page.getName() : ""}"/>
            <input required type="password" placeholder="Password" name="password" />
            <input type="submit" />
        </form>
    </main>
    `
)