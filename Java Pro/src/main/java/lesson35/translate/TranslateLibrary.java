package lesson35.translate;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Arrays;

public class TranslateLibrary {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.eu-de.language-translator.watson.cloud.ibm.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static TranslateService service = retrofit.create(TranslateService.class);

    // text - что переводить
    // direction - с какого на какой языки
    public static String translate(String text, String direction) {
        // {"text": ["Посылаем посылку на луну!"], "model_id":"ru-en"}
        TranslateRequest request = new TranslateRequest();
        request.setModelId(direction);
        request.setText(Arrays.asList(text));
        try {
            Response<Translate> result = service.translate(request).execute();
        /*
            {
              "translations" : [ {
                "translation" : "Trois nuits sans sommeil !"
              } ],
              "word_count" : 4,
              "character_count" : 23
            }
         */
            return result.body().getTranslations().get(0).getTranslation();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
