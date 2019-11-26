package cn.wwwwy.tribune.util;

import lombok.Data;

@Data
public class Result {
	private String msg;
	private Integer code;
	private Object data;


	public Result(String msg, Integer code, Object data) {
		this.msg = msg;
		this.code = code;
		this.data = data;
	}
}
