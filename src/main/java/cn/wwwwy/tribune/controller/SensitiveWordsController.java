package cn.wwwwy.tribune.controller;


import cn.wwwwy.tribune.entity.DailyWord;
import cn.wwwwy.tribune.entity.SensitiveWords;
import cn.wwwwy.tribune.service.ISensitiveWordsService;
import cn.wwwwy.tribune.util.BaseController;
import cn.wwwwy.tribune.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-25
 */
@Controller
@RequestMapping("/sensitiveWords")
public class SensitiveWordsController extends BaseController {
	@Autowired
	private ISensitiveWordsService iSensitiveWordsService;
	@ResponseBody
	@PostMapping(value = "add")
	public Result insertDailyWord(SensitiveWords sensitiveWords){
		if (sensitiveWords.getId()==null){
			QueryWrapper<SensitiveWords> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("content",sensitiveWords.getContent());
			SensitiveWords one = iSensitiveWordsService.getOne(queryWrapper);
			if (one != null)return new Result("失败",500,sensitiveWords);
			iSensitiveWordsService.save(sensitiveWords);
			return new Result("成功",200,sensitiveWords);
		}
		else{
			iSensitiveWordsService.updateById(sensitiveWords);
			return new Result("成功",201,sensitiveWords);
		}
	}
	@ResponseBody
	@PostMapping(value = "deletes")
	public Result deletes(String ids){
		List<String> idList = Arrays.asList(ids.split(","));
		boolean b = iSensitiveWordsService.removeByIds(idList);
		if (b){
			return new Result("删除成功",200,null);
		}else {
			return new Result("删除失败",500,null);
		}
	}
	@ResponseBody
	@PostMapping(value = "delete")
	public Result delete(Integer id){
		boolean b = iSensitiveWordsService.removeById(id);
		if (b){
			return new Result("删除成功",200,null);
		}else {
			return new Result("删除失败",500,null);
		}
	}
	@GetMapping(value = "page")
	public ModelAndView page(Integer current, Integer pageSize, ModelAndView modelAndView, String keyWords){
		if (keyWords==null)keyWords = "";
		IPage<SensitiveWords> page = new Page<>(current,pageSize);
		QueryWrapper<SensitiveWords> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("content",keyWords);
		page = iSensitiveWordsService.page(page,queryWrapper);
		modelAndView.getModel().put("data",buildPage(page));
		modelAndView.getModel().put("keyWords",keyWords);
		modelAndView.setViewName("sensitive_words");
		return modelAndView;
	}
	@GetMapping(value = "getById")
	@ResponseBody
	public SensitiveWords getById(Integer id){
		return iSensitiveWordsService.getById(id);
	}
}
