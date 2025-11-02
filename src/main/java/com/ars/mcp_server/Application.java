package com.ars.mcp_server;

import com.ars.mcp_server.service.ProductService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setLogStartupInfo (false);
        app.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = app.run(args);

        Runtime.getRuntime().addShutdownHook (new Thread(context::close));

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    @Bean
    public ToolCallbackProvider toolCallbackProvider(ProductService productService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(productService)
                .build();
    }

}
