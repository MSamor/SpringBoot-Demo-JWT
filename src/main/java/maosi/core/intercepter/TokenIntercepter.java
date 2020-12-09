package maosi.core.intercepter;

import com.alibaba.fastjson.JSON;
import maosi.core.result.Result;
import maosi.core.result.ResultGenerator;
import maosi.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static maosi.core.result.ResultCode.UNAUTHORIZED;

public class TokenIntercepter implements HandlerInterceptor {
    //日志文件
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
            //处理token值
            String auth = request.getHeader("auth");
            if (auth == null){
                responseResult(response, new Result().setCode(UNAUTHORIZED).setMessage("请携带token"));
                return false;
            }
            int verifyToken = JWTUtil.verifyToken(auth);
            if (verifyToken != 0){
                responseResult(response, new Result().setCode(UNAUTHORIZED).setMessage("鉴权失败，你没有权限访问本接口"));
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
    }

    //拦截器返回体
    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
