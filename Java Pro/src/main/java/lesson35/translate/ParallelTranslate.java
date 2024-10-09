package lesson35.translate;

import java.util.concurrent.CompletableFuture;

public class ParallelTranslate {
    public static void main(String[] args) {
        // параллельно выполните перевод любой фразы
        // с русского на английский и
        // с английского на французский
        // и распечатайте результаты через тире
        // английский результат - франзцузский результат

        CompletableFuture<String> translation1 = CompletableFuture.supplyAsync(
                () -> TranslateLibrary.translate("привет мир", "ru-en")
        );
        CompletableFuture<String> translation2 = CompletableFuture.supplyAsync(
                () -> TranslateLibrary.translate("hello", "en-fr")
        );

        CompletableFuture<Void> combined = CompletableFuture.allOf(translation1, translation2);

        try {
            combined.get();
            System.out.println(translation1.get() + " - " + translation2.get());
        } catch (Exception ignored) {}

        System.exit(0);

    }
}
