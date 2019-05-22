package Controller.Middleware;

import Controller.NewsClient;
import Model.ListOfArticles;
import Model.NewsContext;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetTopHeadlines implements IMiddleware<NewsContext> {
    private IMiddleware nextMiddleware;

    @Override
    public void setNextInChain(IMiddleware iMiddleware)
    {
        this.nextMiddleware = iMiddleware;
    }

    @Override
    public void invokeProcess(NewsContext context) {
        if(this.nextMiddleware != null)
        {
            this.nextMiddleware.process(context);
        }
    }

    @Override
    public void process(NewsContext context)
    {
        NewsClient newsClient = new NewsClient(context);
        Observable<ListOfArticles> topHeadlines = newsClient.getTopHeadlines();
        invokeProcess(context);
    }
}
