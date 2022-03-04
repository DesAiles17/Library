package libraryapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
 
    public static void main(String[] args) {
        List<Book> books = new ArrayList();
        List<Client> clients = new ArrayList();
        List<RentHistoryItem> history = new ArrayList();
        loadData(history, books, clients);
        while (true) {
            System.out.println("\n***************************");
            System.out.println("1.Library");
            System.out.println("2.Client");
            System.out.println("3.Books");
            System.out.println("4.Save data and exit");
            int a = getSelectedItem(4);
            switch (a) {
                case 1:
                    libraryMenu(books, clients, history);
                    break;
                case 2:
                    clientMenu(books, clients, history);
                    break;
                case 3:
                    booksMenu(books, clients, history);
                    break;
                case 4:
                    saveData(history, books, clients);
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    public static void booksMenu(List<Book> books, List<Client> clients, List<RentHistoryItem> history) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.Show book list");
            System.out.println("2.Add book");
            System.out.println("3.Edit book");
            System.out.println("4.Remove book");
            System.out.println("5.Search book");
            System.out.println("6.Back");
            System.out.println("7.Save data and exit");
            int a = getSelectedItem(7);
            switch (a) {
                case 1:
                    sortByBooks(books);
                    break;
                case 2:
                    addBook(books);
                    continue;
                case 3:
                    editBook(books);
                    break;
                case 4:
                    removeBook(books, clients, history);
                    break;
                case 5:
                    bookSearch(books);
                    break;

                case 6:
                    OK = false;
                    break;
                case 7:
                    saveData(history, books, clients);
                    System.exit(0);
                default:
                    break;
            }
        }
    }


    public static void sortByBooks(List<Book> books) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.Sort by name");
            System.out.println("2.Sort by author");
            System.out.println("3.Sort by id");
            System.out.println("4.Back");
            int a = getSelectedItem(4);
            switch (a) {
                case 1:
                case 2:
                case 3:
                    bookPrinter(books, a);
                    break;
                case 4:
                    OK = false;
                    break;
                default:
                    break;
            }
        }
    }


    public static void sortByClients(List<Client> clients) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.Sort by name");
            System.out.println("2.Sort by surname");
            System.out.println("3.Sort by id");
            System.out.println("4.Back");
            int a = getSelectedItem(4);
            switch (a) {
                case 1:
                case 2:
                case 3:
                    clientPrinter(clients, a);
                    break;
                case 4:
                    OK = false;
                    break;
                default:
                    break;
            }
        }
    }


    public static void libraryMenu(List<Book> books, List<Client> clients, List<RentHistoryItem> history) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.Rent book");
            System.out.println("2.Return book");
            System.out.println("3.Rented books"); //by user / all
            System.out.println("4.Rent history");
            System.out.println("5.Back");
            System.out.println("6.Save data and exit");
            int a = getSelectedItem(6);
            switch (a) {
                case 1:
                    rentBook(history, books, clients);
                    break;
                case 2:
                    returnBook(history, books, clients);
                    break;
                case 3:
                    rentedBooks(books, clients, history);
                    break;
                case 4:
                    booksHistory(books, clients, history);
                    break;
                case 5:
                    OK = false;
                    break;
                case 6:
                    saveData(history, books, clients);
                    System.exit(0);
                default:
                    break;
            }
        }
    }


    public static void rentedBooks(List<Book> books, List<Client> clients, List<RentHistoryItem> history) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.All Rented books");
            System.out.println("2.Rented books by user"); 
            System.out.println("3.Back");
            System.out.println("4.Exit");
            int a = getSelectedItem(4);
            switch (a) {
                case 1:
                    List<Book> rentedBooks = new ArrayList();
                    for (Book book : books) {
                        if (book.isInLibrary() == false) {
                            rentedBooks.add(book);
                        }
                    }
                    System.out.println("Rented Books: ");
                    bookPrinter(rentedBooks, 5);
                    break;
                case 2:
                    List<Client> clientsWitchBooks = new ArrayList();
                    for (Client client : clients) {
                        if (client.getRentedBooks().size() > 0) {
                            clientsWitchBooks.add(client);
                        }
                    }
                    clientPrinter(clientsWitchBooks, 5);
                    int id = 0;
                    boolean OK2;
                    do {
                        OK2 = false;
                        id = getInt("client id");
                        for (Client client : clients) {
                            if (client.getId() == id) {
                                OK2 = true;
                                getBooksByClient(client, books);
                                break;
                            }
                        }
                        if (!OK2) {
                            System.err.println("Enter correct client id!");
                        }
                    } while (!OK2);

                    break;
                case 3:
                    OK = false;
                    break;
                case 4:
                    saveData(history, books, clients);
                    System.exit(0);
                default:
                    break;
            }
        }
    }


    public static void booksHistory(List<Book> books, List<Client> clients, List<RentHistoryItem> history) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.History by book");
            System.out.println("2.History by user");
            System.out.println("3.Back");
            System.out.println("4.Exit");
            int a = getSelectedItem(4);
            switch (a) {
                case 1:
                    bookPrinter(books, 5);
                    int id = 0;
                    boolean OK2;
                    do {
                        OK2 = false;
                        id = getInt("book id");
                        for (Book book : books) {
                            if (book.getId() == id) {
                                OK2 = true;
                                getHistoryByBook(id, history, books, clients);
                                break;
                            }
                        }
                        if (!OK2) {
                            System.err.println("Enter correct book id!");
                        }
                    } while (!OK2);
                    break;
                case 2:
                    clientPrinter(clients, 5);
                    int id2 = 0;
                    boolean OK3;
                    do {
                        OK3 = false;
                        id2 = getInt("client id");
                        for (Client client : clients) {
                            if (client.getId() == id2) {
                                OK3 = true;
                                getHistoryByClient(id2, history, books, clients);
                                break;
                            }
                        }
                        if (!OK3) {
                            System.err.println("Enter correct client id!");
                        }
                    } while (!OK3);

                    break;
                case 3:
                    OK = false;
                    break;
                case 4:
                    saveData(history, books, clients);
                    System.exit(0);
                default:
                    break;
            }
        }
    }


    public static void clientMenu(List<Book> books, List<Client> clients, List<RentHistoryItem> history) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.Add client");
            System.out.println("2.Block client");
            System.out.println("3.Edit client");
            System.out.println("4.Show clients");
            System.out.println("5.Back");
            System.out.println("6.Save data and exit");
            int a = getSelectedItem(6);
            switch (a) {

                case 1:
                    addClient(clients);
                    break;
                case 2:
                    blockClient(clients);
                    break;
                case 3:
                    editClient(clients);
                    break;
                case 4:
                    sortByClients(clients);
                    break;
                case 5:
                    OK = false;
                    break;
                case 6:
                    saveData(history, books, clients);
                    System.exit(0);
                default:
                    break;
            }
        }
    }


    public static int getSelectedItem(int maxMenuItem) {
        Scanner scanner = new Scanner(System.in);
        String line;
        int item = 0;
        boolean OK;
        do {
            try {
                OK = true;
                System.out.print("Enter menu item:\n>");
                line = scanner.nextLine();
                item = Integer.parseInt(line);
                if (item <= 0 || item > maxMenuItem) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException | NumberFormatException ex) {
                OK = false;
                System.err.println("Please enter correct item");
            }
        } while (!OK);
        return item;
    }


    public static int getInt(String type) {
        Scanner scanner = new Scanner(System.in);
        String line;
        int item = 0;
        boolean OK;
        do {
            try {
                OK = true;
                System.out.printf("Enter %s :\n>", type);
                line = scanner.nextLine();
                item = Integer.parseInt(line);
            } catch (InputMismatchException | NumberFormatException ex) {
                OK = false;
                System.err.println("Please enter correct number");
            }
        } while (!OK);

        return item;
    }


    public static void addBook(List<Book> books) {
        String author;
        String name;
        int id;
        boolean OK;
        do {
            OK = true;
            id = getInt("id");
            author = getLine("author");
            name = getLine("name");
            for (Book book : books) {
                if (book.getId() == id) {
                    OK = false;
                    System.err.println("Enter unique id!");
                }
            }
        } while (!OK);
        books.add(new Book(id, author, name, true));
        System.out.println("Book added successfully");
    }


    public static String getLine(String type) {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        boolean OK;
        do {
            try {
                OK = true;
                System.out.printf("Enter %s :\n>", type);
                line = scanner.nextLine();
                if (line == "") {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException ex) {
                OK = false;
                System.err.println("Please enter correct name");
            }
        } while (!OK);
        return line;
    }

    public static void bookPrinter(List<Book> books, int sortBy) {
        List<Book> bookList = new ArrayList();
        switch (sortBy) {
            case 1:
                bookList = booksSorter(books, 1);
                break;
            case 2:
                bookList = booksSorter(books, 2);
                break;
            case 3:
                bookList = booksSorter(books, 3);
                break;
            default:
                bookList = booksSorter(books, 1);
                break;
        }

        System.out.println("Books  (id | name | author | in library? ): ");
        for (Book book : bookList) {
            System.out.printf(" %d | %s | %s | %b \n", book.getId(), book.getName(), book.getAuthor(), book.isInLibrary());
        }
        System.out.println("--------------------------------");
    }

    public static List<Book> booksSorter(List<Book> books, int i) {
        List<Book> sortedBooks = new ArrayList();
        sortedBooks.addAll(books);
        List<Book> result = new ArrayList();
        switch (i) {
            case 1:
                while (sortedBooks.size() > 0) {
                    Book curMin = sortedBooks.get(0);
                    for (Book book : sortedBooks) {
                        if (book.getName().compareTo(curMin.getName()) < 0) {
                            curMin = book;
                        }
                    }
                    result.add(curMin);
                    sortedBooks.remove(curMin);
                }
                break;
            case 2:
                while (sortedBooks.size() > 0) {
                    Book curMin = sortedBooks.get(0);
                    for (Book book : sortedBooks) {
                        if (book.getAuthor().compareTo(curMin.getAuthor()) < 0) {
                            curMin = book;
                        }
                    }
                    result.add(curMin);
                    sortedBooks.remove(curMin);
                }
                break;
            case 3:
                while (sortedBooks.size() > 0) {
                    Book curMin = sortedBooks.get(0);
                    for (Book book : sortedBooks) {
                        if (book.getId() < curMin.getId()) {
                            curMin = book;
                        }
                    }
                    result.add(curMin);
                    sortedBooks.remove(curMin);
                }
                break;
        }
        return result;
    }


    public static void removeBook(List<Book> books, List<Client> clients, List<RentHistoryItem> history) {
        bookPrinter(books, 5);
        int id = 0;
        boolean OK;
        do {
            OK = false;
            id = getInt("book id");
            for (Book book : books) {
                if (book.getId() == id) {
                    OK = true;
                    System.out.println("Successfully deleted!");
                    for (Client client : clients) {
                        client.removeBook(book.getId());
                    }
                    books.remove(book);
                    break;
                }
            }
            if (!OK) {
                System.err.println("Enter correct book id!");
            }
        } while (!OK);
    }


    public static void editBook(List<Book> books) {
        bookPrinter(books, 5);
        int id = 0;
        boolean OK;
        do {
            OK = false;
            id = getInt("book id");
            for (Book book : books) {
                if (book.getId() == id) {
                    OK = true;
                    editBookItem(book);
                    break;
                }
            }
            if (!OK) {
                System.err.println("Enter correct book id!");
            }
        } while (!OK);
    }


    public static void editBookItem(Book book) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.Edit name");
            System.out.println("2.Edit author");
            // System.out.println("3.Edit id");
            int a = getSelectedItem(2);
            switch (a) {
                case 1:
                    String name = getLine("name");
                    book.setName(name);
                    System.out.println("Success edited!");
                    OK = false;
                    break;
                case 2:
                    String author = getLine("name");
                    book.setAuthor(author);
                    System.out.println("Success edited!");
                    OK = false;
                    break;
                case 3:
                    int id = getInt("id");
                    book.setId(id);
                    System.out.println("Success edited!");
                    OK = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static void bookSearch(List<Book> books) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.Search by name");
            System.out.println("2.Search by author");
            System.out.println("3.Search by id");
            System.out.println("4.Back");
            int a = getSelectedItem(4);
            switch (a) {
                case 1:
                case 2:
                case 3:
                    bookSearcher(books, a);
                    break;
                case 4:
                    OK = false;
                    break;
                default:
                    break;
            }
        }
    }


    public static void bookSearcher(List<Book> books, int searchBy) {
        List<Book> foundedBooks = new ArrayList();
        switch (searchBy) {
            case 1:
                String searchLine = getLine("search name");
                for (Book book : books) {
                    if (book.getName().contains(searchLine)) {
                        foundedBooks.add(book);
                    }
                }
                break;
            case 2:
                String searchLine2 = getLine("search author");
                for (Book book : books) {
                    if (book.getAuthor().contains(searchLine2)) {
                        foundedBooks.add(book);
                    }
                }
                break;
            case 3:
                int searchedId = getInt("search id");
                for (Book book : books) {
                    if (book.getId() == searchedId) {
                        foundedBooks.add(book);
                    }
                }
                break;
            default:
                break;
        }
        if (foundedBooks.size() > 0) {
            System.out.println("Founded books: ");
            bookPrinter(foundedBooks, 5);
        } else {
            System.out.println("Books not found!");
        }
    }

    public static void addClient(List<Client> clients) {
        String name;
        String surname;
        int id;
        boolean OK;
        do {
            OK = true;
            id = getInt("id");
            name = getLine("name");
            surname = getLine("surname");
            for (Client client : clients) {
                if (client.getId() == id) {
                    OK = false;
                    System.err.println("Enter unique id!");
                }
            }
        } while (!OK);
        clients.add(new Client(id, name, surname));
        System.out.println("Client added successfully");
    }


    public static void clientPrinter(List<Client> clients, int sortBy) {
        List<Client> clientList = new ArrayList();
        switch (sortBy) {
            case 1:
                clientList = clientsSorter(clients, 1);
                break;
            case 2:
                clientList = clientsSorter(clients, 2);
                break;
            case 3:
                clientList = clientsSorter(clients, 3);
                break;
            default:
                clientList = clientsSorter(clients, 3);
                break;
        }

        System.out.println("Clients  (id | name | surname | is blocked? ): ");
        for (Client client : clientList) {
            System.out.printf(" %d | %s | %s | %b \n", client.getId(), client.getName(), client.getSurname(), client.isBlocked());
        }
        System.out.println("--------------------------------");
    }

    public static List<Client> clientsSorter(List<Client> clients, int i) {
        List<Client> sortedClients = new ArrayList();
        sortedClients.addAll(clients);
        List<Client> result = new ArrayList();
        switch (i) {
            case 1:
                while (sortedClients.size() > 0) {
                    Client curMin = sortedClients.get(0);
                    for (Client client : sortedClients) {
                        if (client.getName().compareTo(curMin.getName()) < 0) {
                            curMin = client;
                        }
                    }
                    result.add(curMin);
                    sortedClients.remove(curMin);
                }
                break;
            case 2:
                while (sortedClients.size() > 0) {
                    Client curMin = sortedClients.get(0);
                    for (Client client : sortedClients) {
                        if (client.getSurname().compareTo(curMin.getSurname()) < 0) {
                            curMin = client;
                        }
                    }
                    result.add(curMin);
                    sortedClients.remove(curMin);
                }
                break;
            case 3:
                while (sortedClients.size() > 0) {
                    Client curMin = sortedClients.get(0);
                    for (Client client : sortedClients) {
                        if (client.getId() < curMin.getId()) {
                            curMin = client;
                        }
                    }
                    result.add(curMin);
                    sortedClients.remove(curMin);
                }
                break;
        }
        return result;
    }


    public static void blockClient(List<Client> clients) {
        clientPrinter(clients, 5);
        int id = 0;
        boolean OK;
        do {
            OK = false;
            id = getInt("client id");
            for (Client client : clients) {
                if (client.getId() == id) {
                    OK = true;
                    System.out.println("Successfully blocked!");
                    client.setBlocked(true);
                    break;
                }
            }
            if (!OK) {
                System.err.println("Enter correct book id!");
            }
        } while (!OK);
    }


    public static void editClient(List<Client> clients) {
        clientPrinter(clients, 5);
        int id = 0;
        boolean OK;
        do {
            OK = false;
            id = getInt("client id");
            for (Client client : clients) {
                if (client.getId() == id) {
                    OK = true;
                    editClientItem(client);
                    break;
                }
            }
            if (!OK) {
                System.err.println("Enter correct client id!");
            }
        } while (!OK);
    }


    public static void editClientItem(Client client) {
        boolean OK = true;
        while (OK) {
            System.out.println("\n***************************");
            System.out.println("1.Edit name");
            System.out.println("2.Edit surname");
            System.out.println("3.Edit id");
            int a = getSelectedItem(2);
            switch (a) {
                case 1:
                    String name = getLine("name");
                    client.setName(name);
                    System.out.println("Success edited!");
                    OK = false;
                    break;
                case 2:
                    String surname = getLine("surname");
                    client.setSurname(surname);
                    System.out.println("Success edited!");
                    OK = false;
                    break;
                case 3:
                    int id = getInt("id");
                    client.setId(id);
                    System.out.println("Success edited!");
                    OK = false;
                    break;
                default:
                    break;
            }
        }
    }

 
    public static void getBooksByClient(Client client, List<Book> books) {

        List<Integer> rentedBooksIds = client.getRentedBooks();
        List<Book> rentedBooks = new ArrayList();
        for (int id : rentedBooksIds) {
            for (Book book : books) {
                if (book.getId() == id) {
                    rentedBooks.add(book);
                }
            }
        }
        bookPrinter(rentedBooks, 5);
    }


    public static void getHistoryByBook(int id, List<RentHistoryItem> history, List<Book> books, List<Client> clients) {
        List<RentHistoryItem> filtredHistory = new ArrayList();
        for (RentHistoryItem item : history) {
            if (item.getBookId() == id) {
                filtredHistory.add(item);
            }
        }
        historyPrinter(filtredHistory, books, clients);
    }


    public static void getHistoryByClient(int id, List<RentHistoryItem> history, List<Book> books, List<Client> clients) {
        List<RentHistoryItem> filtredHistory = new ArrayList();
        for (RentHistoryItem item : history) {
            if (item.getUserId() == id) {
                filtredHistory.add(item);
            }
        }
        historyPrinter(filtredHistory, books, clients);
    }


    public static void historyPrinter(List<RentHistoryItem> history, List<Book> books, List<Client> clients) {
        System.out.println("History  (completed | user | book | rentDate | returnDate ): ");
        for (RentHistoryItem item : history) {
            System.out.print(item.isCompleted() + " | ");
            int userId = item.getUserId();
            for (Client user : clients) {
                if (user.getId() == userId) {
                    System.out.print(user.getName() + " " + user.getSurname() + " | ");
                }
            }
            int bookId = item.getBookId();
            boolean founded = false;
            for (Book book : books) {
                if (book.getId() == bookId) {
                    founded = true;
                    System.out.print(book.getAuthor() + " - " + book.getName() + " | ");
                }
            }
            if (!founded) {
                System.out.print("Book was deleted from library | ");
            }
            System.out.print(item.getStartDate() + " | " + item.getEndDate() + "\n");
        }
        System.out.println("--------------------------------");
    }
    public static void rentBook(List<RentHistoryItem> history, List<Book> books, List<Client> clients) {
        List<Client> notBlockedClients = new ArrayList();
        for (Client client : clients) {
            if (client.isBlocked() == false) {
                notBlockedClients.add(client);
            }
        }
        if (notBlockedClients.size() < 1) {
            System.err.println("There are not avaible users!");
            return;
        }
        clientPrinter(notBlockedClients, 3);
        int id = 0;
        boolean OK;
        do {
            OK = false;
            id = getInt("client id");
            for (Client client : clients) {
                if (client.getId() == id) {
                    OK = true;
                    rentBookByClient(history, books, clients, client);
                    break;
                }
            }
            if (!OK) {
                System.err.println("Enter correct client id!");
            }
        } while (!OK);

    }

 
    public static void rentBookByClient(List<RentHistoryItem> history, List<Book> books, List<Client> clients, Client client) {
        List<Book> avaibleBooks = new ArrayList();
        List<Integer> clientIds = client.getRentedBooks();
        for (Book book : books) {
            if (book.isInLibrary()) {
                avaibleBooks.add(book);
            }
        }
        if (avaibleBooks.size() < 1) {
            System.err.println("There is no avaible books in library!");
            return;
        }

        bookPrinter(avaibleBooks, 3);
        int id = 0;
        boolean OK;
        do {
            OK = false;
            id = getInt("book id");
            for (Book book : avaibleBooks) {
                if (book.getId() == id) {
                    OK = true;
                    String date = getLine("rent date (DD.MM.YYYY)");
                    history.add(new RentHistoryItem(false, client.getId(), book.getId(), date, ""));
                    client.addBook(book.getId());
                    book.setIsInLibrary(false);
                    break;
                }
            }
            if (!OK) {
                System.err.println("Enter correct book id!");
            }
        } while (!OK);
    }


    public static void returnBook(List<RentHistoryItem> history, List<Book> books, List<Client> clients) {
        List<Client> clientWithBooks = new ArrayList();
        for (Client client : clients) {
            if (client.getRentedBooks().size() > 0) {
                clientWithBooks.add(client);
            }
        }
        if (clientWithBooks.size() < 1) {
            System.err.println("There are no clients with books!");
            return;
        }
        clientPrinter(clientWithBooks, 3);
        int id = 0;
        boolean OK;
        do {
            OK = false;
            id = getInt("client id");
            for (Client client : clients) {
                if (client.getId() == id) {
                    OK = true;
                    returnBookByClient(history, books, clients, client);
                    break;
                }
            }
            if (!OK) {
                System.err.println("Enter correct client id!");
            }
        } while (!OK);
    }


    public static void returnBookByClient(List<RentHistoryItem> history, List<Book> books, List<Client> clients, Client client) {
        List<Book> clientBooks = new ArrayList();
        List<Integer> clientIds = client.getRentedBooks();
        for (Book book : books) {
            for (int bookId : clientIds) {
                if (book.getId() == bookId) {
                    clientBooks.add(book);
                }
            }
        }
        if (clientBooks.size() < 1) {
            System.err.println("Client not have rented books!");
            return;
        }

        bookPrinter(clientBooks, 3);
        int id = 0;
        boolean OK;
        do {
            OK = false;
            id = getInt("book id");
            for (Book book : clientBooks) {
                if (book.getId() == id) {
                    OK = true;
                    String date = getLine("return date (DD.MM.YYYY)");
                    for (RentHistoryItem item : history) {
                        if (!item.isCompleted() && item.getBookId() == book.getId()) {
                            item.setCompleted(true);
                            item.setEndDate(date);
                        }
                    }
                    client.removeBook(book.getId());
                    book.setIsInLibrary(true);
                    System.out.println("Book was returned");
                    break;
                }
            }
            if (!OK) {
                System.err.println("Enter correct book id!");
            }
        } while (!OK);
    }


    public static void loadData(List<RentHistoryItem> history, List<Book> books, List<Client> clients) {
        loadBooks(books);
        loadClients(clients);
        loadHistory(history);
    }

    public static void loadBooks(List<Book> books) {
        List<Book> loadedBooks = new ArrayList();
        File handler = new File("books.txt");
        try {
            Scanner line = new Scanner(handler);
            while (line.hasNextLine()) {
                String currentLine = line.nextLine();
                if (currentLine.length() > 2) {
                    String[] elements = currentLine.split("###");
                    int id = Integer.parseInt(elements[0]);
                    String author = elements[1];
                    String name = elements[2];
                    boolean inLibrary = Boolean.valueOf(elements[3]);
                    loadedBooks.add(new Book(id, author, name, inLibrary));
                }
            }
            books.addAll(loadedBooks);
            System.out.println("books loaded!");
        } catch (FileNotFoundException ex) {
            System.err.println("file with books not found!");
            books.addAll(loadedBooks);
        }
    }

    public static void loadClients(List<Client> clients) {
        List<Client> loadedClients = new ArrayList();
        File handler = new File("clients.txt");
        try {
            Scanner line = new Scanner(handler);
            while (line.hasNextLine()) {
                String currentLine = line.nextLine();
                if (currentLine.length() > 2) {
                    String[] elements = currentLine.split("###");
                    int id = Integer.parseInt(elements[0]);
                    String name = elements[1];
                    String surname = elements[2];
                    Client currentClient = new Client(id, name, surname);
                    if (elements.length == 4) {
                        String rentedBooks = elements[3];
                        if (rentedBooks.length() > 0) {
                            String[] booksId = rentedBooks.split("_");
                            for (String currentId : booksId) {
                                int intId = Integer.parseInt(currentId);
                                currentClient.addBook(intId);
                            }
                        }
                    }
                    loadedClients.add(currentClient);
                }
            }
            clients.addAll(loadedClients);
            System.out.println("clients loaded!");
        } catch (FileNotFoundException ex) {
            System.err.println("file with clients not found!");
            clients.addAll(loadedClients);
        }
    }

    public static void loadHistory(List<RentHistoryItem> history) {
        List<RentHistoryItem> loadedHistory = new ArrayList();
        File handler = new File("history.txt");
        try {
            Scanner line = new Scanner(handler);
            while (line.hasNextLine()) {
                String currentLine = line.nextLine();
                if (currentLine.length() > 2) {
                    String[] elements = currentLine.split("###");
                    boolean completed = Boolean.valueOf(elements[0]);
                    int clientId = Integer.parseInt(elements[1]);
                    int bookId = Integer.parseInt(elements[2]);
                    String startDate = elements[3];
                    String endDate;
                    if (elements.length == 5) {
                        endDate = elements[4];
                    } else {
                        endDate = " ";
                    }
                    loadedHistory.add(new RentHistoryItem(completed, clientId, bookId, startDate, endDate));
                }
            }
            history.addAll(loadedHistory);
            System.out.println("history loaded!");
        } catch (FileNotFoundException ex) {
            System.err.println("file with history not found!");
            history.addAll(loadedHistory);
        }
    }

    public static void saveData(List<RentHistoryItem> history, List<Book> books, List<Client> clients) {
        saveBooks(books);
        saveClients(clients);
        saveHistory(history);
    }

    public static void saveBooks(List<Book> books) {
        PrintWriter bookSaveFile;
        try {
            bookSaveFile = new PrintWriter("books.txt");
            for (Book book : books) {
                String dataLine = book.getId().toString() + "###" + book.getAuthor() + "###" + book.getName() + "###" + book.isInLibrary() + "\r\n";
                bookSaveFile.write(dataLine);
            }
            bookSaveFile.close();
            System.out.println("Books saved!");
        } catch (FileNotFoundException ex) {
            System.err.println("Can't save book file!");
        }
    }

    public static void saveClients(List<Client> clients) {
        PrintWriter clientSaveFile;
        try {
            clientSaveFile = new PrintWriter("clients.txt");
            for (Client client : clients) {
                String dataLine = client.getId().toString() + "###" + client.getName() + "###" + client.getSurname() + "###";
                List<Integer> booksIds = client.getRentedBooks();
                for (int i = 0; i < booksIds.size(); i++) {
                    if (i == booksIds.size() - 1) {
                        dataLine += booksIds.get(i).toString();
                    } else {
                        dataLine += booksIds.get(i).toString() + "_";
                    }
                }
                dataLine += "\r\n";
                clientSaveFile.write(dataLine);
            }
            clientSaveFile.close();
            System.out.println("Clients saved!");
        } catch (FileNotFoundException ex) {
            System.err.println("Can't save client file!");
        }
    }

    public static void saveHistory(List<RentHistoryItem> history) {
        PrintWriter historySaveFile;
        try {
            historySaveFile = new PrintWriter("history.txt");
            for (RentHistoryItem item : history) {
                if(item.getEndDate()=="") item.setEndDate(" ");
                String dataLine = item.isCompleted() + "###" + item.getUserId() + "###" + item.getBookId() + "###" + item.getStartDate() + "###" + item.getEndDate() + "\r\n";
                historySaveFile.write(dataLine);
            }
            historySaveFile.close();
            System.out.println("History saved!");
        } catch (FileNotFoundException ex) {
            System.err.println("Can't save history file!");
        }
    }
}
