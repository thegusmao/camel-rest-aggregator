package br.com.redhat.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;
import br.com.redhat.aggregators.JsonRestCallsAggregator;
import br.com.redhat.processors.GetAuthorIdProcessor;

@Component
public class RestAggregatorRoute extends RouteBuilder {

	private static final int OK_CODE = 200;
	private static final int APP_RESPONSE_CODE = 204;

	@Override
	public void configure() throws Exception {
		from("direct:call-rest-author")
			.routeId("call-rest-services")
			.to("direct:author-service")
			.choice()
				.when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(OK_CODE))
					.process(new GetAuthorIdProcessor())
					.enrich("direct:books-service", new JsonRestCallsAggregator())
			.otherwise()
				.setHeader(Exchange.HTTP_RESPONSE_CODE).constant(APP_RESPONSE_CODE);

		from("direct:call-rest-all")
			.routeId("all-service")
			.removeHeaders("CamelHttp*")
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
		.to("http://{{authors.url}}/authors");


		from("direct:author-service")
			.routeId("author-service")
			.removeHeaders("CamelHttp*")
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
		.toD("http://{{authors.url}}/authors/${header.name}");

		from("direct:books-service")
			.routeId("books-service")
			.onException(HttpOperationFailedException.class)
				.handled(true)
				.setBody(constant("[]"))
			.end()
			.removeHeaders("CamelHttp*")
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
		.toD("http://{{books.url}}/books/${header.id}");
	}
}