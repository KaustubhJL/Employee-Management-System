package testings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import model.Employee;
import service.SetNextID;

@ExtendWith(MockitoExtension.class)
public class TestServices {

    @Test
    void testIdGenWithMockedDatabase() {
        // 1️⃣ Mock the "DB"
        List<Employee> mockDb = mock(List.class);

        Employee emp1 = mock(Employee.class);
        Employee emp2 = mock(Employee.class);

        when(emp1.getId()).thenReturn("TT25001");
        when(emp2.getId()).thenReturn("TT25002");

        when(mockDb.size()).thenReturn(2);
        when(mockDb.get(0)).thenReturn(emp1);
        when(mockDb.get(1)).thenReturn(emp2);

        String nextId = SetNextID.generateNextId(mockDb);

        assertEquals("TT25003", nextId);

        verify(mockDb, atLeastOnce()).get(anyInt());
        verify(emp1).getId();
        verify(emp2).getId();
    }
}
