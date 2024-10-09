package lesson4.cloning;

public class Book implements Cloneable {
    private String name;
    private int year;
    private Author author;

    public Book(String name, int year, Author author) {
        this.name = name;
        this.year = year;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", author=" + author +
                '}';
    }

    @Override
    protected Book clone() throws CloneNotSupportedException {
        Book newBook = (Book) super.clone(); // вызываем метод Object
        Author a = author.clone();
        newBook.author = a;
        return newBook;
    }
}
