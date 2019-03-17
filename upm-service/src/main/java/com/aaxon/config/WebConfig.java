package com.aaxon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author elviswu
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final String[] STATIC_RESOURCES = { "/**/*.css", "/**/*.js",
			"/**/*.jpg", "/**/*.png", "/**/*.svg", "/**/*.eot", "/**/*.ttf",
			"/**/*.woff" };

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.setOrder(-1).addResourceHandler(STATIC_RESOURCES)
				.addResourceLocations("classpath:/static/");
	}
}
