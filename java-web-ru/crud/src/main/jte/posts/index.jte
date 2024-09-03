@import exercise.dto.posts.PostsPage
@import exercise.repository.PostRepository
@import exercise.util.NamedRoutes

@param PostsPage page

@template.layout.page(content = @`
    @if(page.getPageN() > 1)
        <a href="${NamedRoutes.postsPath()}?page=${page.getPageN() - 1}">Назад</a>
    @endif
    @if(page.getPageN() <= PostRepository.getEntities().size() / 5)
        <a href="${NamedRoutes.postsPath()}?page=${page.getPageN() + 1}">Далее</a>
    @endif
    <div class="mx-auto p-4 py-md-5">
        <main>
            @for(var post : page.getPosts())
                <p><a href="${NamedRoutes.concretePostPath(post.getId())}">${post.getName()}</a></p>
            @endfor
        </main>
    </div>
`)
