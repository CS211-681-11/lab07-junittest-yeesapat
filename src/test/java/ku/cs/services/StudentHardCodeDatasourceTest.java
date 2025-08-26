package ku.cs.services;

import ku.cs.models.Student;
import ku.cs.models.StudentList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class StudentHardCodeDatasourceTest {
    private StudentHardCodeDatasource datasource;
    
    @BeforeEach
    void setUp() {
        datasource = new StudentHardCodeDatasource();
    }
    
    @Test
    @DisplayName("Test readData returns StudentList with correct data")
    void testReadData() {
        StudentList studentList = datasource.readData();
        
        assertNotNull(studentList);
        assertEquals(4, studentList.getStudents().size());
        Student first = studentList.findStudentById("6710400001");
        assertNotNull(first);
        assertEquals("6710400001", first.getId());
        assertEquals("First", first.getName());
        assertEquals(0.0, first.getScore()); // Default score
        
        Student second = studentList.findStudentById("6710400002");
        assertNotNull(second);
        assertEquals("6710400002", second.getId());
        assertEquals("Second", second.getName());
        assertEquals(0.0, second.getScore());
        
        Student third = studentList.findStudentById("6710400003");
        assertNotNull(third);
        assertEquals("6710400003", third.getId());
        assertEquals("Third", third.getName());
        assertEquals(0.0, third.getScore());
        
        Student fourth = studentList.findStudentById("6710400004");
        assertNotNull(fourth);
        assertEquals("6710400004", fourth.getId());
        assertEquals("Fourth", fourth.getName());
        assertEquals(0.0, fourth.getScore());
    }
    
    @Test
    @DisplayName("Test readData always returns same data")
    void testReadDataConsistency() {
        StudentList list1 = datasource.readData();
        StudentList list2 = datasource.readData();
        assertNotSame(list1, list2);
        assertEquals(list1.getStudents().size(), list2.getStudents().size());
        // Compare each
        for (int i = 0; i < list1.getStudents().size(); i++) {
            Student s1 = list1.getStudents().get(i);
            Student s2 = list2.getStudents().get(i);
            assertNotSame(s1, s2); // Different objects
            assertEquals(s1.getId(), s2.getId()); // Same data
            assertEquals(s1.getName(), s2.getName());
            assertEquals(s1.getScore(), s2.getScore());
        }
    }
    
    @Test
    @DisplayName("Test readData returns modifiable StudentList")
    void testReadDataModifiable() {
        StudentList studentList = datasource.readData();
        
        // Should be able to modify the returned list
        studentList.addNewStudent("999", "New Student");
        assertEquals(5, studentList.getStudents().size());
        
        // Should be able to modify existing students
        studentList.giveScoreToId("6710400001", 85.0);
        assertEquals(85.0, studentList.findStudentById("6710400001").getScore());
        
        // Original datasource should still return unmodified data
        StudentList freshList = datasource.readData();
        assertEquals(4, freshList.getStudents().size());
        assertEquals(0.0, freshList.findStudentById("6710400001").getScore());
    }
    
    @Test
    @DisplayName("Test all expected student IDs are present")
    void testAllStudentIdsPresent() {
        StudentList studentList = datasource.readData();
        
        String[] expectedIds = {"6710400001", "6710400002", "6710400003", "6710400004"};
        String[] expectedNames = {"First", "Second", "Third", "Fourth"};
        
        for (int i = 0; i < expectedIds.length; i++) {
            Student student = studentList.findStudentById(expectedIds[i]);
            assertNotNull(student, "Student with ID " + expectedIds[i] + " should exist");
            assertEquals(expectedNames[i], student.getName());
        }
    }
    
    @Test
    @DisplayName("Test no duplicate student IDs")
    void testNoDuplicateIds() {
        StudentList studentList = datasource.readData();
        
        String[] expectedIds = {"6710400001", "6710400002", "6710400003", "6710400004"};
        
        // Each ID should exist exactly once
        for (String id : expectedIds) {
            int count = 0;
            for (Student student : studentList.getStudents()) {
                if (student.getId().equals(id)) {
                    count++;
                }
            }
            assertEquals(1, count, "ID " + id + " should appear exactly once");
        }
    }
    
    @Test
    @DisplayName("Test constructor creates valid instance")
    void testConstructor() {
        StudentHardCodeDatasource newDatasource = new StudentHardCodeDatasource();
        assertNotNull(newDatasource);
        
        StudentList data = newDatasource.readData();
        assertNotNull(data);
        assertEquals(4, data.getStudents().size());
    }
}
