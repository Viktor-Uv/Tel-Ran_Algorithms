package lesson34;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class E_CompletableFutureCompose {
    public static void main(String[] args) {
        // thenCompose
        // Если есть библиотечные функции, возвращающие CompletableFuture,
        // но чтобы вызвать их последовательно удобно использовать thenCompose

        // r = getUserInfo(122)
        //          getUserRating(r)

        CompletableFuture<CompletableFuture<UserRating>> rating =
                getUserInfo(122)
                        // так как getUserRating возвращает CompletableFuture<UserRating>
                        // то thenApplyAsync возвратит CompletableFuture<CompletableFuture<UserRating>>
                        .thenApplyAsync(
                                info -> getUserRating(info)
                        );

        // вопрос - как получить CompletableFuture<UserRating>?
        CompletableFuture<UserRating> realRating =
                getUserInfo(122)
                        .thenCompose(
                                info -> getUserRating(info)
                        );

        // запустите и rating и realRating и распечатайте результат
        // добейтесь путем использования get() чтобы и в 1 и во 2 случае
        // распечаталось UserRating
        try {
            System.out.println(rating.get().get());
            System.out.println(realRating.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


    }

    static CompletableFuture<UserInfo> getUserInfo(int id) {
        return CompletableFuture.supplyAsync(
                () -> new UserInfo(id)
        );
    }

    static CompletableFuture<UserRating> getUserRating(UserInfo info) {
        return CompletableFuture.supplyAsync(
                () -> new UserRating(info)
        );
    }

}

class UserInfo {
    private int userId;

    public UserInfo(int userId) {
        this.userId = userId;
    }
}

class UserRating {
    private UserInfo info;

    public UserRating(UserInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UserRating";
    }
}
