package com.baby_p2p.baby_p2p.dao;

import com.baby_p2p.baby_p2p.entity.TUserAccount;
import com.baby_p2p.baby_p2p.entity.TUserInfo;
import com.baby_p2p.baby_p2p.entity.TUserWallet;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    //用户登录
    public TUserAccount login(String username);

    public int loginDate( @Param("username")String username);
    //用户注册
    public int register1(@Param("id") String id, @Param("username")String username, @Param("password")String password);

    public int register2(@Param("account_id")String account_id);

    public int register3(@Param("account_id")String account_id);

    //个人信息
    public TUserInfo getTUserInfo(String account_id);

    public TUserWallet getTUserWallet(String account_id);
}
