package testings;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import service.PasswordMethods;

class PasswordMethodsTest {

	@BeforeEach
	void clearContext() {
		PasswordMethods.clearLoginContext();
	}

	@Test
	void testSetAndGetLoginContext() {
		PasswordMethods.setLoginContext("TT25001", List.of("Admin", "Manager"));
		assertEquals("TT25001", PasswordMethods.getLoggedInId());
		assertTrue(PasswordMethods.hasRole("Admin"));
		assertTrue(PasswordMethods.hasRole("Manager"));
		assertFalse(PasswordMethods.hasRole("Employee"));
	}

	@Test
	void testHashConsistency() {
		String pass = "Default123";
		String hashed = PasswordMethods.hash(pass);

		assertTrue(BCrypt.checkpw(pass, hashed));
	}

	@Test
	void testRandomPasswordGenerator() {
		String pw1 = PasswordMethods.randomPasswordGenerator();
		String pw2 = PasswordMethods.randomPasswordGenerator();

		assertNotNull(pw1);
		assertNotNull(pw2);
		assertNotEquals(pw1, pw2);
		assertTrue(pw1.length() <= 12);
		assertTrue(pw2.length() <= 12);
	}

	@Test
	void testClearLoginContext() {
		PasswordMethods.setLoginContext("TT25001", List.of("Admin"));
		PasswordMethods.clearLoginContext();

		assertNull(PasswordMethods.getLoggedInId());
		assertTrue(PasswordMethods.getLoggedInRoles().isEmpty());
	}
}