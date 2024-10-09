package lesson4.cloning;

public class CloneTester {
    public static void main(String[] args) throws CloneNotSupportedException {
        Book b1 = new Book("Pinoccio", 1955, new Author("Rodari"));
        // Book b2 = b1; // не подходит для клонирования -
        // просто создание еще одной ссылки на объект
        Book b2 = b1.clone();
        b2.setName("War and Peace");
        b2.setYear(1844);
        Author author = b2.getAuthor();
        author.setName("Leo Tolstoy");
        b2.setAuthor(author);
        System.out.println(b1);
        System.out.println(b2);
    }
}
