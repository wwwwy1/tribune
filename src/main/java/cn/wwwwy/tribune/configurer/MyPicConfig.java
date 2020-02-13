package cn.wwwwy.tribune.configurer;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/user/**").addResourceLocations("file:F:\\eimg\\user\\");
		registry.addResourceHandler("/images/ceshi/**").addResourceLocations("file:F:\\eimg\\ceshi\\");
	}
}