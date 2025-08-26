package ku.cs.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;
    
    @BeforeEach
    void setUp() {
        student = new Student("6710400000", "John Doe");
    }
    
    // Constructor Tests
    @Test
    @DisplayName("Test constructor with id and name - score should be 0")
    void testConstructorWithIdAndName() {
        Student s = new Student("123", "Test Student");
        assertEquals("123", s.getId());
        assertEquals("Test Student", s.getName());
        assertEquals(0.0, s.getScore());
    }
    
    @Test
    @DisplayName("Test constructor with id, name, and score")
    void testConstructorWithIdNameAndScore() {
        Student s = new Student("123", "Test Student", 85.5);
        assertEquals("123", s.getId());
        assertEquals("Test Student", s.getName());
        assertEquals(85.5, s.getScore());
    }
    
    // changeName() Tests
    @Test
    @DisplayName("Test changeName with valid name")
    void testChangeNameValid() {
        student.changeName("Jane Smith");
        assertEquals("Jane Smith", student.getName());
    }
    
    @Test
    @DisplayName("Test changeName with name having whitespace - should trim")
    void testChangeNameWithWhitespace() {
        student.changeName("  Jane Smith  ");
        assertEquals("Jane Smith", student.getName());
    }
    
    @Test
    @DisplayName("Test changeName with empty name - should not change")
    void testChangeNameEmpty() {
        String originalName = student.getName();
        student.changeName("");
        assertEquals(originalName, student.getName());
    }
    
    @Test
    @DisplayName("Test changeName with only whitespace - should not change")
    void testChangeNameOnlyWhitespace() {
        String originalName = student.getName();
        student.changeName("   ");
        assertEquals(originalName, student.getName());
    }
    
    // addScore() Tests
    @Test
    @DisplayName("Test addScore with positive value")
    void testAddScorePositive() {
        student.addScore(75.5);
        assertEquals(75.5, student.getScore());
    }
    
    @Test
    @DisplayName("Test addScore multiple times - should accumulate")
    void testAddScoreMultiple() {
        student.addScore(40.0);
        student.addScore(35.5);
        assertEquals(75.5, student.getScore());
    }
    
    @Test
    @DisplayName("Test addScore with zero - should not change")
    void testAddScoreZero() {
        double originalScore = student.getScore();
        student.addScore(0);
        assertEquals(originalScore, student.getScore());
    }
    
    @Test
    @DisplayName("Test addScore with negative value - should not change")
    void testAddScoreNegative() {
        student.addScore(50.0); // Add some initial score
        double scoreAfterPositive = student.getScore();
        student.addScore(-10.0);
        assertEquals(scoreAfterPositive, student.getScore());
    }
    
    // grade() Tests
    @Test
    @DisplayName("Test grade A (score >= 80)")
    void testGradeA() {
        student.addScore(85.0);
        assertEquals("A", student.grade());
        assertEquals("A", student.getGrade()); // Test getGrade() method too
    }
    
    @Test
    @DisplayName("Test grade A boundary (score = 80)")
    void testGradeABoundary() {
        student.addScore(80.0);
        assertEquals("A", student.grade());
    }
    
    @Test
    @DisplayName("Test grade B (70 <= score < 80)")
    void testGradeB() {
        student.addScore(75.0);
        assertEquals("B", student.grade());
    }
    
    @Test
    @DisplayName("Test grade B boundary (score = 70)")
    void testGradeBBoundary() {
        student.addScore(70.0);
        assertEquals("B", student.grade());
    }
    
    @Test
    @DisplayName("Test grade C (60 <= score < 70)")
    void testGradeC() {
        student.addScore(65.0);
        assertEquals("C", student.grade());
    }
    
    @Test
    @DisplayName("Test grade C boundary (score = 60)")
    void testGradeCBoundary() {
        student.addScore(60.0);
        assertEquals("C", student.grade());
    }
    
    @Test
    @DisplayName("Test grade D (50 <= score < 60)")
    void testGradeD() {
        student.addScore(55.0);
        assertEquals("D", student.grade());
    }
    
    @Test
    @DisplayName("Test grade D boundary (score = 50)")
    void testGradeDBoundary() {
        student.addScore(50.0);
        assertEquals("D", student.grade());
    }
    
    @Test
    @DisplayName("Test grade F (score < 50)")
    void testGradeF() {
        student.addScore(45.0);
        assertEquals("F", student.grade());
    }
    
    @Test
    @DisplayName("Test grade F with zero score")
    void testGradeFZero() {
        assertEquals("F", student.grade());
    }
    
    // isId() Tests
    @Test
    @DisplayName("Test isId with correct id")
    void testIsIdCorrect() {
        assertTrue(student.isId("6710400000"));
    }
    
    @Test
    @DisplayName("Test isId with incorrect id")
    void testIsIdIncorrect() {
        assertFalse(student.isId("1234567890"));
    }
    
    // isNameContains() Tests
    @Test
    @DisplayName("Test isNameContains with exact match")
    void testIsNameContainsExact() {
        assertTrue(student.isNameContains("John Doe"));
    }
    
    @Test
    @DisplayName("Test isNameContains with partial match")
    void testIsNameContainsPartial() {
        assertTrue(student.isNameContains("John"));
        assertTrue(student.isNameContains("Doe"));
    }
    
    @Test
    @DisplayName("Test isNameContains case insensitive")
    void testIsNameContainsCaseInsensitive() {
        assertTrue(student.isNameContains("john"));
        assertTrue(student.isNameContains("JOHN"));
        assertTrue(student.isNameContains("JoHn DoE"));
    }
    
    @Test
    @DisplayName("Test isNameContains with no match")
    void testIsNameContainsNoMatch() {
        assertFalse(student.isNameContains("Smith"));
    }
    
    @Test
    @DisplayName("Test isNameContains with empty string")
    void testIsNameContainsEmpty() {
        assertTrue(student.isNameContains(""));
    }
    
    // Getter Tests
    @Test
    @DisplayName("Test getId()")
    void testGetId() {
        assertEquals("6710400000", student.getId());
    }
    
    @Test
    @DisplayName("Test getName()")
    void testGetName() {
        assertEquals("John Doe", student.getName());
    }
    
    @Test
    @DisplayName("Test getScore()")
    void testGetScore() {
        assertEquals(0.0, student.getScore());
        student.addScore(50.5);
        assertEquals(50.5, student.getScore());
    }
    
    // toString() Test
    @Test
    @DisplayName("Test toString() format")
    void testToString() {
        student.addScore(85.5);
        String expected = "{id: '6710400000', name: 'John Doe', score: 85.5}";
        assertEquals(expected, student.toString());
    }
}
