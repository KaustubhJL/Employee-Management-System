package service;

import java.time.Year;
//import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class SetNextID {
	public static String generateNextId(List<Employee> list) {
		int max = 000;
		int next;
		for (Employee e : list) {
			String id = e.getId();
			int num = Integer.parseInt(id.substring(4));
			max = Math.max(max, num);
		}
		next = max + 1;
		int year = Year.now().getValue() % 100;
		return "TT" + year + String.format("%03d", next);
	}
}
