package httpjar.service.Impl;

import httpjar.mapper.UserMapper;
import httpjar.mapper.UsersInfoMapper;
import httpjar.model.po.User;
import httpjar.model.po.UsersInfo;
import httpjar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UsersInfoMapper usersInfoMapper;

    @Override
    public boolean checkUser(String account) {
        User user = userMapper.selectByAccount(account);
        return user != null && user.getId() != 0;
    }

    @Override
    public User registerUser(String account) {
        // 三个表单条目
        UsersInfo usersInfo = new UsersInfo();
        usersInfoMapper.insert(usersInfo);

        User user = new User();
        user.setAccount(account);
        user.setAutoUpdate(false);
        user.setUserInfoId(usersInfo.getId());
        userMapper.insert(user);
        return user;
    }
}
