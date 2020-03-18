package cn.wwwwy.tribune.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
    * 
    * </p>
*
* @author wwwwy
* @since 2020-02-25
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class SensitiveWords implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 敏感词id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 敏感词内容
            */
        @TableField("content")
    private String content;

    /**
     * 软删除  0:未删除 1:已删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 插入时间
     */
    @TableField(value = "insert_date",fill = FieldFill.INSERT)
    private LocalDateTime insertDate;
    /**
     * 更新时间
     */
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;


}
