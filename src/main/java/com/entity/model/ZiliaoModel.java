package com.entity.model;

import com.entity.ZiliaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 资料信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZiliaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 老师
     */
    private Integer laoshiId;


    /**
     * 资料信息名称
     */
    private String ziliaoName;


    /**
     * 资料信息编号
     */
    private String ziliaoUuidNumber;


    /**
     * 资料信息封面
     */
    private String ziliaoPhoto;


    /**
     * 资料信息类型
     */
    private Integer ziliaoTypes;


    /**
     * 资料信息下载
     */
    private String ziliaoFile;


    /**
     * 资料信息介绍
     */
    private String ziliaoContent;


    /**
     * 逻辑删除
     */
    private Integer ziliaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
