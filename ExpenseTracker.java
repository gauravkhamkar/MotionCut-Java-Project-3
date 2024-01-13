import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Expense {
    String date;
    String category;
    double amount;

    public Expense(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }
}

class ExpenseTracker {
    private ArrayList<Expense> expenses;
    private Scanner scanner;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    displayExpenses();
                    break;
                case 3:
                    displayCategorySummation();
                    break;
                case 4:
                    saveExpensesToFile();
                    break;
                case 5:
                    loadExpensesFromFile();
                    break;
                case 6:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void displayMenu() {
        System.out.println("Expense Tracker Menu:");
        System.out.println("1. Add Expense");
        System.out.println("2. Display Expenses");
        System.out.println("3. Display Category-wise Summation");
        System.out.println("4. Save Expenses to File");
        System.out.println("5. Load Expenses from File");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addExpense() {
        System.out.print("Enter expense date (MM/DD/YYYY): ");
        String date = scanner.nextLine();
        System.out.print("Enter expense category: ");
        String category = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();

        Expense expense = new Expense(date, category, amount);
        expenses.add(expense);

        System.out.println("Expense added successfully!");
    }

    private void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
        } else {
            System.out.println("List of Expenses:");
            for (Expense expense : expenses) {
                System.out.println("Date: " + expense.date + ", Category: " + expense.category + ", Amount: $" + expense.amount);
            }
        }
    }

    private void displayCategorySummation() {
        // Implement logic to calculate and display category-wise summation
         if (expenses.isEmpty()) {
        System.out.println("No expenses to display.");
        return;
    }

    // Create a map to store category-wise summation
    Map<String, Double> categorySumMap = new HashMap<>();

    for (Expense expense : expenses) {
        String category = expense.category;
        double amount = expense.amount;

        // Update or add the category's total expense
        categorySumMap.put(category, categorySumMap.getOrDefault(category, 0.0) + amount);
    }

    System.out.println("Category-wise Summation:");
    for (Map.Entry<String, Double> entry : categorySumMap.entrySet()) {
        System.out.println("Category: " + entry.getKey() + ", Total Expense: $" + entry.getValue());
    }

        
    }

    private void saveExpensesToFile() {
        // Implement logic to save expenses to a file
         try (PrintWriter writer = new PrintWriter(new FileWriter("expenses.txt"))) {
        for (Expense expense : expenses) {
            writer.println(expense.date + "," + expense.category + "," + expense.amount);
        }
        System.out.println("Expenses saved to file successfully.");
    } catch (IOException e) {
        System.out.println("Error saving expenses to file: " + e.getMessage());
    }
    }

    private void loadExpensesFromFile() {
        // Implement logic to load expenses from a file

         expenses.clear(); // Clear existing expenses before loading from file

    try (Scanner fileScanner = new Scanner(new File("expenses.txt"))) {
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String date = parts[0];
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);

                Expense expense = new Expense(date, category, amount);
                expenses.add(expense);
            }
        }
        System.out.println("Expenses loaded from file successfully.");
    } catch (IOException e) {
        System.out.println("Error loading expenses from file: " + e.getMessage());
    }
    }



    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        expenseTracker.run();
    }
}
