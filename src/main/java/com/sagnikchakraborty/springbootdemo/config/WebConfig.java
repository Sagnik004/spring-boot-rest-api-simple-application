package com.sagnikchakraborty.springbootdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Configuring response type so that client can negotiate their preferred response type...
        // If mediaType=xml is passed as parameter then response type will be xml
        // If mediaType=json is passed as parameter then response type will be json
        // If mediaType is not passed as parameter then response type will be defaulted to json
        // To support XML data format ensure to have jackson-dataformat-xml package included in project
        configurer.favorParameter(true)
                .parameterName("mediaType")
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }
}
