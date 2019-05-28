package Model;

import io.reactivex.disposables.Disposable;

import java.util.Properties;

public class NewsContext
{
    private Properties properties;

    public NewsContext(Properties properties)
    {
        this.properties = properties;
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
