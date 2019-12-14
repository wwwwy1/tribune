package cn.wwwwy.tribune.service;

import cn.wwwwy.tribune.entity.User;
import cn.wwwwy.tribune.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwwwy
 * @since 2019-11-23
 */
public interface IUserService extends IService<User> {
	public Result login(String username, String password,HttpServletRequest request, HttpServletResponse response);
	public Result register(User user);
	//public Result checkEmail(String email);
	//public Result checkUserName(String userName);

}
