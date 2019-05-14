package Controller;

import Model.ListOfArticles;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI
{
    @GET("/v2/top-headlines")
    Observable<ListOfArticles> getTopHeadlines(@Query("apiKey") String apiKey);

}
