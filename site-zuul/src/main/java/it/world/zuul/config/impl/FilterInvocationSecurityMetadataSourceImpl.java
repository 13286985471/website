package it.world.zuul.config.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import it.world.zuul.entity.SysPermission;
import it.world.zuul.mapper.AuthFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    private Map<String, Collection<ConfigAttribute>> map=null;

    @Autowired
    private AuthFeign authFeign;
    /*public FilterInvocationSecurityMetadataSourceImpl(HashMap<String, Collection<ConfigAttribute>> map) {
        this.map = loadResourceDefine();
    }*/

    public void  loadResourceDefine() {
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;

        //获取当前用户的所有角色
        /*List<SysRole> roles = ((SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles();
        if(!roles.isEmpty()){
            for(SysRole role:roles){
                List<SysPermission> sysPermissions= role.getSysPermissions();
                //遍历用户中的所有权限
                for (SysPermission sysPermission : sysPermissions) {
                    array = new ArrayList<>();
                    //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
                    cfg = new SecurityConfig(sysPermission.getPmName());
                    array.add(cfg);
                    //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value
                    map.put(sysPermission.getUrl(), array);
                }
            }
        }*/

        String strSpm=authFeign.queryAllSPM();
        JSONArray jsonSpm= JSONArray.parseArray(strSpm);
        List<SysPermission> sysPermissions = jsonSpm.toJavaList(SysPermission.class);
        for (SysPermission sysPermission : sysPermissions) {
            array = new ArrayList<>();
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            cfg = new SecurityConfig(sysPermission.getPmName());
            array.add(cfg);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value
            map.put(sysPermission.getUrl(), array);
        }
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map==null){
            loadResourceDefine();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        //遍历权限表中的url
        for (String url : map.keySet()) {
            matcher = new AntPathRequestMatcher(url);
            //与request对比，符合则说明权限表中有该请求的URL
            if(matcher.matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
