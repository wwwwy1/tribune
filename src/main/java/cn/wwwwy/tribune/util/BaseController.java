package cn.wwwwy.tribune.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 控制器的基类，将公共方法写在这个类中
 */

public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    
    protected static final String QUERY = "QUERY";
    
    protected static final String LIST = "LIST";
    protected static final String INFO = "INFO";
    protected static final String EXPT = "EXPT";
    protected static final String IMPT = "IMPT";
    protected static final String DTL = "DTL";
    
    protected static final int EXPORT_MAX_ROW = 20000;
    
    protected static final int SUCCESS = 0;
    protected static final String SUCCESS_MSG = "成功";
    protected static final int FAILURE = 400;
    
    protected static final String ITEMS = "list";
    

    /********************************************结果解析*****************************************************/
    public Object buildSuccess() {
        return buildSuccess(SUCCESS_MSG);
    }
    
    public Object buildSuccess(String msg) {
        return buildResult(SUCCESS, null, null, msg, null);
    }
    
    public Object buildFailure(String message) {
        return buildResult(FAILURE, null, null, message, null);
    }
    
    protected Object buildObj(Object obj) {
        return buildResult(SUCCESS, obj, null, null, null);
    }
    

    public Object buildList(Collection<?> list) {
        Map<String, Collection<?>> mapData = getMapData(list);
        return buildResult(SUCCESS, mapData, null, null, null);
    }
    

    public Object buildPage(IPage<?> page) {
        Map<String, Collection<?>> mapData = getMapData(page.getRecords());
        return buildResult(SUCCESS, mapData, page, null, null);
    }

    
    private Object buildResult(int code, Object data, IPage<?> page, String msg, String gridName) {
        Map<String, Object> root = new LinkedHashMap<String, Object>();
        root.put("code", code);
        if (null != msg) {
            root.put("msg", msg);
        }
        if (null != data) {
            root.put("data", data);
        }
        if (null != page) {
            Map<String, Object> pagination = new HashMap<String, Object>();
            pagination.put("total", page.getTotal());
            pagination.put("pageSize", page.getSize());
            pagination.put("pageNum", page.getCurrent());
            pagination.put("pages",page.getPages());
            root.put("page", pagination);
        }
        return root;
    }
    
    private Map<String, Collection<?>> getMapData(Collection<?> list) {
        Map<String, Collection<?>> map = new HashMap<>();
        map.put(ITEMS, list);
        return map;
    }

    

    

}
