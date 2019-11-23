package cn.wwwwy.tribune.util;

import lombok.Data;

@Data
public class CodeMsg {
	private int code;
	private String msg;

	public static final String LOGIN_SUCCESS = "登录成功";

	public static CodeMsg SERVER_ERROR = new CodeMsg(500, "服务器内部错误");
	public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(401, "无访问权限");
	public static CodeMsg RESOURCE_NOT_FOUND = new CodeMsg(404,"找不到资源");


	public static CodeMsg USER_NOT_FOUND = new CodeMsg(5001,"用户名或密码错误");
	public static CodeMsg REGISTER_ERROR_EMAIL = new CodeMsg(5002,"邮箱已被注册");
	public static CodeMsg REGISTER_ERROR_USERNAME = new CodeMsg(5003,"用户名已被注册");

	public CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
