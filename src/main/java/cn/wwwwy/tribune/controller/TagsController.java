package cn.wwwwy.tribune.controller;


import cn.wwwwy.tribune.entity.Tags;
import cn.wwwwy.tribune.service.ITagsService;
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

import javax.swing.text.html.HTML;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-09
 */
@Controller
@RequestMapping("/tags")
public class TagsController extends BaseController {
	@Autowired
	private ITagsService iTagsService;
	@ResponseBody
	@PostMapping(value = "add")
	public Result insertTags(Tags tags){
		if (tags.getId()==null){
			iTagsService.save(tags);
			return new Result("成功",200,tags);
		}
		else{
			iTagsService.updateById(tags);
			return new Result("成功",201,tags);
		}
	}
	@ResponseBody
	@PostMapping(value = "deletes")
	public Result deletes(String ids){
		List<String> idList = Arrays.asList(ids.split(","));
		boolean b = iTagsService.removeByIds(idList);
		if (b){
			return new Result("删除成功",200,null);
		}else {
			return new Result("删除失败",500,null);
		}
	}
	@ResponseBody
	@PostMapping(value = "delete")
	public Result delete(Integer id){
		boolean b = iTagsService.removeById(id);
		if (b){
			return new Result("删除成功",200,null);
		}else {
			return new Result("删除失败",500,null);
		}
	}
	@GetMapping(value = "page")
	public ModelAndView page(Integer current, Integer pageSize, ModelAndView modelAndView, String keyWords){
		if (keyWords==null)keyWords = "";
		IPage<Tags> page = new Page<>(current,pageSize);
		QueryWrapper<Tags> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("tags_name",keyWords);
		page = iTagsService.page(page,queryWrapper);
		modelAndView.getModel().put("data",buildPage(page));
		modelAndView.getModel().put("keyWords",keyWords);
		modelAndView.setViewName("tags_manager");
		return modelAndView;
	}
	@GetMapping(value = "getById")
	@ResponseBody
	public Tags getById(Integer id){
		return iTagsService.getById(id);
	}
}
