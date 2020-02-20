package com.baby_p2p.baby_p2p.controller;

import com.baby_p2p.baby_p2p.entity.TUserInfo;
import com.baby_p2p.baby_p2p.entity.TUserWallet;
import com.baby_p2p.baby_p2p.service.UserService;
import com.baby_p2p.baby_p2p.service.UserServiceImpl;
import com.baby_p2p.baby_p2p.entity.TUserAccount;
import com.baby_p2p.baby_p2p.tools.Item;
import com.baby_p2p.baby_p2p.tools.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    private MD5Utils md5Utils;

    private Item items;

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestParam("username")String username, @RequestParam("password")String password){
        Map map = new HashMap<String,Object>();
        TUserAccount tUserAccount = userService.login(username);
        String passwordmd5 = md5Utils.toMD5(password);
        if (tUserAccount==null){
            map.put("code","400");
            map.put("msg","用户不存在");
        }else if (!username.equals(tUserAccount.getUsername())){
            map.put("code","400");
            map.put("msg","用户名错误");
        }else if (!passwordmd5.equals(tUserAccount.getPassword())){
            map.put("code","400");
            map.put("msg","密码错误");
        }else {
            map.put("code","200");
            map.put("data",tUserAccount);
        }
        return map;
    }

    @PostMapping("/checkUsername")
    @ResponseBody
    public Object checkUsername(@RequestParam("username")String username){
        boolean faly = false;
        TUserAccount tUserAccount = userService.login(username);
        if (tUserAccount == null){
            faly = true;
        }else {
            faly = false;
        }
        return faly;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestParam("username")String username, @RequestParam("password")String password){
        String id = items.getItemID(32);
        Map map = new HashMap<String,Object>();
        String password5 = MD5Utils.toMD5(password);
        boolean faly = userService.register(id,username,password5);
        if (faly){
            map.put("code","200");
        }else {
            map.put("code","400");
            map.put("msg","添加失败");
        }
        return map;
    }

    @PostMapping("/userinfo/get/{id}")
    @ResponseBody
    public Object userinfoget(@PathVariable("id")String id){
        Map map = new HashMap<String,Object>();
        TUserInfo tUserInfo = userService.getTUserInfo(id);
        if (tUserInfo.getAccountId().equals("")){
            map.put("code","400");
            map.put("msg","查询失败");
        }else{
            map.put("code","200");
            map.put("data",tUserInfo);
        }
        return  map;
    }



    @PostMapping("/wallet/get/{id}")
    @ResponseBody
    public Object walletget(@PathVariable("id")String id){
        Map map = new HashMap<String,Object>();
        TUserWallet tUserWallet = userService.getTUserWallet(id);
        if (tUserWallet.getAccountId().equals("")){
            map.put("code","400");
            map.put("msg","查询失败");
        }else {
            map.put("code","200");
            map.put("data",tUserWallet);
        }
        return  map;
    }
}
