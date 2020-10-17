package pl.coderslab.warsztaty;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class tasks {

    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[0;34m";

    public static void main(String[] args) {

        String[][] data = loadData();


        Scanner scanner = new Scanner(System.in);
        String input;


        do {
            System.out.println();
            menu();
            input = scanner.nextLine();
            System.out.println();
            switch (input) {
                case "add":
                    data = add(data);
                    break;
                case "remove":
                    System.out.println("choise remove");
                    break;
                case "list":
                    list(data);
                    break;
                case "exit":
                    System.out.println("choise exit");

                    break;
                default:
                    System.out.println("Wrong word, try again: ");
            }
        } while (!input.equalsIgnoreCase("exit"));
    }

    public static String[][] add(String data[][]) {
        Scanner scanner2 = new Scanner(System.in);
        String[] inputData = new String[3];

        System.out.println("Please add task description:");
        inputData[0] = scanner2.nextLine();

        System.out.println("Please add task due date:");
        inputData[1] = scanner2.nextLine();

        do {
            System.out.println("Is your task is important: true/false");
            inputData[2] = scanner2.nextLine();
        }while (!inputData[2].equals("true") != inputData[2].equals("false"));


        return data = Arrays.copyOf(data, data.length + 1);
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