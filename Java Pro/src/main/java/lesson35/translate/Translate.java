package lesson35.translate;

// generated with https://www.jsonschema2pojo.org/

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translate {

    @SerializedName("translations")
    @Expose
    private List<Translation> translations;
    @SerializedName("word_count")
    @Expose
    private Integer wordCount;
    @SerializedName("character_count")
    @Expose
    private Integer characterCount;

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getCharacterCount() {
        return characterCount;
    }

    public void setCharacterCount(Integer characterCount) {
        this.characterCount = characterCount;
    }

}
