package Controller;

import Model.ListOfArticles;
import Model.NewsContext;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsClient
{
    private final NewsAPI api;
    private final NewsContext _context;

    public NewsClient(NewsContext context)
    {
        _context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(_context.getProperties().getProperty("NewsUrl"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(NewsAPI.class);
    }

    public Observable<ListOfArticles> getTopHeadlines()
    {
        return api.getTopHeadlines(_context.getProperties().getProperty("NewsApiKey"), "us");
    }
}
