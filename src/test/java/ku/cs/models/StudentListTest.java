package ku.cs.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {
    private StudentList studentList;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
    }

    @Test
    @DisplayName("Test constructor creates empty list")
    void testConstructor() {
        assertNotNull(studentList.getStudents());
        assertEquals(0, studentList.getStudents().size());
    }

    @Test
    @DisplayName("Test addNewStudent with valid id and name")
    void testAddNewStudentValid() {
        studentList.addNewStudent("123", "John Doe");
        assertEquals(1, studentList.getStudents().size());
        Student student = studentList.findStudentById("123");
        assertNotNull(student);
        assertEquals("123", student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals(0.0, student.getScore());
    }

    @Test
    @DisplayName("Test addNewStudent with only whitespace id - should not add")
    void testAddNewStudentWhitespaceId() {
        studentList.addNewStudent("   ", "John Doe");
        assertEquals(0, studentList.getStudents().size());
    }

    // filterByName() Tests
    @Test
    @DisplayName("Test filterByName with exact match")
    void testFilterByNameExact() {
        studentList.addNewStudent("123", "John Doe");
        studentList.addNewStudent("456", "Jane Smith");

        StudentList filtered = studentList.filterByName("John Doe");
        assertEquals(1, filtered.getStudents().size());
        assertEquals("John Doe", filtered.getStudents().get(0).getName());
    }

    // giveScoreToId() Tests
    @Test
    @DisplayName("Test giveScoreToId with existing student")
    void testGiveScoreToIdExists() {
        studentList.addNewStudent("123", "John Doe");
        studentList.giveScoreToId("123", 85.5);

        Student student = studentList.findStudentById("123");
        assertEquals(85.5, student.getScore());
    }

    // viewGradeOfId() Tests
    @Test
    @DisplayName("Test viewGradeOfId with existing student")
    void testViewGradeOfIdExists() {
        studentList.addNewStudent("123", "John Doe");
        studentList.giveScoreToId("123", 85.0);

        String grade = studentList.viewGradeOfId("123");
        assertEquals("A", grade);
    }

    // getStudents() Tests
    @Test
    @DisplayName("Test getStudents returns correct ArrayList")
    void testGetStudents() {
        studentList.addNewStudent("123", "John Doe");
        studentList.addNewStudent("456", "Jane Smith");

        assertEquals(2, studentList.getStudents().size());
        assertEquals("John Doe", studentList.getStudents().get(0).getName());
        assertEquals("Jane Smith", studentList.getStudents().get(1).getName());
    }

    @Test
    @DisplayName("Test getStudents with empty list")
    void testGetStudentsEmpty() {
        assertEquals(0, studentList.getStudents().size());
    }
}