package com.company;
//you have a global variable that isn't used an import that isn't used however the main method is clea, and you have good try catches
//Good as you managed to keep the main method only a list of method calls, Global variable that isn't needed.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    // attributes of each book [title, isbn, author, genre]
    private static final List<String> bookList = new ArrayList<>();

    // multiple book strings ["title, isbn, author, genre", "title, isbn, author, genre"]
    private static final List<String> bookInfo = new ArrayList<>();

    private static final List<String> useremails = new ArrayList<>();
    private static final List<String> userpassword = new ArrayList<>();

    private static int numberOfBooks;

    private static String password;

    private static String email;

    public static final int passwordLength = 8;

    private static final String regex = "^(.+)@(.+)$";


    // each is assigned a string by bookTitle(), ISBN(), author(), genre(). Then all are added to bookList which is added to bookInfo.

    private static final File bookStore = new File("BookInfo.txt");

    private static final File login = new File("login.txt");

    public static void main(String[] args) {

        menu();
        validPassword(password);


        createFile();
        getNoBooks();

        for (int i = 0; i < numberOfBooks; i++) {
            bookTitle();
            ISBN();
            author();
            genre();
        }

        writeToFile();
        readFile();
        DeleteFile();

    }

    private static void menu() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("would you like to login or register?: ");
                String userInput = input.nextLine();

                if (userInput.equalsIgnoreCase("login")) {
                    login();
                    break;
                }
                if (userInput.equalsIgnoreCase("register")) {
                    register();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid input");
            }
        }
    }

    private static void register() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in).useDelimiter("\n");

                System.out.println("please enter a email address you would like to register with: ");

                email = input.next();
                useremails.add(email);
                if (validEmail(email)) {
                    System.out.println("you have a valid email");
                    break;
                } else System.out.println("enter a valid email");
            } catch (Exception e) {
                System.out.println("error" + e);
            }
        }

        while (true) {
            try {
                Scanner input = new Scanner(System.in);

                System.out.println("please enter a password: ");

                password = input.next();
                if (validPassword(password)) {
                    System.out.println("password is valid");
                    userpassword.add(password);
                    break;
                } else System.out.println("password too weak try again");
            } catch (Exception e) {
                System.out.println("error" + e);
            }
        }

    }

    private static boolean validEmail(String email) {

        Pattern pattern = Pattern.compile(regex);
        for (String value : useremails) {
            Matcher matcher = pattern.matcher(value);

            if (matcher.matches()) return true;

            else return false;

        }
        return false;
    }

    private static boolean validPassword(String password) {
        if (Main.password.length() < passwordLength) return false;

        int charCount = 0;
        int numCount = 0;

        for (int i = 0; i < Main.password.length(); i++) {

            char ch = Main.password.charAt(i);

            if (Character.isLetter(ch)) charCount++;
            else if (Character.isDigit(ch)) numCount++;
            else if (ch == ' ') charCount++;

            else return false;
        }

        return (charCount >= 2 && numCount > 2);
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }


    public static boolean is_Numeric(char ch) {

        return (ch >= '0' && ch <= '9');
    }


    private static void login() {
        System.out.println("login");
    }

    private static void getNoBooks() {
        while (true) {
            try {
                // ask user for no. books
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("How many books do you want to store");
                numberOfBooks = Integer.parseInt(bufferedReader.readLine());

                break;
            } catch (IOException e) {
                System.out.println("Exclusively enter numbers");
            }
        }
    }

    private static void bookTitle() {
        while (true) {
            try {
                // ask user for no. books
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("please enter a book");
                bookList.add(bufferedReader.readLine());

                break;
            } catch (IOException e) {
                System.out.println("Please type a valid input: ");
            }
        }

    }

    private static void ISBN() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);

                System.out.println("Please enter the ISBN");

                bookList.add(Integer.toString(input.nextInt()));

                break;

            } catch (Exception e) {
                System.out.println("Enter only numbers");
            }
        }
    }

    private static void author() {
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Please enter the author");
                bookList.add(bufferedReader.readLine());

                break;

            } catch (Exception e) {
                System.out.println("Enter a Valid response");
            }
        }
    }

    private static void genre() {
        // automatically adds to bookInfo
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Please enter the genre");
            bookList.add(bufferedReader.readLine());
            
            bookInfo.add(String.valueOf(bookList));
            bookList.clear();
            System.out.println(bookInfo);

        } catch (Exception e) {
            System.out.println("Enter a Valid response");
        }

    }


    private static void createFile() {
        try {
            if (bookStore.createNewFile()) {
                System.out.println("File Created: " + bookStore.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter(bookStore.getName(), true);
            System.out.println("This file contains: ");

            for (int i = 0; i < bookInfo.size(); i++) {
                myWriter.write(", " + bookInfo.get(i));
            }

            myWriter.close();
            System.out.println("Successfully saved your information");
        } catch (IOException e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }

    private static void readFile() {
        try {
            Scanner input = new Scanner(bookStore);
            //input.useDelimiter("\n")
            while (input.hasNextLine()) {
                String data = input.nextLine();
                System.out.println(data);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("An error has occurred, could not locate file");
            e.printStackTrace();
        }
    }

    public static void DeleteFile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to delete the file? y/n");
        if (input.next().equalsIgnoreCase("y")) {
            if (bookStore.delete()) {
                System.out.println("Deleted the file: " + bookStore.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File not deleted");
        }

    }
}
