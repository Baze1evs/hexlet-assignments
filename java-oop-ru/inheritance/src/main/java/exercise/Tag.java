package exercise;

import java.util.Map;

// BEGIN
abstract class Tag {
    protected final String tag;
    protected final Map<String, String> attributes;

    protected Tag(String tag, Map<String, String> attributes) {
        this.tag = tag;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("<");

        stringBuilder.append(tag);
        stringBuilder.append(' ');
        attributes.forEach((key, value) -> {
            stringBuilder.append(key);
            stringBuilder.append("=\"");
            stringBuilder.append(value);
            stringBuilder.append("\" ");
        });
        stringBuilder.setCharAt(stringBuilder.length() - 1, '>');

        return stringBuilder.toString();
    }
}
// END
