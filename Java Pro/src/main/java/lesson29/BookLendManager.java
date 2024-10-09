package lesson29;

public class BookLendManager {
    private BookService service;

    public BookLendManager(BookService service) {
        this.service = service;
    }

    // будем тестировать эту "бизнес логику" выдачи книги абоненту
    public void checkout(int bookId, int memberId) {
        if (service.inStock(bookId)) {
            service.lend(bookId, memberId);
        } else {
            throw new IllegalStateException("Book is not available");
        }
    }

    public interface BookService { // Must be set to "public" to make it visible in tests
        // доступна ли книга для выдачи
        boolean inStock(int bookId);

        // выдаем книгу пользователю
        void lend(int bookId, int memberId);
    }
}
