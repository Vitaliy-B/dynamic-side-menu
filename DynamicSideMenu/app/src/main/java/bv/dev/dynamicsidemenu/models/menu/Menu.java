package bv.dev.dynamicsidemenu.models.menu;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Menu {
    public static final String FUNC_TEXT = "text";
    public static final String FUNC_IMAGE = "image";
    public static final String FUNC_URL = "url";

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("function")
    @Expose
    private String function;
    @SerializedName("param")
    @Expose
    private String param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override @NonNull
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", function='" + function + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}