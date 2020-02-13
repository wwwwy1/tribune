package cn.wwwwy.tribune.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class TestController {
	//发布项目后改为项目地址
	private static String UPLOADED_FOLDER = "F:\\eimg\\ceshi\\";

	@RequestMapping("/uploadImage")
	@ResponseBody
	public Map<String, Object> editormdPic(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		String fileName = file.getOriginalFilename();// 获取文件名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀
		String newFileName = UUID.fastUUID() + suffixName;
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(),
					new File(UPLOADED_FOLDER + newFileName));
			resultMap.put("success", 1);
			resultMap.put("message", "上传成功！");
			resultMap.put("url", "http://localhost:8911/images/ceshi/" + newFileName);
		} catch (Exception e) {
			resultMap.put("success", 0);
			resultMap.put("message", "上传失败！");
			e.printStackTrace();
		}

		return resultMap;
	}
}
