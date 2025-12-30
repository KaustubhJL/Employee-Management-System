package dao;

import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public void setEmployees(List<Employee> list) {
        employees.clear();
        employees.addAll(list);
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    public Employee findById(String id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public void save(Employee employee) {
        employees.add(employee);
    }

    public void delete(Employee employee) {
        employees.remove(employee);
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }
}
