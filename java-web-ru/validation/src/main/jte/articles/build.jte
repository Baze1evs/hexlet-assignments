@import exercise.dto.articles.BuildArticlePage

@param BuildArticlePage page

@template.layout.page(content = @`
    @if(page.errors() != null)
        <ul>
            @for(var validator : page.errors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <form action="/articles" method="post">
        <div>
            <label>
                <input required type="text" name="title" minlength="2" value="${page.title()}" />
            </label>
        </div>
        <div>
            <label>
                <textarea required name="content" minlength="10">${page.content()}</textarea>
            </label>
        </div>
        <input type="submit" value="Опубликовать" />
    </form>
    `
)