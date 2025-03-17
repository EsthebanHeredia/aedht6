package uvg.edu.gt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uvg.edu.gt.model.Pokemon;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp() {
        Main.pokemonMap = MapFactory.getMap("hashmap");
        Main.userCollection.clear();
        Main.addPokemonToDatabase(new Pokemon(1, "Bulbasaur", "Grass", "Poison", "Seed Pokémon", 0.7, 6.9, "Overgrow", 1, false));
        Main.addPokemonToDatabase(new Pokemon(4, "Charmander", "Fire", null, "Lizard Pokémon", 0.6, 8.5, "Blaze", 1, false));
    }

    @Test
    void testAddPokemonToUserCollection() {
        String result = Main.addPokemonToUserCollection("Bulbasaur");
        assertEquals("Pokemon added to the user's collection.", result);

        result = Main.addPokemonToUserCollection("Bulbasaur");
        assertEquals("Error: Pokemon already in the user's collection.", result);
    }

    @Test
    void testGetPokemonDetails() {
        Pokemon pokemon = Main.getPokemonDetails("Bulbasaur");
        assertNotNull(pokemon);
        assertEquals("Bulbasaur", pokemon.getName());

        pokemon = Main.getPokemonDetails("NonExistentPokemon");
        assertNull(pokemon);
    }

    @Test
    void testGetUserCollectionSortedByType() {
        Main.addPokemonToUserCollection("Bulbasaur");
        Main.addPokemonToUserCollection("Charmander");

        List<Pokemon> sortedList = Main.getUserCollectionSortedByType();
        assertEquals(2, sortedList.size());
        assertEquals("Charmander", sortedList.get(0).getName());
        assertEquals("Bulbasaur", sortedList.get(1).getName());
    }

    @Test
    void testGetAllPokemonSortedByType() {
        List<Pokemon> sortedList = Main.getAllPokemonSortedByType();
        assertEquals(2, sortedList.size());
        assertEquals("Charmander", sortedList.get(0).getName());
        assertEquals("Bulbasaur", sortedList.get(1).getName());
    }

    @Test
    void testGetPokemonByAbility() {
        List<Pokemon> result = Main.getPokemonByAbility("Overgrow");
        assertEquals(1, result.size());
        assertEquals("Bulbasaur", result.get(0).getName());

        result = Main.getPokemonByAbility("NonExistentAbility");
        assertTrue(result.isEmpty());
    }
}