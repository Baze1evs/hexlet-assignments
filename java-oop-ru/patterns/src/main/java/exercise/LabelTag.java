package exercise;

// BEGIN
class LabelTag implements TagInterface {
    private final String text;
    private final TagInterface wrappee;

    public LabelTag(String text, TagInterface wrappee) {
        this.text = text;
        this.wrappee = wrappee;
    }

    @Override
    public String render() {
        return "<label>" + text + wrappee.render() + "</label>";
    }
}
// END
