import java.io.*;
import java.util.*;

class Employee extends User {
    public Employee(String username, String password) {
        super(username, password);
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("employees.txt", true))) {
            writer.println(getUsername() + "," + getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      public void removeFromDatabase() {
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(getUsername() + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try (PrintWriter writer = new PrintWriter(new FileWriter("employees.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}