package zhaohang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import zhaohang.entity.Depart;
import zhaohang.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 赵航
 * @since 2023-10-13
 */
@RestController
@RequestMapping("/abc")
@RefreshScope  //配置文件动态刷新
public class DepartController {
    @Autowired
    private DepartService service;
    @Autowired //客户端可以直接使用
    private DiscoveryClient client;
    @Value("${depart.name}")
    private String departName;
    //添加
    @PostMapping("/")
    public Boolean addHandle(@RequestBody Depart depart){
        return  service.save(depart);
    }
    //删除
    @DeleteMapping("/{id}")
    public Boolean deleteHandle(@PathVariable("id") Integer id){
        return service.removeById(id);
    }
    //修改
    @PutMapping("/")
    public Boolean updataHandle(@RequestBody Depart depart){
        return service.updateById(depart);
    }
    //全表查询
    @GetMapping("/list")
    public List<Depart> getHandleAll(){
        System.out.println("11111111111"+departName);
       return service.list();
    }
    //id查询
    @GetMapping("/{id}")
    public Depart getHandleById(@PathVariable("id") Integer id){
        return service.getById(id);
    }
    @GetMapping("/discovery")
    public List<String> discovery(){
        //获取注册中心的所有服务名称
        List<String> services = client.getServices();
        for(String serviceName : services){
            //获取指定微服务名称的所有微服务实例
            List<ServiceInstance> instances = client.getInstances(serviceName);
            for(ServiceInstance instance: instances){
                Map<String,Object> map = new HashMap<>();
                map.put("serviceName",serviceName);
                map.put("serviceId" ,instance.getServiceId());
                map.put("serviceHost",instance.getHost());
                map.put("servicePort" ,instance.getPort());
                map.put("serviceUri",instance.getUri());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(map);
            }
        }
        return services;
    }
    //读取配置文件内容
    @GetMapping("/aaa")
    public String getDepartName() {
        System.out.println("++++++++++++++++"+departName);
        return departName;
    }
}
