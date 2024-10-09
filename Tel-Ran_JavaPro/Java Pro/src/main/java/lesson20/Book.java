package lesson20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book {
    private String title;
    private List<String> authors = new ArrayList<>();

    public Book(String title, String ... authors) {
        this.title = title;
        this.authors.addAll(Arrays.asList(authors));
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }
}
