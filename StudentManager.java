import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private static final String CSV_FILE = "students.csv";
    private static final String BIN_FILE = "students.dat";
    
    public void add(Student s) {
        students.add(s);
    }
    
    public void remove(String name) {
        students.removeIf(s -> s.getName().equalsIgnoreCase(name));
    }
    
    public List<Student> getAll() {
        return students;
    }
    
    public void printAll() {
        if (students.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        System.out.println("\n=== СТУДЕНТЫ ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
        System.out.println("Всего: " + students.size());
    }
    
    public void saveCSV() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Student s : students) {
                w.write(s.toCSV());
                w.newLine();
            }
            System.out.println("✅ Сохранено в " + CSV_FILE);
        } catch (IOException e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }
    }
    
    public void loadCSV() {
        File f = new File(CSV_FILE);
        if (!f.exists()) {
            System.out.println("Файл " + CSV_FILE + " не найден.");
            return;
        }
        students.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                Student s = Student.fromCSV(line);
                if (s != null) students.add(s);
            }
            System.out.println("✅ Загружено из " + CSV_FILE);
        } catch (IOException e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }
    }
    
    public void saveBinary() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_FILE))) {
            oos.writeObject(students);
            System.out.println("✅ Сохранено в " + BIN_FILE);
        } catch (IOException e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public void loadBinary() {
        File f = new File(BIN_FILE);
        if (!f.exists()) {
            System.out.println("Файл " + BIN_FILE + " не найден.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BIN_FILE))) {
            students = (List<Student>) ois.readObject();
            System.out.println("✅ Загружено из " + BIN_FILE);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }
    }
    
    public void loadData() {
        loadBinary();
        if (students.isEmpty()) {
            loadCSV();
        }
    }
}