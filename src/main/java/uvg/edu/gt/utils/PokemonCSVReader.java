package uvg.edu.gt.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import uvg.edu.gt.model.Pokemon;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class PokemonCSVReader {
    public static void loadPokemonData(String filePath, Map<String, Pokemon> pokemonMap) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Saltamos la primera línea (encabezados)
            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    String name = nextLine[0];
                    int pokedexNumber = Integer.parseInt(nextLine[1]);
                    String type1 = nextLine[2];
                    String type2 = nextLine[3].isEmpty() ? null : nextLine[3];
                    String classification = nextLine[4];
                    double height = Double.parseDouble(nextLine[5]);
                    double weight = Double.parseDouble(nextLine[6]);
                    String abilities = nextLine[7];
                    int generation = Integer.parseInt(nextLine[8]);
                    boolean legendary = nextLine[9].equalsIgnoreCase("Yes");

                    Pokemon pokemon = new Pokemon(pokedexNumber, name, type1, type2,
                            classification, height, weight, abilities, generation, legendary);
                    pokemonMap.put(name, pokemon);
                } catch (NumberFormatException e) {
                    System.err.println("Error al procesar línea: " + String.join(",", nextLine));
                }
            }
            System.out.println("Datos de Pokémon cargados correctamente.");

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
}