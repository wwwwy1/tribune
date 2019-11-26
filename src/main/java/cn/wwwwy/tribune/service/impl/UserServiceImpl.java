package cn.wwwwy.tribune.service.impl;

import cn.hutool.core.lang.UUID;
import cn.wwwwy.tribune.entity.User;
import cn.wwwwy.tribune.mapper.UserMapper;
import cn.wwwwy.tribune.service.IUserService;
import cn.wwwwy.tribune.util.CodeMsg;
import cn.wwwwy.tribune.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2019-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Resource
	private UserMapper userMapper;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public Result login(String username, String password) {
		QueryWrapper<User> byUsername = new QueryWrapper<>();
		QueryWrapper<User> byEmail = new QueryWrapper<>();
		byUsername.eq("user_name",username);
		byUsername.eq("user_password",password);
		byEmail.eq("user_email",username);
		byEmail.eq("user_password",password);
		User userByName = userMapper.selectOne(byUsername);
		User userByEmail = userMapper.selectOne(byEmail);
		if (userByEmail == null && userByName == null){
			return new Result("用户名或密码错误",500,null);
		}
		log.info("UserServiceImpl-login: username-" + username + "password-" + password);
		UUID uuid = UUID.fastUUID();
		// 登录后,把token返回到前台,并且把token保存到redis中
		redisTemplate.opsForValue().set(uuid.toString(),userByEmail==null?userByName.getId():userByEmail.getId());

		return new Result("登录成功",200,userByEmail == null?userByName:userByEmail);
	}

	@Override
	public Result register(User user) {
		if (!checkEmail(user.getUserEmail())){
			return new Result("该邮箱已被注册",500,null);
		}
		if (!checkUserName(user.getUserName())){
			return new Result("该用户名已被占用",500,null);
		}
		userMapper.insert(user);
		log.info("UserServiceImpl-register: user" + user);
		return new Result("注册成功",200,null);
	}

	public Boolean checkEmail(String email) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_email",email);
		List<User> user = userMapper.selectList(queryWrapper);
		if (user.size() > 0){
			return false;
		}else {
			return true;
		}
	}
	public Boolean checkUserName(String userName) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name",userName);
		List<User> user = userMapper.selectList(queryWrapper);
		if (user.size() > 0){
			return false;
		}else {
			return true;
		}
	}



	/*@Override
	public Result checkEmail(String email) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_email",email);
		List<User> user = userMapper.selectList(queryWrapper);
		if (user.size() > 0){
			return new Result("该邮箱已被注册",500,null);
		}else {
			return new Result("邮箱可用",200,null);
		}
	}

	@Override
	public Result checkUserName(String userName) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name",userName);
		List<User> user = userMapper.selectList(queryWrapper);
		if (user.size() > 0){
			return new Result("该用户已被注册",500,null);
		}else {
			return new Result("用户名可用",200,null);
		}
	}*/
}
