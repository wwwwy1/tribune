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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
	@Override
	public Result<String> login(String username, String password) {
		QueryWrapper<User> byUsername = new QueryWrapper<>();
		QueryWrapper<User> byEmail = new QueryWrapper<>();
		byUsername.eq("user_name",username);
		byUsername.eq("user_password",password);
		byEmail.eq("user_email",username);
		byEmail.eq("user_password",password);
		User userByName = userMapper.selectOne(byUsername);
		User userByEmail = userMapper.selectOne(byEmail);
		if (userByEmail == null && userByName == null){
			return Result.error(CodeMsg.USER_NOT_FOUND);
		}
		UUID uuid = UUID.fastUUID();
		// 登录后,把token返回到前台,并且把token保存到redis中
		redisTemplate.opsForValue().set(uuid.toString(),userByEmail==null?userByName.getId():userByEmail.getId());

		return Result.success(uuid.toString());
	}

	@Override
	public Result<String> register(User user) {
		userMapper.insert(user);
		return Result.success("成功");
	}

	@Override
	public Result<Boolean> checkEmail(String email) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_email",email);
		User user = userMapper.selectOne(queryWrapper);
		if (user == null){
			return Result.success(false);
		}else {
			return Result.error(CodeMsg.REGISTER_ERROR_EMAIL);
		}
	}

	@Override
	public Result<Boolean> checkUserName(String userName) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name",userName);
		User user = userMapper.selectOne(queryWrapper);
		if (user == null){
			return Result.success(true);
		}else {
			return Result.error(CodeMsg.REGISTER_ERROR_USERNAME);
		}
	}
}
