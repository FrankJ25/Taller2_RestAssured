package factoryRequest;

import java.util.HashMap;
import java.util.Map;

public class RequestInformation {
    private String url;
    private String body;
    private Map<String,String> headers = new HashMap<>();

    public RequestInformation(){}

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RequestInformation setUrl(String url) {
        this.url = url;
        return this;
    }

    public RequestInformation setBody(String body) {
        this.body = body;
        return this;
    }

    public RequestInformation setHeaders(String key,String value) {
        this.headers.put(key,value);
        return this;
    }
}
