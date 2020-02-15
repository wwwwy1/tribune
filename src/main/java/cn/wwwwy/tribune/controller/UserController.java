package cn.wwwwy.tribune.controller;


import cn.wwwwy.tribune.entity.DailyWord;
import cn.wwwwy.tribune.entity.User;
import cn.wwwwy.tribune.service.IUserService;
import cn.wwwwy.tribune.util.BaseController;
import cn.wwwwy.tribune.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

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
public class UserController extends BaseController {
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
	@GetMapping(value = "page")
	public ModelAndView page(Integer current, Integer pageSize, ModelAndView modelAndView, String keyWords){
		if (keyWords==null)keyWords = "";
		IPage<User> page = new Page<>(current,pageSize);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("insert_date");
		//queryWrapper.like("user_nikename",keyWords);
		page = iUserService.page(page,queryWrapper);
		modelAndView.getModel().put("data",buildPage(page));
		modelAndView.getModel().put("keyWords",keyWords);
		modelAndView.setViewName("user_manager");
		return modelAndView;
	}
	@ResponseBody
	@PostMapping(value = "delete")
	public Result delete(String id){
		boolean b = iUserService.removeById(id);
		if (b){
			return new Result("删除成功",200,null);
		}else {
			return new Result("删除失败",500,null);
		}
	}
	@GetMapping(value = "getById")
	@ResponseBody
	public User getById(String id){
		return iUserService.getById(id);
	}

	@ResponseBody
	@PostMapping(value = "deletes")
	public Result deletes(String ids){
		List<String> idList = Arrays.asList(ids.split(","));
		boolean b = iUserService.removeByIds(idList);
		if (b){
			return new Result("删除成功",200,null);
		}else {
			return new Result("删除失败",500,null);
		}
	}
	@ResponseBody
	@PostMapping(value = "add")
	public Result insertUser(User user){
		if (user.getId()==null){
			Result register = iUserService.register(user);
			return register;
		}
		else{
			iUserService.updateById(user);
			return new Result("成功",201,user);
		}
	}
	@PostMapping(value = "checkName")
	public Boolean checkName(String userName){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name",userName);
		int count = iUserService.count(queryWrapper);
		return count!=0;
	}
}
