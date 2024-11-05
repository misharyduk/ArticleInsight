package com.mike.articleinsight.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator articleInsightRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/articleinsight/articles/**")
                        .filters(f -> f.rewritePath("/articleinsight/articles/(?<segment>.*)",
                                                    "/${segment}"))
                        .uri("lb://ARTICLES-MS"))
                .route(p -> p
                        .path("/articleinsight/review/**")
                        .filters(f -> f.rewritePath("/articleinsight/review/(?<segment>.*)",
                                                    "/${segment}"))
                        .uri("lb://REVIEW-MS"))
                .build();
    }

}
