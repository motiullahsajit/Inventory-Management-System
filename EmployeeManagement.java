import java.util.*;
class EmployeeManagement {
    private Map<String, String> employees;

    public EmployeeManagement() {
        this.employees = new HashMap<>();
    }

    public void addEmployee(String employeeName, String username, String password) {
        employees.put(employeeName, username + ":" + password);
    }

    public void removeEmployee(String employeeName) {
        employees.remove(employeeName);
    }

    public Map<String, String> getEmployees() {
        return employees;
    }

    public String getEmployeeCredentials(String employeeName) {
        return employees.get(employeeName);
    }
    
}
