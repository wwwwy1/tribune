package cn.wwwwy.tribune.controller;


import cn.wwwwy.tribune.entity.DailyWord;
import cn.wwwwy.tribune.service.IDailyWordService;
import cn.wwwwy.tribune.util.BaseController;
import cn.wwwwy.tribune.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/dailyWord")
public class DailyWordController extends BaseController {
	@Autowired
	private IDailyWordService iDailyWordService;
	@PostMapping(value = "add")
	public Result insertDailyWord(DailyWord dailyWord){
		if (dailyWord.getId()==null)
			iDailyWordService.save(dailyWord);
		else
			iDailyWordService.updateById(dailyWord);
		return new Result("成功",200,dailyWord);
	}
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
	public Object page(Integer current,Integer pageSize){
		IPage<DailyWord> page = new Page<>(current,pageSize);
		page = iDailyWordService.page(page);
		return buildPage(page);
	}
}
