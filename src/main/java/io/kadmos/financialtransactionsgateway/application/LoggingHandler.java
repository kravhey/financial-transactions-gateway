package io.kadmos.financialtransactionsgateway.application;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class LoggingHandler implements RewriteFunction<String, String> {
    final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

    @Override
    public Publisher<String> apply(final ServerWebExchange e, final String body) {
        final ServerHttpRequest request = e.getRequest();
        StringBuilder s = new StringBuilder();
        final HttpMethod method = request.getMethod();
        s.append("path=").append(request.getPath()).append('|');
        s.append("http verb=").append(method).append('|');
        final HttpHeaders headers = request.getHeaders();
        s.append("http headers=").append(headers).append('|');
        s.append("body=").append(body);
        logger.info("{}", s);
        return Mono.justOrEmpty(body);
    }
}
