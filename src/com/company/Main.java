package com.company;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

    //stores multiple registered emails
    private static final List<String> userEmail = new ArrayList<>();

    //stores multiple registered passwords
    private static final List<String> userPassword = new ArrayList<>();

    private static int numberOfBooks;

    private static String password;

    private static String email;

    //searches for patterns to see if an email is valid or not
    private static final String regex = "^(.+)@(.+)$";


    // each is assigned a string by bookTitle(), ISBN(), author(), genre(). Then all are added to bookList which is added to bookInfo.

    private static final File bookStore = new File("BookInfo.txt");


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
                //main menu to ask for login and register
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

                } else {
                    System.out.println("Please enter a valid input");
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
                userEmail.add(email);//need to fix where this is (validate the email then add it to the array)

                //checks if an email is valid with regex, if it is it will proceed to ask for a password
                if (validEmail(email)) {
                    System.out.println("you have a valid email");
                    break;
                } else {
                    System.out.println("invalid email try again");
                }
            } catch (Exception e) {
                System.out.println("error" + e);
            }
        }

        while (true) {
            try {
                Scanner input = new Scanner(System.in);

                System.out.println("please enter a password: ");

                password = input.next();
                //checks if a password is valid, the password has to have at least 8 letters and 2 numbers in it for a password to be valid
                if (validPassword(password)) {
                    System.out.println("password is valid");
                    userPassword.add(password);
                    break;
                } else System.out.println("password too weak try again");
            } catch (Exception e) {
                System.out.println("error" + e);
            }
        }
        menu();
    }

    private static boolean validEmail(String email) {
        //uses regex to see if an email is valid using patterns
        Pattern pattern = Pattern.compile(regex);
        for (String value : userEmail) {
            Matcher matcher = pattern.matcher(value);
            //if the email matches the pattern it will return value as true so the email will be valid
            if (matcher.matches()) return true;
            // if the password doesn't match the regex it will return false and the email will not be valid
            else return false;

        }
        return false;
    }

    private static boolean validPassword(String password) {
        final int passwordLength = 8;
        if (Main.password.length() < passwordLength) return false; // if the password length is greater than the inputted password length it will return as false
                                                                   // meaning it's not valid
        int charCount = 0;
        int numCount = 0;

        for (int i = 0; i < Main.password.length(); i++) { //this will loop through every character in the password and add to the num and char count

            char ch = Main.password.charAt(i);

            if (is_Letter(ch)) charCount++; //for every char it will add 1 to the char count
            else if (is_Numeric(ch)) numCount++; // for every number it will add 1 to the char count
            else if (ch == ' ') charCount++; //spaces will add to the char count as well

            else return false;
        }

        return (charCount >= 2 && numCount > 2); //num count has to be greater than 2 and char count has to be equal or less than to 2 to come back as valid
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    public static boolean is_Numeric(char ch) {

        return (ch >= '0' && ch <= '9');
    }

    private static void login() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Please enter a email");

                String emailInput = input.next();

                for (int i = 0; i < userEmail.size(); i++) {
                    String currentMail = userEmail.get(i); //if the user inputs an email that is registered it will ask for the password
                    if (emailInput.equals(currentMail)) {

                        System.out.println("Please enter a password");
                        String passwordInput = input.next();

                        if (passwordInput.equals(userPassword.get(i))) { //it will log in if the user matches the password correctly with the one registered
                            System.out.println("you have logged in");
                            break;
                        } else {
                            System.out.println("invalid password try again");
                        }
                    } else {
                        System.out.println("invalid email try again");
                    }
                }
            } catch (Exception e) {
                System.out.println("error" + e);
            }
            return;
        }
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
            FileWriter myWriter = new FileWriter(bookStore.getName(), true); //writes the attributes to a file in order
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
            Scanner input = new Scanner(bookStore); //reads what's in the file and prints it out
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
        Scanner input = new Scanner(System.in); //asks if you want to delete the whole library
        System.out.println("Do you want to delete the file? y/n"); // (need to make an edit feature where you can edit the names and delete certain attributes)
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
