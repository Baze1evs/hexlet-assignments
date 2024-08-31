package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
class PairedTag extends Tag {
    private final String text;
    private final List<Tag> wrappees;

    public PairedTag(String tag, Map<String, String> attributes, String text, List<Tag> wrappees) {
        super(tag, attributes);
        this.text = text;
        this.wrappees = wrappees;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        wrappees.forEach(stringBuilder::append);
        stringBuilder.append(text);
        stringBuilder.append("</");
        stringBuilder.append(tag);
        stringBuilder.append('>');

        return stringBuilder.toString();
    }
}
// END
