
package libraryapp;


public class Book {
    private String author;
    private String name;
    private int id;
    private boolean isInLibrary;

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public boolean isInLibrary() {
        return isInLibrary;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsInLibrary(boolean isInLibrary) {
        this.isInLibrary = isInLibrary;
    }
    
    
    
    public Book(int id, String author, String name, boolean isInLibrary){
        this.id = id;
        this.author = author;
        this.name = name;
        this.isInLibrary = isInLibrary;
    }
}
