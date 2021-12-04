package com.company;
//you have a global variable that isn't used an import that isn't used however the main method is clea, and you have good try catches
//Good as you managed to keep the main method only a list of method calls, Global variable that isn't needed.

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

    private static List<String> bookList = new ArrayList<>();

    private static List<String> bookInfo = new ArrayList<>();

    private static int numberOfBooks = 0;

    private static String isbn, bookname, author, genre;


    private static File bookStore = new File("BookInfo.txt");

    public static void main(String[] args) {
        bookTitle();
        ISBN();
        author();
        genre();


        createFile();

        writeToFile();

        readFile();


    }

    private static void bookTitle() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("How many books do you want to store");
            numberOfBooks = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < numberOfBooks; i++) {
                System.out.println("please enter a book");
                bookname = (bufferedReader.readLine());

                bookList.add(bookname);
            }
        } catch (Exception e) {
            System.out.println("Enter a valid response");
        }
    }

    private static void ISBN() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                for (int i = 0; i < numberOfBooks; i++) {
                    System.out.println("Please enter the ISBN");
                    isbn = Integer.toString(input.nextInt());
                    bookList.add(isbn);
                }

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
                for (int i = 0; i < numberOfBooks; i++) {
                    System.out.println("Please enter the author");
                    author = (bufferedReader.readLine());
                    bookList.add(author);
                }

                break;

            } catch (Exception e) {
                System.out.println("Enter a Valid response");
            }
        }
    }

        private static void genre () {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                for (int i = 0; i < numberOfBooks; ++i) {
                    System.out.println("Please enter the genre");
                    genre = bufferedReader.readLine();
                    //bookList.add(genre);
                    bookInfo.add(bookList.get(i) + "," + (isbn) + "," + (author) + "," + (genre));
                }

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

        private static void writeToFile () {
            try {
                FileWriter myWriter = new FileWriter(bookStore.getName(), true);
                System.out.println("This file contains: ");
                myWriter.write(String.valueOf(bookInfo));
                myWriter.close();
                System.out.println("Successfully saved your information");
            } catch (IOException e) {
                System.out.println("An error has occurred");
                e.printStackTrace();
            }
        }

        private static void readFile () {
            try {
                Scanner input = new Scanner(System.in);
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
    }

