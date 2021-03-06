package cn.wwwwy.tribune.controller;


import cn.wwwwy.tribune.entity.DailyWord;
import cn.wwwwy.tribune.service.IDailyWordService;
import cn.wwwwy.tribune.util.BaseController;
import cn.wwwwy.tribune.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2019-12-14
 */
@Controller
@RequestMapping("/dailyWord")
public class DailyWordController extends BaseController {
	@Autowired
	private IDailyWordService iDailyWordService;
	@ResponseBody
	@PostMapping(value = "add")
	public Result insertDailyWord(DailyWord dailyWord){
		if (dailyWord.getId()==null){
			iDailyWordService.save(dailyWord);
			return new Result("成功",200,dailyWord);
		}
		else{
			iDailyWordService.updateById(dailyWord);
			return new Result("成功",201,dailyWord);
		}
	}
	@ResponseBody
	@PostMapping(value = "deletes")
	public Result deletes(String ids){
		List<String> idList = Arrays.asList(ids.split(","));
		boolean b = iDailyWordService.removeByIds(idList);
		if (b){
			return new Result("删除成功",200,null);
		}else {
			return new Result("删除失败",500,null);
		}
	}
	@ResponseBody
	@PostMapping(value = "delete")
	public Result delete(Integer id){
		boolean b = iDailyWordService.removeById(id);
		if (b){
			return new Result("删除成功",200,null);
		}else {
			return new Result("删除失败",500,null);
		}
	}
	@GetMapping(value = "page")
	public ModelAndView page(Integer current, Integer pageSize, ModelAndView modelAndView, String keyWords){
		if (keyWords==null)keyWords = "";
		IPage<DailyWord> page = new Page<>(current,pageSize);
		QueryWrapper<DailyWord> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("content",keyWords);
		page = iDailyWordService.page(page,queryWrapper);
		modelAndView.getModel().put("data",buildPage(page));
		modelAndView.getModel().put("keyWords",keyWords);
		modelAndView.setViewName("table_bootstrap");
		return modelAndView;
	}
	@GetMapping(value = "getById")
	@ResponseBody
	public DailyWord getById(Integer id){
		return iDailyWordService.getById(id);
	}
}
