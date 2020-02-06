package cn.wwwwy.tribune.interceptor;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfiguration {
	// 3.1.1之后不需要这一步来完成逻辑删除
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}
}
