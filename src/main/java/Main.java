import Controller.NewsController;
import Model.NewsContext;
import View.NewsFrame;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        NewsController newsController = new NewsController(new NewsContext(properties));
        NewsFrame newsFrame = new NewsFrame(newsController);
        newsFrame.setVisible(true);
    }
}
