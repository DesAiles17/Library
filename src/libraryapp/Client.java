package libraryapp;

import java.util.ArrayList;
import java.util.List;


public class Client {
    private String name;
    private String surname;
    private int id;
    private boolean isBlocked = false;
    public List<Integer> rentedBooks;
    
    public void addBook(int bookId){
        rentedBooks.add(bookId);
    }
    
    public void removeBook(int bookId){
        for(int i=0;i<rentedBooks.size();i++){
            if(rentedBooks.get(i)==bookId){
                rentedBooks.remove(i);
                break;
            }
        }
    }

    public List<Integer> getRentedBooks() {
        return rentedBooks;
    }

    
    public Client(int id, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        rentedBooks = new ArrayList();
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
    
}
