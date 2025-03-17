package uvg.edu.gt;

import uvg.edu.gt.model.Pokemon;
import uvg.edu.gt.utils.PokemonCSVReader;

import java.util.*;

public class Main {
    private static Map<String, Pokemon> pokemonMap = new HashMap<>();
    private static List<Pokemon> userCollection = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cargar datos de Pokémon desde el archivo CSV en la carpeta resources
        String filePath = "src/main/resources/pokemon.csv";
        PokemonCSVReader.loadPokemonData(filePath, pokemonMap);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar Pokémon a la colección del usuario");
            System.out.println("2. Mostrar datos de un Pokémon");
            System.out.println("3. Mostrar colección del usuario ordenada por tipo");
            System.out.println("4. Mostrar todos los Pokémon ordenados por tipo");
            System.out.println("5. Mostrar Pokémon por habilidad");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String name = scanner.nextLine();
                    System.out.println(addPokemonToUserCollection(name));
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    name = scanner.nextLine();
                    Pokemon pokemon = getPokemonDetails(name);
                    if (pokemon != null) {
                        System.out.println(pokemon);
                    } else {
                        System.out.println("Pokémon no encontrado.");
                    }
                    break;
                case 3:
                    List<Pokemon> sortedUserCollection = getUserCollectionSortedByType();
                    for (Pokemon p : sortedUserCollection) {
                        System.out.println(p);
                    }
                    break;
                case 4:
                    List<Pokemon> sortedAllPokemon = getAllPokemonSortedByType();
                    for (Pokemon p : sortedAllPokemon) {
                        System.out.println(p);
                    }
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad: ");
                    String ability = scanner.nextLine();
                    List<Pokemon> pokemonByAbility = getPokemonByAbility(ability);
                    for (Pokemon p : pokemonByAbility) {
                        System.out.println(p);
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }

    public static String addPokemonToUserCollection(String name) {
        if (!pokemonMap.containsKey(name)) {
            return "Error: Pokemon not found in the database.";
        }
        Pokemon pokemon = pokemonMap.get(name);
        if (userCollection.contains(pokemon)) {
            return "Error: Pokemon already in the user's collection.";
        }
        userCollection.add(pokemon);
        return "Pokemon added to the user's collection.";
    }

    public static Pokemon getPokemonDetails(String name) {
        return pokemonMap.get(name);
    }

    public static List<Pokemon> getUserCollectionSortedByType() {
        List<Pokemon> sortedList = new ArrayList<>(userCollection);
        sortedList.sort(Comparator.comparing(Pokemon::getType1));
        return sortedList;
    }

    public static List<Pokemon> getAllPokemonSortedByType() {
        List<Pokemon> sortedList = new ArrayList<>(pokemonMap.values());
        sortedList.sort(Comparator.comparing(Pokemon::getType1));
        return sortedList;
    }

    public static List<Pokemon> getPokemonByAbility(String ability) {
        List<Pokemon> result = new ArrayList<>();
        for (Pokemon pokemon : pokemonMap.values()) {
            if (pokemon.getAbilities().contains(ability)) {
                result.add(pokemon);
            }
        }
        return result;
    }

    // Método para agregar Pokémon a la base de datos (debería llamarse al leer los datos del archivo CSV)
    public static void addPokemonToDatabase(Pokemon pokemon) {
        pokemonMap.put(pokemon.getName(), pokemon);
    }
}