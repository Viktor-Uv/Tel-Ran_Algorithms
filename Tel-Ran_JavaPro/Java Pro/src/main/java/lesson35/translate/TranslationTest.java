package lesson35.translate;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class TranslationTest {
    public static void main(String[] args) throws IOException {
//        System.out.println(
//                TranslateLibrary.translate("привет мир", "ru-en")
//        ); // Result: hello world
//        System.exit(0);

        // напишите вызов двух переводов
        // один с русского на английский
        // и результат переведите с английского на французский
        CompletableFuture<String> request = CompletableFuture.supplyAsync(
                        () -> TranslateLibrary.translate("привет, как дела", "ru-en")
                )
                .thenApply(
                        s -> TranslateLibrary.translate(s, "en-fr")
                );

        try {
            System.out.println(request.get()); // Result: Salut, comment tu es?
        } catch (Exception ignored) {}

        System.exit(0);
    }
}
