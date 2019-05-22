package Model;

import Controller.NewsController;
import View.NewsFrame;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Properties;

public class NewsContext
{
    private Properties properties;
    private ListOfArticles listOfArticles;
    private Disposable disposable;

    public NewsContext(Properties properties)
    {
        this.properties = properties;
    }

    public Disposable getDisposable() {
        return disposable;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }


    public ListOfArticles getListOfArticles() {
        return listOfArticles;
    }

    public void setListOfArticles(ListOfArticles listOfArticles) {
        this.listOfArticles = listOfArticles;
    }

    public void setProperties(Properties properties)
    {
        this.properties = properties;
    }

    public Properties getProperties()
    {
        return properties;
    }
}
