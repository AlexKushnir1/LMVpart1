
import java.util.Scanner;
import java.io.IOException;

public class Main {
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter command: ");
        String command = scanner.nextLine();
        scanner.close();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Доступні команди: MODE, ERASE, COPY, EXIT");
            System.out.print("Введіть команду: ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("MODE")) {
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "MODE");
                    processBuilder.inheritIO();
                    Process process = processBuilder.start();
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (command.equalsIgnoreCase("ERASE")) {
                try {
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "ERASE", fileName);
                    processBuilder.inheritIO();
                    Process process = processBuilder.start();
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (command.equalsIgnoreCase("COPY")) {
                try {
                    System.out.print("Введіть шлях до файлу: ");
                    String sourceFileName = scanner.nextLine();
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "COPY", sourceFileName, "clip");
                    processBuilder.inheritIO();
                    Process process = processBuilder.start();
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (command.equalsIgnoreCase("EXIT")) {
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }

        scanner.close();
    }
}