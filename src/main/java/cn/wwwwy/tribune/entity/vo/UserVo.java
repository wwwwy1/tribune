package cn.wwwwy.tribune.entity.vo;

import cn.wwwwy.tribune.entity.User;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* <p>
    * 
    * </p>
*
* @author wwwwy
* @since 2019-11-23
*/
    @Data
public class UserVo extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer current;
    private Integer total;
    private Integer size;
    private List<User> userList;

}
