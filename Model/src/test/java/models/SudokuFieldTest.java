package models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import sudoku.model.exceptions.SudokuFieldComparisonException;
import sudoku.model.models.SudokuField;

public class SudokuFieldTest {
    //Test to see whether the constructor sets the value correctly
    @Test
    void testConstructorWithValue() {
        SudokuField field = new SudokuField(5);
        assertEquals(5, field.getValue());
    }

    //test to see if nothing is passed in the constructor then the field value should be 0
    @Test
    void testConstructorWithoutValue() {
        SudokuField field = new SudokuField();
        assertEquals(0, field.getValue());
    }

    //test to see if the value of field that is set returns appropriately when getValue is called
    @Test
    void testGetValue() {
        SudokuField field = new SudokuField(7);
        assertEquals(7, field.getValue());
    }

    //test to see if the value of field is returned correclty after the field value changes after initialization
    @Test
    void testSetValue() {
        SudokuField field = new SudokuField(3);
        field.setValue(8);
        assertEquals(8, field.getValue());
    }

    @Test
    void testEquals_SameObject() {
        SudokuField field = new SudokuField(5);
        assertTrue(field.equals(field));
    }

    //test to see if two sudoku fields with the same value are considered equal
    @Test
    void testEquals_SameValue() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        assertTrue(field1.equals(field2));
    }

    //test to see if two sudoku fields with different values are considered not equal
    @Test
    void testEquals_DifferentValue() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(6);
        assertFalse(field1.equals(field2));
    }

    //test to confirm that the a field is not null after initialization and that the equals method returns false when comparing to null
    @Test
    void testEquals_Null() {
        SudokuField field = new SudokuField(5);
        assertFalse(field.equals(null));
    }

    //test to confirm that the equals method returns false when comparing to an object of a different class
    @Test
    void testEquals_DifferentClass() {
        SudokuField field = new SudokuField(5);
        assertFalse(field.equals("not a SudokuField"));
    }

    //test to confirm that the hash code of two equal fields is the same
    @Test
    void testHashCode() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        assertEquals(field1.hashCode(), field2.hashCode());
    }

    //
    @Test
    void testClone() {
        SudokuField original = new SudokuField(7);
        SudokuField cloned = original.clone();
        assertEquals(original.getValue(), cloned.getValue());
        assertNotSame(original, cloned);
    }

    //test to see if the toString method returns a non-null string that contains the value of the field
    @Test
    void testToString() {
        SudokuField field = new SudokuField(4);
        String result = field.toString();
        assertNotNull(result);
        assertTrue(result.contains("4"));
    }

    
    @Test
    void testCompareTo() {
        SudokuField field1 = new SudokuField(1);
        SudokuField field2 = new SudokuField(2);
        SudokuField field3 = new SudokuField(3);

        assertDoesNotThrow(() -> {
            assertTrue(field1.compareTo(field2) < 0);
            assertTrue(field2.compareTo(field3) < 0);
            assertTrue(field1.compareTo(field3) < 0);
        });

        assertDoesNotThrow(() -> {
            assertTrue(field2.compareTo(field1) > 0);
            assertTrue(field3.compareTo(field2) > 0);
            assertTrue(field3.compareTo(field1) > 0);
        });
    }

    //test to see if the compareTo method returns 0 when comparing two fields with the same value
    @Test
    void testCompareTo_Equal() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        assertEquals(0, field1.compareTo(field2));
    }

    //test to see if the compareTo method throws a SudokuFieldComparisonException when comparing to null
    @Test
    void testCompareToWithNull_ThrowsException() {
        SudokuField field = new SudokuField(5);
        assertThrows(SudokuFieldComparisonException.class, () -> {
            field.compareTo(null);
        });
    }
}
