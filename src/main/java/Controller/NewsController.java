package Controller;

import Controller.Middleware.GetTopHeadlines;
import Model.ListOfArticles;
import Model.NewsContext;

import java.util.List;

public class NewsController
{
    private NewsContext _context;

    public NewsController(NewsContext context)
    {
        _context = context;
    }
    //TODO refactor
    public NewsContext getTopHeadlines()
    {
        GetTopHeadlines getTopHeadlines = new GetTopHeadlines();
        getTopHeadlines.process(_context);
        return _context;
    }
}
