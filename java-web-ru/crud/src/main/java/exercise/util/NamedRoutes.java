package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return rootPath() + "posts";
    }

    public static String abstractPostPath() {
        return postsPath() + "/{id}";
    }

    public static String concretePostPath(Long id) {
        return postsPath() + '/' + id.toString();
    }
    // END
}
