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
 * 资料信息
 *
 * @author 
 * @email
 */
@TableName("ziliao")
public class ZiliaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZiliaoEntity() {

	}

	public ZiliaoEntity(T t) {
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
     * 老师
     */
    @ColumnInfo(comment="老师",type="int(11)")
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 资料信息名称
     */
    @ColumnInfo(comment="资料信息名称",type="varchar(200)")
    @TableField(value = "ziliao_name")

    private String ziliaoName;


    /**
     * 资料信息编号
     */
    @ColumnInfo(comment="资料信息编号",type="varchar(200)")
    @TableField(value = "ziliao_uuid_number")

    private String ziliaoUuidNumber;


    /**
     * 资料信息封面
     */
    @ColumnInfo(comment="资料信息封面",type="varchar(200)")
    @TableField(value = "ziliao_photo")

    private String ziliaoPhoto;


    /**
     * 资料信息类型
     */
    @ColumnInfo(comment="资料信息类型",type="int(11)")
    @TableField(value = "ziliao_types")

    private Integer ziliaoTypes;


    /**
     * 资料信息下载
     */
    @ColumnInfo(comment="资料信息下载",type="varchar(200)")
    @TableField(value = "ziliao_file")

    private String ziliaoFile;


    /**
     * 资料信息介绍
     */
    @ColumnInfo(comment="资料信息介绍",type="text")
    @TableField(value = "ziliao_content")

    private String ziliaoContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "ziliao_delete")

    private Integer ziliaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：老师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }
    /**
	 * 设置：老师
	 */

    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 获取：资料信息名称
	 */
    public String getZiliaoName() {
        return ziliaoName;
    }
    /**
	 * 设置：资料信息名称
	 */

    public void setZiliaoName(String ziliaoName) {
        this.ziliaoName = ziliaoName;
    }
    /**
	 * 获取：资料信息编号
	 */
    public String getZiliaoUuidNumber() {
        return ziliaoUuidNumber;
    }
    /**
	 * 设置：资料信息编号
	 */

    public void setZiliaoUuidNumber(String ziliaoUuidNumber) {
        this.ziliaoUuidNumber = ziliaoUuidNumber;
    }
    /**
	 * 获取：资料信息封面
	 */
    public String getZiliaoPhoto() {
        return ziliaoPhoto;
    }
    /**
	 * 设置：资料信息封面
	 */

    public void setZiliaoPhoto(String ziliaoPhoto) {
        this.ziliaoPhoto = ziliaoPhoto;
    }
    /**
	 * 获取：资料信息类型
	 */
    public Integer getZiliaoTypes() {
        return ziliaoTypes;
    }
    /**
	 * 设置：资料信息类型
	 */

    public void setZiliaoTypes(Integer ziliaoTypes) {
        this.ziliaoTypes = ziliaoTypes;
    }
    /**
	 * 获取：资料信息下载
	 */
    public String getZiliaoFile() {
        return ziliaoFile;
    }
    /**
	 * 设置：资料信息下载
	 */

    public void setZiliaoFile(String ziliaoFile) {
        this.ziliaoFile = ziliaoFile;
    }
    /**
	 * 获取：资料信息介绍
	 */
    public String getZiliaoContent() {
        return ziliaoContent;
    }
    /**
	 * 设置：资料信息介绍
	 */

    public void setZiliaoContent(String ziliaoContent) {
        this.ziliaoContent = ziliaoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZiliaoDelete() {
        return ziliaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZiliaoDelete(Integer ziliaoDelete) {
        this.ziliaoDelete = ziliaoDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Ziliao{" +
            ", id=" + id +
            ", laoshiId=" + laoshiId +
            ", ziliaoName=" + ziliaoName +
            ", ziliaoUuidNumber=" + ziliaoUuidNumber +
            ", ziliaoPhoto=" + ziliaoPhoto +
            ", ziliaoTypes=" + ziliaoTypes +
            ", ziliaoFile=" + ziliaoFile +
            ", ziliaoContent=" + ziliaoContent +
            ", ziliaoDelete=" + ziliaoDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
