package Model;

public class Article
{
    private Source source = new Source();
    private String author;
    private String title;
    private String description;
    private String urlToImage;
    private String url;
    private String publishedAt;
    private String content;

    public Source getSource() {
        return source;
    }

    public String getUrl() { return url; }
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

}