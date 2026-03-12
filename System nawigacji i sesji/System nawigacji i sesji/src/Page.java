import java.io.Serializable;
import java.time.LocalDateTime;

public class Page implements Serializable {

    private String title;
    private String url;
    private LocalDateTime timestamp;

    public Page(String title, String url) {
        this.title = title;
        this.url = url;
        this.timestamp = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}