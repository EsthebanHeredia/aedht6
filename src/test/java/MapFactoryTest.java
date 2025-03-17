package uvg.edu.gt.factory;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class MapFactoryTest {

    @Test
    void testGetHashMap() {
        Map<String, String> map = MapFactory.getMap("hashmap");
        assertTrue(map instanceof HashMap);
    }

    @Test
    void testGetTreeMap() {
        Map<String, String> map = MapFactory.getMap("treemap");
        assertTrue(map instanceof TreeMap);
    }

    @Test
    void testGetLinkedHashMap() {
        Map<String, String> map = MapFactory.getMap("linkedhashmap");
        assertTrue(map instanceof LinkedHashMap);
    }

    @Test
    void testGetInvalidMap() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MapFactory.getMap("invalidmap");
        });
        assertEquals("Tipo de Map no válido: invalidmap", exception.getMessage());
    }
}