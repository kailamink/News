package View;

import Model.Article;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ArticlePanel extends JPanel
{
    private JLabel _title;
    private JLabel _authorName;
    private JLabel _imageLabel;
    private JTextArea _content;
    private JButton _url;

    public ArticlePanel()
    {
        _title = new JLabel();
        _authorName = new JLabel();
        _imageLabel = new JLabel();
        _content = new JTextArea();
        _url = new JButton();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS ));
        add(_title);
        add(_authorName);
        add(_imageLabel);
        add(_content);
        add(_url);
    }

    public void updatePanel(Article article) {
        _title.setText(article.getTitle());
        _authorName.setText(article.getAuthor());
        setImageLabel(article.getUrlToImage());
        _content.setText(article.getContent());
        _url.setText(article.getUrl());
        _url.addActionListener(actionEvent -> {
            try {
                openWebpage(new URL(_url.getText()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    public void setImageLabel(String url) {
        try {
            Image image = new ImageIcon(new URL(url))
                    .getImage()
                    .getScaledInstance(200,100, Image.SCALE_SMOOTH);
            _imageLabel.setIcon(new ImageIcon(image));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
