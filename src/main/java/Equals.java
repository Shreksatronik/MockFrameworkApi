public class Equals extends MyMock {
    private final Object wanted;

    public Equals(Object wanted) {
        this.wanted = wanted;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Equals)) {
            return false;
        }
        Equals other = (Equals) o;
        return (this.wanted == null && other.wanted == null)
                || this.wanted != null && this.wanted.equals(other.wanted);
    }
}
