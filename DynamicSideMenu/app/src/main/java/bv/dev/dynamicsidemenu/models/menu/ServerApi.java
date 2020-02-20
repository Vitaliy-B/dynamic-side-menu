package bv.dev.dynamicsidemenu.models.menu;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerApi {
    String baseUrl = "https://www.dropbox.com/s/fk3d5kg6cptkpr6/";

    @GET("menu.json?dl=1")
    Call<MenuModel> getMenus();
}
