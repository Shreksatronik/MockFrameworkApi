public class Cat {
    public enum Trait {
        kind, indifferent, evil
    }

    public enum Box {
        big, medium, small
    }

    static public String GiveBox (Box box) {
        return switch (box) {
            case big -> "sleep";
            case medium -> "lie down";
            case small -> "sit";
        };
    }

    Trait trait;
    public Cat () {
        this.trait = Trait.kind;
    }
    public Cat (Trait trait) {
        this.trait = trait;
    }

    public String PetTheCat () {
        return switch (this.trait) {
            case kind -> "purr";
            case indifferent -> "nothing";
            case evil -> "bite";
        };
    }
}
