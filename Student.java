import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    private String group;
    private double gpa;
    
    public Student(String name, int age, String group, double gpa) {
        this.name = name;
        this.age = age;
        this.group = group;
        this.gpa = gpa;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGroup() { return group; }
    public double getGpa() { return gpa; }
    
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGroup(String group) { this.group = group; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    
    @Override
    public String toString() {
        return name + " | " + age + " лет | " + group + " | GPA: " + gpa;
    }
    
    public String toCSV() {
        return name + "," + age + "," + group + "," + gpa;
    }
    
    public static Student fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            return new Student(
                parts[0],
                Integer.parseInt(parts[1]),
                parts[2],
                Double.parseDouble(parts[3])
            );
        }
        return null;
    }
}