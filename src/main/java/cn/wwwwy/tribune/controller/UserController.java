package cn.wwwwy.tribune.controller;


import cn.wwwwy.tribune.entity.User;
import cn.wwwwy.tribune.service.IUserService;
import cn.wwwwy.tribune.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2019-11-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService iUserService;
	@PostMapping("login")
	public Result toLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
		return iUserService.login(username,password,request,response);
	}

	@PostMapping("register")
	public Result toRegister(User user){
		return iUserService.register(user);
	}
	/*@GetMapping("checkEmail/{email}")
	public Result checkEmail(@PathVariable String email){
		return iUserService.checkEmail(email);
	}
	@GetMapping("checkUserName/{userName}")
	public Result checkUserName(@PathVariable String userName){
		return iUserService.checkUserName(userName);
	}*/
}
