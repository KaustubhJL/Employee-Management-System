package controller;

import java.util.Scanner;

import service.AccessAndPasswordMethods;
import service.Manage;

import java.io.Console;
import java.util.Arrays;

public class Login {

	public static void authenticate(Manage ops, Scanner sc) {
		Console console = System.console();

		while (true) {
			System.out.print("ID: ");
			String id = sc.nextLine();

			char[] passChars;
			if (console != null)
				passChars = console.readPassword("Password: ");
			else {
				System.out.print("Password: ");
				passChars = sc.nextLine().toCharArray();
			}

			String pass = new String(passChars);
			Arrays.fill(passChars, '\0');

			if (AccessAndPasswordMethods.acc(ops, id, pass)) {
				System.out.println("Login successful\n");
				return;
			}
			System.out.println("Invalid credentials\n");
		}
	}
}