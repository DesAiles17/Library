package libraryapp;


public class RentHistoryItem {
    private boolean completed;
    private int userId;
    private int bookId;
    private String startDate;
    private String endDate;

    public RentHistoryItem(boolean completed, int userId, int bookId, String startDate, String endDate) {
        this.completed = completed;
        this.userId = userId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
}
