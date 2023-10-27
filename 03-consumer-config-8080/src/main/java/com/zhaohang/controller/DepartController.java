package com.zhaohang.controller;


//import com.consumerabc.entity.Depart;
import com.zhaohang.entity.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 赵航
 * @since 2023-10-11
 */
@RestController
@RequestMapping("/consumer")
public class DepartController {
    @Autowired
    private RestTemplate restTemplate;
    //直连方式
//    private static final String SERVICE_PROCIER = "http://localhost:8081/abc";
    //微服务连接
    private static final String SERVICE_PROCIER = "http://depart-provider/abc";

    @PostMapping("/") //添加
    public Boolean addHandle(@RequestBody Depart depart){
        String url = SERVICE_PROCIER+"/";
        return  restTemplate.postForObject(url,depart,Boolean.class);
    }
    @DeleteMapping("/{id}")//删除
    public void deleteHandle(@PathVariable("id") Integer id){
        String url = SERVICE_PROCIER+"/"+id;
         restTemplate.delete(url);
    }
    @PutMapping("/")//修改
    public void updataHandle(@RequestBody Depart depart){
        String url = SERVICE_PROCIER+"/";
        restTemplate.put(url,depart);
    }
    @GetMapping("/{id}") //id查询
    public Depart getHandleById(@PathVariable("id") Integer id){
        String url = SERVICE_PROCIER+"/"+id;
        return restTemplate.getForObject(url,Depart.class);
    }
    @GetMapping("/list")//全表
    public List<Depart> getHandleAll(){
        String url = SERVICE_PROCIER+"/list";
        return restTemplate.getForObject(url,List.class);
    }
}

