package testings;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Employee;
import service.PasswordMethods;

public class TestFormats {


    private Employee emp;
    
	@BeforeEach
    void setUp() {
        emp = new Employee("TT26001",
    			"Rineesha","rin@gmail.com","hyd","Admin",new ArrayList<>
    	(List.of("Admin","Manager")),PasswordMethods.randomPasswordGenerator());
    }

	 @Test
	    void testMailFormat() {
	        assertTrue(emp.getMail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));
	    }
	 
	 @Test
	    void testEmployeeRole() {
	        assertEquals("ADMIN", emp.getRole());
	    }
	 @Test
	    void testEmployeeId() {
	        assertEquals("TT26004", emp.getId());
	    }
	
}
