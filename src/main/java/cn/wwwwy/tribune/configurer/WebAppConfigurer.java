package cn.wwwwy.tribune.configurer;

import cn.wwwwy.tribune.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebAppConfigurer implements WebMvcConfigurer {
	@Autowired
	private MyInterceptor myInterceptor;
	/*
	 * 需要权限访问的可添加到下面
	 * */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> excludePathList = new ArrayList<>();
		excludePathList.add("/login.html");
		excludePathList.add("/register.html");
		excludePathList.add("/404.html");
		excludePathList.add("/401.html");
		excludePathList.add("/500.html");
		excludePathList.add("/js/**");
		excludePathList.add("/css/**");
		excludePathList.add("/fonts/**");
		excludePathList.add("/img/**");
		/*注册登录*/
		//excludePathList.add("/user/login");
		//excludePathList.add("/user/register");
		excludePathList.add("/user/**");
		registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns(excludePathList);
	}
}
