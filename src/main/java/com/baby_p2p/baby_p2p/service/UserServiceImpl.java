package com.baby_p2p.baby_p2p.service;


import com.baby_p2p.baby_p2p.dao.UserMapper;
import com.baby_p2p.baby_p2p.entity.TUserAccount;
import com.baby_p2p.baby_p2p.entity.TUserInfo;
import com.baby_p2p.baby_p2p.entity.TUserWallet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public TUserAccount login(String username) {
        int faly = userMapper.loginDate(username);
        if (faly!=0){
            return userMapper.login(username);
        }
        return null;
    }

    @Override
    public boolean register(String id, String username, String password) {
        boolean faly =  false;
        int register1 = userMapper.register1(id,username,password);
        int register2 = userMapper.register2(id);
        int register3 = userMapper.register3(id);
        if (register1 != 0&&register2!=0&&register3!=0){
            faly = true;
        }
        return faly;
    }

    @Override
    public TUserInfo getTUserInfo(String id) {
        return userMapper.getTUserInfo(id);
    }

    @Override
    public TUserWallet getTUserWallet(String id) {
        return userMapper.getTUserWallet(id);
    }
}
