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
    private JTextArea _title;
    private JTextArea _authorName;
    private JLabel _imageLabel;
    private JTextArea _content;
    private JButton _url;

    private int _openWebPageTries;

    public ArticlePanel()
    {
        _title = new JTextArea();
        _authorName = new JTextArea();
        _imageLabel = new JLabel();
        _content = new JTextArea();
        _url = new JButton();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(_title);
        add(_authorName);
        add(_imageLabel);
        add(_content);
        add(_url);
    }

    public void updatePanel(Article article) {
        _title.setText(createLineBreaksInString(article.getTitle()));
        _authorName.setText(article.getAuthor());
        setImageLabel(article.getUrlToImage());
        _content.setText(createLineBreaksInString(article.getContent()));
        _url.setText("Click here to browse the full article");
        _openWebPageTries = 0;
        _url.addActionListener(actionEvent -> {
            try {
                if(_openWebPageTries == 0) {
                    openWebpage(new URL(article.getUrl()));
                }
                ++_openWebPageTries;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    public String createLineBreaksInString(String str)
    {
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if(str != null) {
            String[] strArray = str.split(" ");
            for (String s : strArray) {
                stringBuilder.append(s + " ");
                if (index % 15 == 0 && index != 0) {
                    stringBuilder.append(System.lineSeparator());
                }
                ++index;
            }
        }
        return stringBuilder.toString();
    }

    public void setImageLabel(String url) {
        try {
            Image image = new ImageIcon(new URL(url))
                    .getImage()
                    .getScaledInstance(300,200, Image.SCALE_SMOOTH);
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
