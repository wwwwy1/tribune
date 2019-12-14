package cn.wwwwy.tribune.controller;


import cn.wwwwy.tribune.entity.DailyWord;
import cn.wwwwy.tribune.mapper.DailyWordMapper;
import cn.wwwwy.tribune.service.IDailyWordService;
import cn.wwwwy.tribune.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DailyWordController {
	@Autowired
	private IDailyWordService iDailyWordService;
	@PostMapping
	public Result insertDailyWord(DailyWord dailyWord){
		iDailyWordService.save(dailyWord);
		return new Result("成功",200,dailyWord);
	}

}
