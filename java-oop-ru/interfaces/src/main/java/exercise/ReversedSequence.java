package exercise;

// BEGIN
class ReversedSequence implements CharSequence {
    private final String seq;

    public ReversedSequence(String seq) {
        this.seq = new StringBuilder(seq).reverse().toString();
    }

    @Override
    public int length() {
        return seq.length();
    }

    @Override
    public char charAt(int index) {
        return seq.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return seq.subSequence(start, end);
    }

    @Override
    public String toString() {
        return seq;
    }
}
// END
