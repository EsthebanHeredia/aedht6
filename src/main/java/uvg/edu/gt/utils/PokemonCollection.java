package uvg.edu.gt.utils;

import uvg.edu.gt.model.Pokemon;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PokemonCollection {
    private Set<String> collection = new HashSet<>();
    private final String filePath = "src/main/resources/user_collection.txt";

    public PokemonCollection() {
        loadCollection();
    }

    public void addPokemon(String name, Map<String, Pokemon> pokemonMap) {
        if (!pokemonMap.containsKey(name)) {
            System.out.println("Error: Pokémon no encontrado.");
            return;
        }
        if (collection.contains(name)) {
            System.out.println("Ya tienes a " + name + " en tu colección.");
        } else {
            collection.add(name);
            saveCollection();
            System.out.println("Pokémon " + name + " agregado a la colección.");
        }
    }

    public void showPokemon(String name, Map<String, Pokemon> pokemonMap) {
        if (pokemonMap.containsKey(name)) {
            System.out.println(pokemonMap.get(name));
        } else {
            System.out.println("Pokémon no encontrado.");
        }
    }

    public void showUserCollection(Map<String, Pokemon> pokemonMap) {
        collection.stream()
                .map(pokemonMap::get)
                .sorted((p1, p2) -> p1.getType1().compareToIgnoreCase(p2.getType1()))
                .forEach(System.out::println);
    }

    public static void showAllPokemon(Map<String, Pokemon> pokemonMap) {
        pokemonMap.values().stream()
                .sorted((p1, p2) -> p1.getType1().compareToIgnoreCase(p2.getType1()))
                .forEach(System.out::println);
    }

    public static void findPokemonByAbility(String ability, Map<String, Pokemon> pokemonMap) {
        pokemonMap.values().stream()
                .filter(p -> p.getAbilities().contains(ability))
                .forEach(System.out::println);
    }

    private void saveCollection() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String name : collection) {
                writer.write(name);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar la colección: " + e.getMessage());
        }
    }

    private void loadCollection() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                collection.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la colección: " + e.getMessage());
        }
    }
}