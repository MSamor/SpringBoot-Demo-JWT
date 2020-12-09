package maosi.service;

import maosi.entity.User;
import maosi.mapper.UserMapper;
import maosi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;

    public User getUser(String account,String password){
        User user = userMapper.getUser(account);
        if (user == null){
            return null;
        }else if(user.getPassword().equals(password)){
            Map<String, Object> map = new HashMap<>();
            map.put("username",user.getUsername());
            String token = JWTUtil.createToken(map);
            user.setToken(token);
            user.setPassword(null);
            return user;
        }else {
            return null;
        }
    }
}
