package View;

import Controller.NewsController;
import Model.Article;
import Model.NewsContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewsFrame extends JFrame
{
    private NewsContext _context;
    private JList _headlines;
    private JScrollPane _scrollPane;
    private boolean isSidebarConfigured = false;

    public NewsFrame(NewsController controller)
    {
        setSize(800, 600);
        setTitle("News");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        _headlines = new JList();
        _scrollPane = new JScrollPane(_headlines);
        root.add(_scrollPane, BorderLayout.WEST);
        setContentPane(root);
//        _context.setNewsFrame(this);
        _context  = controller.getTopHeadlines();

        if(_context.getListOfArticles() != null)
        {
            setSideBar();
        }
        onWindowClose();
    }

    public void setSideBar()
    {
        DefaultListModel listModel = new DefaultListModel();
        for (Article article : _context.getListOfArticles().articles) {
            listModel.addElement(article.getTitle());
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
