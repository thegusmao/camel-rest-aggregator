package br.com.redhat.beans;

import java.util.Map;

import org.apache.camel.Exchange;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

@Component
public class Authors {
    
    public void getId(Exchange exchange) {
        String author = exchange.getIn().getBody(String.class);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = parser.parseMap(author);
        String authorId = (String) jsonMap.get("id");
    
        exchange.getIn().setHeader("id", authorId);
        exchange.getIn().setBody(author);
    }
}