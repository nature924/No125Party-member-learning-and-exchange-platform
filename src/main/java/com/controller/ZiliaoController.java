
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 资料信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/ziliao")
public class ZiliaoController {
    private static final Logger logger = LoggerFactory.getLogger(ZiliaoController.class);

    private static final String TABLE_NAME = "ziliao";

    @Autowired
    private ZiliaoService ziliaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ExampaperService exampaperService;//试卷
    @Autowired
    private ExampapertopicService exampapertopicService;//试卷选题
    @Autowired
    private ExamquestionService examquestionService;//试题表
    @Autowired
    private ExamrecordService examrecordService;//考试记录表
    @Autowired
    private ExamredetailsService examredetailsService;//答题详情表
    @Autowired
    private ExamrewrongquestionService examrewrongquestionService;//错题表
    @Autowired
    private KechengService kechengService;//课程
    @Autowired
    private LaoshiService laoshiService;//老师
    @Autowired
    private QiandaoService qiandaoService;//签到
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZuoyeService zuoyeService;//作业
    @Autowired
    private ZuoyeTijiaoService zuoyeTijiaoService;//作业提交
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        params.put("ziliaoDeleteStart",1);params.put("ziliaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = ziliaoService.queryPage(params);

        //字典表数据转换
        List<ZiliaoView> list =(List<ZiliaoView>)page.getList();
        for(ZiliaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZiliaoEntity ziliao = ziliaoService.selectById(id);
        if(ziliao !=null){
            //entity转view
            ZiliaoView view = new ZiliaoView();
            BeanUtils.copyProperties( ziliao , view );//把实体数据重构到view中
            //级联表 老师
            //级联表
            LaoshiEntity laoshi = laoshiService.selectById(ziliao.getLaoshiId());
            if(laoshi != null){
            BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLaoshiId(laoshi.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZiliaoEntity ziliao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,ziliao:{}",this.getClass().getName(),ziliao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("老师".equals(role))
            ziliao.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZiliaoEntity> queryWrapper = new EntityWrapper<ZiliaoEntity>()
            .eq("laoshi_id", ziliao.getLaoshiId())
            .eq("ziliao_name", ziliao.getZiliaoName())
            .eq("ziliao_types", ziliao.getZiliaoTypes())
            .eq("ziliao_delete", ziliao.getZiliaoDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiliaoEntity ziliaoEntity = ziliaoService.selectOne(queryWrapper);
        if(ziliaoEntity==null){
            ziliao.setZiliaoDelete(1);
            ziliao.setInsertTime(new Date());
            ziliao.setCreateTime(new Date());
            ziliaoService.insert(ziliao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZiliaoEntity ziliao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,ziliao:{}",this.getClass().getName(),ziliao.toString());
        ZiliaoEntity oldZiliaoEntity = ziliaoService.selectById(ziliao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("老师".equals(role))
//            ziliao.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(ziliao.getZiliaoPhoto()) || "null".equals(ziliao.getZiliaoPhoto())){
                ziliao.setZiliaoPhoto(null);
        }
        if("".equals(ziliao.getZiliaoFile()) || "null".equals(ziliao.getZiliaoFile())){
                ziliao.setZiliaoFile(null);
        }

            ziliaoService.updateById(ziliao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZiliaoEntity> oldZiliaoList =ziliaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZiliaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZiliaoEntity ziliaoEntity = new ZiliaoEntity();
            ziliaoEntity.setId(id);
            ziliaoEntity.setZiliaoDelete(2);
            list.add(ziliaoEntity);
        }
        if(list != null && list.size() >0){
            ziliaoService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ZiliaoEntity> ziliaoList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZiliaoEntity ziliaoEntity = new ZiliaoEntity();
//                            ziliaoEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //老师 要改的
//                            ziliaoEntity.setZiliaoName(data.get(0));                    //资料信息名称 要改的
//                            ziliaoEntity.setZiliaoUuidNumber(data.get(0));                    //资料信息编号 要改的
//                            ziliaoEntity.setZiliaoPhoto("");//详情和图片
//                            ziliaoEntity.setZiliaoTypes(Integer.valueOf(data.get(0)));   //资料信息类型 要改的
//                            ziliaoEntity.setZiliaoFile(data.get(0));                    //资料信息下载 要改的
//                            ziliaoEntity.setZiliaoContent("");//详情和图片
//                            ziliaoEntity.setZiliaoDelete(1);//逻辑删除字段
//                            ziliaoEntity.setInsertTime(date);//时间
//                            ziliaoEntity.setCreateTime(date);//时间
                            ziliaoList.add(ziliaoEntity);


                            //把要查询是否重复的字段放入map中
                                //资料信息编号
                                if(seachFields.containsKey("ziliaoUuidNumber")){
                                    List<String> ziliaoUuidNumber = seachFields.get("ziliaoUuidNumber");
                                    ziliaoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> ziliaoUuidNumber = new ArrayList<>();
                                    ziliaoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("ziliaoUuidNumber",ziliaoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //资料信息编号
                        List<ZiliaoEntity> ziliaoEntities_ziliaoUuidNumber = ziliaoService.selectList(new EntityWrapper<ZiliaoEntity>().in("ziliao_uuid_number", seachFields.get("ziliaoUuidNumber")).eq("ziliao_delete", 1));
                        if(ziliaoEntities_ziliaoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZiliaoEntity s:ziliaoEntities_ziliaoUuidNumber){
                                repeatFields.add(s.getZiliaoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [资料信息编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        ziliaoService.insertBatch(ziliaoList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = ziliaoService.queryPage(params);

        //字典表数据转换
        List<ZiliaoView> list =(List<ZiliaoView>)page.getList();
        for(ZiliaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZiliaoEntity ziliao = ziliaoService.selectById(id);
            if(ziliao !=null){


                //entity转view
                ZiliaoView view = new ZiliaoView();
                BeanUtils.copyProperties( ziliao , view );//把实体数据重构到view中

                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(ziliao.getLaoshiId());
                if(laoshi != null){
                    BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLaoshiId(laoshi.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZiliaoEntity ziliao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,ziliao:{}",this.getClass().getName(),ziliao.toString());
        Wrapper<ZiliaoEntity> queryWrapper = new EntityWrapper<ZiliaoEntity>()
            .eq("laoshi_id", ziliao.getLaoshiId())
            .eq("ziliao_name", ziliao.getZiliaoName())
            .eq("ziliao_uuid_number", ziliao.getZiliaoUuidNumber())
            .eq("ziliao_types", ziliao.getZiliaoTypes())
            .eq("ziliao_delete", ziliao.getZiliaoDelete())
//            .notIn("ziliao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiliaoEntity ziliaoEntity = ziliaoService.selectOne(queryWrapper);
        if(ziliaoEntity==null){
            ziliao.setZiliaoDelete(1);
            ziliao.setInsertTime(new Date());
            ziliao.setCreateTime(new Date());
        ziliaoService.insert(ziliao);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

