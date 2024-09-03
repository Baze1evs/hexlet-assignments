package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class PostsController {

    // BEGIN
    public static void showPost(Context ctx) {
        Post post = PostRepository.find(ctx.pathParamAsClass("id", Long.class).get())
                .orElseThrow(() -> new NotFoundResponse("Page not found"));

        PostPage page = new PostPage(post);

        ctx.render("posts/show.jte", model("page", page));
    }

    public static void showPosts(Context ctx) {
        int pageN = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);

        List<Post> result = PostRepository.findAll(pageN, 5);
        PostsPage page = new PostsPage(result, pageN);

        ctx.render("posts/index.jte", model("page", page));
    }
    // END
}
