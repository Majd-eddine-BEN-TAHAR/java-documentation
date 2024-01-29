/*
 * Inner classes are often used to logically group and encapsulate related functionalities that are meant to be used by the outer class only.
 * 
 * An inner class can access all the members (variables and methods) of its outer class, including private members.
 * An instance of a Nested Inner Class is related only to an instance of its outer class. This means you cannot create an object of the inner class without first creating an object of the outer class.
 *
 * Benefits: it helps encapsulate the classes better.
 * Considerations: Can make the code more complex
*/

// The inner class is Book here, go down to see it

class Library {
    private String libraryName;
    private int maxBooks;
    private int bookCount;
    private Book[] books;

    // Constructor
    public Library(String libraryName, int maxBooks) {
        this.libraryName = libraryName;
        this.maxBooks = maxBooks;
        this.bookCount = 0;
        this.books = new Book[maxBooks];
    }

    // Inner Class: Book
    class Book {
        private String title;
        private String author;

        // Constructor for Book
        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        // Display book information
        public void displayBookInfo() {
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
        }
    }

    // Add a book to the library
    public void addBook(String title, String author) {
        if (bookCount < maxBooks) {
            Book newBook = new Book(title, author);
            books[bookCount] = newBook;
            bookCount++;
            System.out.println(title + " by " + author + " has been added to " + libraryName + ".");
        } else {
            System.out.println("Cannot add more books. The library is full.");
        }
    }

    // List all books in the library
    public void listBooks() {
        System.out.println("Books in " + libraryName + ":");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". " + books[i].title + " by " + books[i].author);
        }
    }

    public static void main(String[] args) {
        Library myLibrary = new Library("My Library", 5);

        // Adding books to the library
        myLibrary.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        myLibrary.addBook("To Kill a Mockingbird", "Harper Lee");
        myLibrary.addBook("1984", "George Orwell");

        // Displaying the list of books in the library
        myLibrary.listBooks();
    }
}