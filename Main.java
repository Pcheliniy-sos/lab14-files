import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== ЗАГРУЗКА ДАННЫХ ===");
        manager.loadData();
        
        while (true) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Добавить студента");
            System.out.println("2. Показать всех");
            System.out.println("3. Удалить студента");
            System.out.println("4. Сохранить в CSV");
            System.out.println("5. Загрузить из CSV");
            System.out.println("6. Сохранить в бинарный");
            System.out.println("7. Загрузить из бинарного");
            System.out.println("8. Выйти");
            System.out.print("Выберите: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Возраст: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Группа: ");
                    String group = scanner.nextLine();
                    System.out.print("Средний балл: ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine();
                    manager.add(new Student(name, age, group, gpa));
                    System.out.println("✅ Добавлен!");
                    break;
                    
                case 2:
                    manager.printAll();
                    break;
                    
                case 3:
                    System.out.print("Имя для удаления: ");
                    manager.remove(scanner.nextLine());
                    System.out.println("✅ Удалено!");
                    break;
                    
                case 4:
                    manager.saveCSV();
                    break;
                    
                case 5:
                    manager.loadCSV();
                    break;
                    
                case 6:
                    manager.saveBinary();
                    break;
                    
                case 7:
                    manager.loadBinary();
                    break;
                    
                case 8:
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}