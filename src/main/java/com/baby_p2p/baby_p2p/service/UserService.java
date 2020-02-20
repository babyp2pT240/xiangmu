package com.baby_p2p.baby_p2p.service;

import com.baby_p2p.baby_p2p.entity.TUserAccount;
import com.baby_p2p.baby_p2p.entity.TUserInfo;
import com.baby_p2p.baby_p2p.entity.TUserWallet;

public interface UserService {
    public TUserAccount login(String username);

    public boolean register(String id,String username,String password);

    public TUserInfo getTUserInfo(String id);

    public TUserWallet getTUserWallet(String id);
}
