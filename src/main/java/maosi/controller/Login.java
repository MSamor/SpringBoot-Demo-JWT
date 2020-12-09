package maosi.controller;

import io.swagger.annotations.*;
import maosi.entity.User;
import maosi.core.result.Result;
import maosi.core.result.ResultGenerator;
import maosi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "用户登录",tags = {"用户登录接口"})
@RestController
public class Login {
    //    @ApiImplicitParams({
//                    @ApiImplicitParam(name = "account",value = "账号",dataType = "string",required = true),
//                    @ApiImplicitParam(name = "password",value = "密码",dataType = "string",required = true)
//    })

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录",notes = "登录note")
    public Result login(@ApiParam(name="用户对象",value="传入json格式",required=true) @RequestParam String account , @RequestParam String password){
        if (account.equals("null") ||  password.equals("null")){
            return ResultGenerator.genFailResult("账号或密码缺失");
        }else {
            User user = loginService.getUser(account,password);
            if (user == null){
                return new Result().setMessage("账号或密码错误");
            }else {
                return ResultGenerator.genSuccessResult(user);
            }
        }
    }
}
