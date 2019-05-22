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

//        NewsController newsController = new NewsController());
        NewsFrame newsFrame = new NewsFrame(new NewsContext(properties));
        newsFrame.setVisible(true);
    }
}
