package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 签到
 *
 * @author 
 * @email
 */
@TableName("qiandao")
public class QiandaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public QiandaoEntity() {

	}

	public QiandaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 内容
     */
    @ColumnInfo(comment="内容",type="text")
    @TableField(value = "qiandao_text")

    private String qiandaoText;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="text")
    @TableField(value = "qiandao_beizhu")

    private String qiandaoBeizhu;


    /**
     * 签到时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="签到时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：内容
	 */
    public String getQiandaoText() {
        return qiandaoText;
    }
    /**
	 * 设置：内容
	 */

    public void setQiandaoText(String qiandaoText) {
        this.qiandaoText = qiandaoText;
    }
    /**
	 * 获取：备注
	 */
    public String getQiandaoBeizhu() {
        return qiandaoBeizhu;
    }
    /**
	 * 设置：备注
	 */

    public void setQiandaoBeizhu(String qiandaoBeizhu) {
        this.qiandaoBeizhu = qiandaoBeizhu;
    }
    /**
	 * 获取：签到时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：签到时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Qiandao{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", qiandaoText=" + qiandaoText +
            ", qiandaoBeizhu=" + qiandaoBeizhu +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
