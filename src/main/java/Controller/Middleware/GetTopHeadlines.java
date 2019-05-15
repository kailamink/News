package Controller.Middleware;

import Controller.NewsClient;
import Model.NewsContext;
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
        Disposable disposable = newsClient.getTopHeadlines()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(listOfArticles -> {
                    context.setListOfArticles(listOfArticles);
                    context.getNewsFrame().setSideBar();
                });
        context.setDisposable(disposable);
        invokeProcess(context);
    }
}
