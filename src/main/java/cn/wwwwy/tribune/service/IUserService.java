package cn.wwwwy.tribune.service;

import cn.wwwwy.tribune.entity.User;
import cn.wwwwy.tribune.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwwwy
 * @since 2019-11-23
 */
public interface IUserService extends IService<User> {
	public Result<String> login(String username, String password);
	public Result<String> register(User user);
	public Result<Boolean> checkEmail(String email);
	public Result<Boolean> checkUserName(String userName);

}
