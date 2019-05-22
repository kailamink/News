package View;

import Controller.NewsClient;
import Model.Article;
import Model.NewsContext;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewsFrame extends JFrame
{
    private NewsContext _context;
    private JList _headlines;
    private JScrollPane _scrollPane;
    private JScrollPane _articleScrollPane;
    private Disposable _disposable;
    private ArticlePanel _articlePanel;

    public NewsFrame(NewsContext context)
    {
        _context = context;
        setSize(800, 600);
        setTitle("News");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        configureHeadlinePane();
        root.add(_scrollPane, BorderLayout.WEST);
        configureNewsPane();
        root.add(_articleScrollPane, BorderLayout.CENTER);
        setContentPane(root);

        NewsClient newsClient = new NewsClient(_context);
        _disposable = newsClient.getTopHeadlines()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(listOfArticles -> {
                    _context.setListOfArticles(listOfArticles);
                    setSideBar();
                });
        _context.setDisposable(_disposable);
        onWindowClose();
    }

    private void configureHeadlinePane()
    {
        _headlines = new JList();
        _headlines.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
        _scrollPane = new JScrollPane(_headlines);
        _scrollPane.setVerticalScrollBar(_scrollPane.getVerticalScrollBar());
    }

    private void configureNewsPane()
    {
        _articlePanel = new ArticlePanel();
        _articleScrollPane = new JScrollPane(_articlePanel);
        _articleScrollPane.setVerticalScrollBar(_scrollPane.getVerticalScrollBar());
    }

    private void setSideBar()
    {
        DefaultListModel listModel = new DefaultListModel();
        for (Article article : _context.getListOfArticles().articles) {
            String headline = article.getTitle();
            listModel.addElement(headline.length() > 39
                                ? headline.substring(0, 39) + "..."
                                : headline);
        }
        _headlines.setModel(listModel);
        setSideBarListener();
    }

    private void setSideBarListener()
    {
        _headlines.addListSelectionListener(listSelectionListener -> {
            int headlineIndex = _headlines.getSelectedIndex();
            updateNewsStory(headlineIndex);
        });
    }

    private void updateNewsStory(int headlineIndex)
    {
        _articlePanel.updatePanel(_context.getListOfArticles().articles.get(headlineIndex));
        System.out.println(_context.getListOfArticles().articles.get(headlineIndex).getTitle());
    }

    private void onWindowClose()
    {
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                _context.getDisposable().dispose();
            }
        });
    }

}
