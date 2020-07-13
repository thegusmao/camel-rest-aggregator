package br.com.redhat.aggregators;

import java.util.Objects;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.util.json.JsonObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class JsonRestCallsAggregator implements AggregationStrategy {
    
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String books = newExchange.getIn().getBody(String.class);
        String author = oldExchange.getIn().getBody(String.class);
        
        JsonParser parser = JsonParserFactory.getJsonParser();
        JsonObject json = new JsonObject();
        json.put("author", parser.parseMap(author));
        json.put("books", parser.parseList(books));
        
        newExchange.getIn().setBody(json.toJson());
        return newExchange;
    }
}