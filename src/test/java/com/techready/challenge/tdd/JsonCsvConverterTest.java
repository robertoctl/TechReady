//package com.techready.challenge.tdd;
//
//import org.junit.Test;
//import com.challenge.tdd.JsonCsvConverter;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//
//public class JsonCsvConverterTest {
//
//    @Test
//    public void shouldParseJsonIntoCsvRow() {
//        String json = "{\"name\":\"Alice\",\"age\":30}";
//        String expected = "Alice,30";
//        assertEquals(expected, JsonCsvConverter.convert(json));
//    }
//
//    @Test
//    public void whenJsonIsEmpty_shouldThrowException() {
//        String json = "";
//        assertThrows(IllegalArgumentException.class, () -> JsonCsvConverter.convert(json));
//    }
//
//    @Test
//    public void whenJsonHasMultipleObjects_shouldParseJsonIntoCsvRows() {
//        String json = "[{\"name\":\"Alice\",\"age\":30},{\"name\":\"Bert\",\"age\":35}]";
//        String expected = "Alice,30\nBert,35\n";
//        assertEquals(expected, JsonCsvConverter.convert(json));
//    }
//}
