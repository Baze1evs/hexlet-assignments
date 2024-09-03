package exercise;

import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.get("articles/build", ctx -> {
            ctx.render("articles/build.jte",
                    model("page", new BuildArticlePage("", "", null)));
        });

        app.post("/articles", ctx -> {
            String title = ctx.formParam("title");
            String content = ctx.formParam("content");

            try {
                String validTitle = ctx.formParamAsClass("title", String.class)
                        .check(str -> str.length() >= 2, "Название не должно быть короче двух символов")
                        .check(str -> !ArticleRepository.existsByTitle(str),
                                "Статья с таким названием уже существует")
                        .get();
                String validContent = ctx.formParamAsClass("content", String.class)
                        .check(str -> str.length() >= 10, "Статья должна быть не короче 10 символов")
                        .get();

                Article article = new Article(validTitle, validContent);
                ArticleRepository.save(article);

                ctx.redirect("/articles");
            } catch (ValidationException e) {
                var page = new BuildArticlePage(title, content, e.getErrors());
                ctx.status(422);
                ctx.render("articles/build.jte", model("page", page));
            }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
