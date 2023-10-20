package lesson35.translate;

// generated with https://www.jsonschema2pojo.org/

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslateRequest {

    @SerializedName("text")
    @Expose
    private List<String> text;
    @SerializedName("model_id")
    @Expose
    private String modelId;

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

}
