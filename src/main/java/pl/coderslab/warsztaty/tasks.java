package pl.coderslab.warsztaty;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class tasks {

    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[0;34m";
    public static final String RED = "\u001B[31m";
    ;

    public static void main(String[] args) {

        String[][] data = loadData();


        Scanner scanner = new Scanner(System.in);
        String input;
        int removeField;

        do {
            System.out.println();
            menu();
            input = scanner.nextLine();
            System.out.println();
            switch (input) {
                case "add":
                    String[] inputData = add();
                    data = ArrayUtils.add(data, inputData);
                    break;
                case "remove":
                    removeField = remove(data.length);
                    data = ArrayUtils.remove(data, removeField);
                    System.out.println("Value was successfully deleted.");

                    break;
                case "list":
                    list(data);
                    break;
                case "exit":
                    exit(data);
                    break;
                default:
                    System.out.println("Wrong word, try again: ");
            }
        } while (!input.equalsIgnoreCase("exit"));

    }


    public static void exit(String[][] data) {
        Path files = Paths.get("./tasks.csv");
        String[] lines = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            lines[i] = String.join(",", data[i]);
        }
        try {
            Files.write(files, Arrays.asList(lines));
        } catch (IOException e) {
            System.err.println("Błędna ścieżka");
        }
        System.out.println(RED + "Bye Bye" + RESET);
    }


    public static int remove(int dataLength) {
        Scanner scanner = new Scanner(System.in);
        int removeNumber2 = -1;

        System.out.println("Please select number to remove (between 0 and " + (dataLength - 1) + "): ");

        String removeNumber = scanner.nextLine();
        if (NumberUtils.isParsable(removeNumber)) {
            removeNumber2 = Integer.parseInt(removeNumber);
        }

        while (!NumberUtils.isParsable(removeNumber) || (removeNumber2 >= dataLength || 0 > removeNumber2)) {
            System.out.println("Incorrect argument passed. Please give number between 0 and " + (dataLength-1));
            removeNumber = scanner.nextLine();
            if (NumberUtils.isParsable(removeNumber)) {
                removeNumber2 = Integer.parseInt(removeNumber);
            }
        }
        removeNumber2 = Integer.parseInt(removeNumber);
        System.out.println("Your choise " + removeNumber2);
        return removeNumber2;
    }

    

    public static String[] add() {
        Scanner scanner2 = new Scanner(System.in);
        String[] inputData = new String[3];

        System.out.println("Please add task description:");
        inputData[0] = " " + scanner2.nextLine();

        System.out.println("Please add task due date:");
        inputData[1] = " " + scanner2.nextLine();

        do {
            System.out.println("Is your task is important: true/false");
            inputData[2] = " " + scanner2.nextLine();
        } while (!inputData[2].equals(" true") != inputData[2].equals(" false"));

        return inputData;
    }


    public static void list(String data[][]) {
        System.out.println();
        for (int i = 0; i < data.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + ",");
            }
            System.out.println();
        }
    }


    public static String[][] loadData() {
        File files = new File("./tasks.csv");
        List<String> data = new ArrayList<String>();
        String[][] dataFromFiles = null;

        try {
            Scanner scanner = new Scanner(files);

            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            String[] copyData = data.toArray(new String[0]);
            dataFromFiles = new String[data.size()][3];

            for (int i = 0; i < data.size(); i++) {
                dataFromFiles[i] = copyData[i].split(",");
            }
        } catch (IOException e) {
            System.err.println("Brak pliku");
        }
        return dataFromFiles;

    }


    public static void menu() {
        System.out.println(BLUE + "Please select an option:" + RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

}