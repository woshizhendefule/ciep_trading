package com.chengyu.ciep_trading.aop;

import com.chengyu.ciep_trading.domain.vo.UserInfo;
import com.chengyu.ciep_trading.utils.JwtUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CL
 */
@Aspect
@Component
public class UserIdAdvice {
    /**
     * 调用方法Mapping
     */
    private static final Set<String> CONTROLLER_MAPPING;

    static {
        CONTROLLER_MAPPING = new HashSet<>();
        CONTROLLER_MAPPING.add("/user/login");
    }

    /**
     * 截断Authorization的BEARER 值
     */
    public static final String BEARER = "Bearer ";

    /**
     * 定义切入点（Pointcut）：所有控制器执行时
     */
    @Pointcut("execution(public * com.chengyu.ciep_trading.controller.*.*(..)))")
    public void pointCut() {

    }

    /**
     * 切入点（Pointcut）之前（Before）执行：解析Token获取用户id 进行用户唯一标识
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        // 获取RequestContext -> 获取Request属性 -> 获取Request -> 获取Header中的Authorization
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        String authorization = request.getHeader("Authorization");

        // 获取Request URI
        String requestURI = request.getRequestURI();

        // 判断调用方法Mapping是否需要Token解析方法：CONTROLLER_MAPPING中的mapping 必定不执行；
        //                                      CONTROLLER_MAPPING外的mapping 若无authorization的Token值则执行，反之执行
        if (!CONTROLLER_MAPPING.contains(requestURI) && authorization == null) {
            return;
        }

        Integer id = null;
        if (authorization != null) {
            // 截断Authorization的BEARER 值 获取Token
            String token = authorization.substring(BEARER.length());
            // Token解析 获取id
            id = JwtUtils.getUserIdFromToken(token);
        }

        // JoinPoint中获取参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            // 如果arg属于UserInfo类/子类，arg强转为UserInfo并赋值
            if (arg instanceof UserInfo) {
                UserInfo userInfo = (UserInfo) arg;
                userInfo.setId(id);
            }
        }
    }
}
