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
            String[] nextLine;
            reader.readNext(); // Omitir encabezados

            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0]; // Name
                String type1 = nextLine[2]; // Type1
                String type2 = nextLine[3]; // Type2
                String abilities = nextLine[7]; // Abilities

                Pokemon pokemon = new Pokemon(name, type1, type2, abilities);
                pokemonMap.put(name, pokemon);
            }

            System.out.println("Datos de Pokémon cargados correctamente.");

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
}