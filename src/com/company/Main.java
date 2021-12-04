package com.company;
//you have a global variable that isn't used an import that isn't used however the main method is clea, and you have good try catches
//Good as you managed to keep the main method only a list of method calls, Global variable that isn't needed.

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

    // attributes of each book [title, isbn, author, genre]
    private static final List<String> bookList = new ArrayList<>();

    // multiple book strings ["title, isbn, author, genre", "title, isbn, author, genre"]
    private static final List<String> bookInfo = new ArrayList<>();

    private static int numberOfBooks;

    // each is assigned a string by bookTitle(), ISBN(), author(), genre(). Then all are added to bookList which is added to bookInfo.

    private static final File bookStore = new File("BookInfo.txt");

    public static void main(String[] args) {
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

        private static void genre () {
            // automatically adds to bookInfo
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Please enter the genre");
                bookList.add(bufferedReader.readLine());

//                String result = "";
//                for (int i = 0; i < bookList.size(); ++i) {
//                    if (i != bookList.size()-1) {
//                        result += bookList.get(i) + ",";
//                    } else {
//                        result += bookList.get(i);
//                    }
//                }
                bookInfo.add(String.valueOf(bookList));
                bookList.clear();
                System.out.println(bookInfo);

            } catch (Exception e) {
                System.out.println("Enter a Valid response");
            }

        }


        private static void createFile () {
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

        private static void readFile () {
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

