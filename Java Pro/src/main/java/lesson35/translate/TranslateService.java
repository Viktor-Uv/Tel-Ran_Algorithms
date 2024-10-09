package lesson35.translate;

// https://square.github.io/retrofit/

/*
curl
     -X POST
     --header "Authorization: Basic YXBpa2V5OmZMTENkZ0hUN2ZDTmFQaEtLSkcwUVRtOHBsNmMtaWRpX2lVMkVvc2U1UUdp"
     --header "Content-Type: application/json"
     --data '{"text": ["Посылаем посылку на луну!"], "model_id":"ru-en"}'
     "https://api.eu-de.language-translator.watson.cloud.ibm.com/instances/047da6bd-1e84-431e-a292-f5d0d378742d/v3/translate?version=2018-05-01"

 */

// https://api.eu-de.language-translator.watson.cloud.ibm.com

import reactor.core.publisher.Mono;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TranslateService {
    @Headers(
            {
                    "Authorization: Basic YXBpa2V5OmZMTENkZ0hUN2ZDTmFQaEtLSkcwUVRtOHBsNmMtaWRpX2lVMkVvc2U1UUdp",
                    "Content-Type: application/json"
            }
    )
    @POST("/instances/047da6bd-1e84-431e-a292-f5d0d378742d/v3/translate?version=2018-05-01")
    Call<Translate> translate(
            @Body TranslateRequest request
    );

    @Headers(
            {
                    "Authorization: Basic YXBpa2V5OmZMTENkZ0hUN2ZDTmFQaEtLSkcwUVRtOHBsNmMtaWRpX2lVMkVvc2U1UUdp",
                    "Content-Type: application/json"
            }
    )
    @POST("/instances/047da6bd-1e84-431e-a292-f5d0d378742d/v3/translate?version=2018-05-01")
    Mono<Translate> reactiveTranslate(
            @Body TranslateRequest request
    );
}
