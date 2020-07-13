package br.com.redhat.processors;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class GetAuthorIdProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String author = exchange.getIn().getBody(String.class);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = parser.parseMap(author);
        String authorId = (String) jsonMap.get("id");
    
        exchange.getIn().setHeader("id", authorId);
        exchange.getIn().setBody(author);
	}
    
}