package bv.dev.dynamicsidemenu.models.menu;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuModel {

    @SerializedName("menu")
    @Expose
    private List<Menu> menuList = new ArrayList<>();

    public List<Menu> getMenuList() {
        return menuList;
    }

    @SuppressWarnings("unused")
    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

}