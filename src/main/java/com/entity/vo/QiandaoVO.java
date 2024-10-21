package com.entity.vo;

import com.entity.QiandaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 签到
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("qiandao")
public class QiandaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 内容
     */

    @TableField(value = "qiandao_text")
    private String qiandaoText;


    /**
     * 备注
     */

    @TableField(value = "qiandao_beizhu")
    private String qiandaoBeizhu;


    /**
     * 签到时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：内容
	 */
    public String getQiandaoText() {
        return qiandaoText;
    }


    /**
	 * 获取：内容
	 */

    public void setQiandaoText(String qiandaoText) {
        this.qiandaoText = qiandaoText;
    }
    /**
	 * 设置：备注
	 */
    public String getQiandaoBeizhu() {
        return qiandaoBeizhu;
    }


    /**
	 * 获取：备注
	 */

    public void setQiandaoBeizhu(String qiandaoBeizhu) {
        this.qiandaoBeizhu = qiandaoBeizhu;
    }
    /**
	 * 设置：签到时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：签到时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
