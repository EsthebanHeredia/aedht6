package uvg.edu.gt.model;

public class Pokemon {
    private int pokedexNumber;
    private String name;
    private String type1;
    private String type2;
    private String classification;
    private double height;
    private double weight;
    private String abilities;
    private int generation;
    private boolean legendary;

    public Pokemon(int pokedexNumber, String name, String type1, String type2, String classification, double height, double weight, String abilities, int generation, boolean legendary) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.classification = classification;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.generation = generation;
        this.legendary = legendary;
    }

    // Getters and toString() method
    public int getPokedexNumber() { return pokedexNumber; }
    public String getName() { return name; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public String getClassification() { return classification; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public String getAbilities() { return abilities; }
    public int getGeneration() { return generation; }
    public boolean isLegendary() { return legendary; }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokedexNumber=" + pokedexNumber +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", classification='" + classification + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", abilities='" + abilities + '\'' +
                ", generation=" + generation +
                ", legendary=" + legendary +
                '}';
    }
}