package lesson35.reactor; // Date 18.10.2023

// https://github.com/reactor/reactor-core

import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import lesson35.translate.TranslateRequest;
import lesson35.translate.TranslateService;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Arrays;
import java.util.function.Consumer;

public class ReactorTest {
    public static void main(String[] args) {
        // Flux - источник потенциально бесконечного количества событий: 0 .... бесконечность
        // Mono - источник максимум с 1 событием: 0 ... 1

        // поток событий может быть:
        //      потенциально бесконечным (Flux)
        //      может завершиться в любой момент
        //      может завершиться с исключением
        //      может вообще не выдать ни одного события

        Flux<String> countries = Flux.just("Monaco", "France", "Italy");

        countries
                .subscribe(
                        // распечатайте страны
                        s -> System.out.println(s)
                );

        countries
                // переведите страны в верхний регистр
                .map(s -> s.toUpperCase())
                .subscribe(s -> System.out.println(s));

        // Subscriber

        Flux.just(1,2,3,4,5)
                .subscribe(
                        new Subscriber<Integer>() {
                            @Override
                            public void onSubscribe(Subscription subscription) {
                                // вызывается при подписке
                                // передается объект "подписка"
                                // можно попросить подписку передавать значения по
                                // нескольку штук - BackPressure (slow down)
                                subscription.request(200);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                // при получении каждого нового события
                                System.out.println("onNext: " + integer);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                // если в потоке происходит исключение
                            }

                            @Override
                            public void onComplete() {
                                // вызывается когда поток завершается
                                System.out.println("Closed");
                            }
                        }
                );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.eu-de.language-translator.watson.cloud.ibm.com")
                .addConverterFactory(GsonConverterFactory.create())
                // мы подключили библиотеку с адаптером Reactor
                // для Retrofit
                // это позволит возвращать Mono<>
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .build();

        TranslateService service = retrofit.create(TranslateService.class);

        Flux.just("один", "два", "три топора")
                .map(s -> {
                    TranslateRequest r = new TranslateRequest();
                    r.setText(Arrays.asList(s));
                    r.setModelId("ru-en");
                    return r;
                })
                .flatMap(r -> service.reactiveTranslate(r)
                        .subscribeOn(Schedulers.parallel()) // встроенный пул потоков
                )
                .map(
                        t -> t.getTranslations().get(0).getTranslation()
                )
                .subscribe(
                        s -> System.out.println(s)
                );
        try {Thread.sleep(5_000);}
        catch (Exception e){}
        System.exit(0); // удачно ли завершилась программа
    }
}
