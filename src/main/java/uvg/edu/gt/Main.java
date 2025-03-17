package uvg.edu.gt;

import uvg.edu.gt.factory.MapFactory;
import uvg.edu.gt.model.Pokemon;
import uvg.edu.gt.utils.PokemonCSVReader;
import uvg.edu.gt.utils.PokemonCollection;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PokemonCollection userCollection = new PokemonCollection();

        System.out.println("Seleccione la implementación de Map (hashmap, treemap, linkedhashmap):");
        String tipoMapa = scanner.nextLine();
        Map<String, Pokemon> pokemonMap = MapFactory.getMap(tipoMapa);

        String filePath = "src/main/resources/pokemon.csv";
        PokemonCSVReader.loadPokemonData(filePath, pokemonMap);

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar Pokémon a la colección");
            System.out.println("2. Mostrar datos de un Pokémon");
            System.out.println("3. Ver colección de usuario ordenada por Type1");
            System.out.println("4. Ver todos los Pokémon ordenados por Type1");
            System.out.println("5. Buscar Pokémon por habilidad");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1 -> {
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String name = scanner.nextLine();
                    userCollection.addPokemon(name, pokemonMap);
                }
                case 2 -> {
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String name = scanner.nextLine();
                    userCollection.showPokemon(name, pokemonMap);
                }
                case 3 -> userCollection.showUserCollection(pokemonMap);
                case 4 -> PokemonCollection.showAllPokemon(pokemonMap);
                case 5 -> {
                    System.out.print("Ingrese la habilidad: ");
                    String ability = scanner.nextLine();
                    PokemonCollection.findPokemonByAbility(ability, pokemonMap);
                }
                case 6 -> System.exit(0);
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}