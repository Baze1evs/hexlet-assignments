package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import io.javalin.http.Context;
import org.checkerframework.checker.units.qual.C;

public class SessionsController {

    // BEGIN
    public static void show(Context ctx) {
        var name = ctx.sessionAttribute("currentUser");
        var page = new MainPage(name);
        ctx.render("index.jte", model("page", page));
    }

    public static void build(Context ctx) {
        ctx.render("build.jte");
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");

        var userOpt = UsersRepository.findByName(name);

        if (userOpt.isPresent()) {
            var user = userOpt.get();

            if (user.getPassword().equals(encrypt(password))) {
                ctx.sessionAttribute("currentUser", name);
                ctx.redirect("/");
                return;
            }
        }

        var page = new LoginPage(name, "Wrong username or password");
        ctx.render("build.jte", model("page", page));
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
    // END
}
