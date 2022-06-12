package Part3;

public class Mark {
    private final int value;

    public Mark(int value) {
        if (value < 0 || value > 100)
            throw new IllegalArgumentException("Mark should be from 0 to 100");
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
