@import exercise.dto.users.UserPage

@param UserPage page

@template.layout.page(
    title = @`${page.getUser().getFirstName()} ${page.getUser().getLastName()}`,
    content = @`
        <table>
            <tr><td>ID</td><td>${page.getUser().getId()}</td></tr>
            <tr><td>First Name</td><td>${page.getUser().getFirstName()}</td></tr>
            <tr><td>Last Name</td><td>${page.getUser().getLastName()}</td></tr>
            <tr><td>E-mail</td><td>${page.getUser().getEmail()}</td></tr>
        </table>
    `
)
