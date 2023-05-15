package com.itwill.post.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.User;
import com.itwill.post.repository.UserDao;

public class UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    private final UserDao userDao = UserDao.getInstance();
    
    // singleton
    private static UserService instance = null;
    private UserService() {}
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    // username과 password가 일치하면 null이 아닌 User 객체, 일치하지 않으면 null 리턴.
    public User singIn (String username, String password) {
        log.info("signIn(username= {}, password= {})", username, password);
        
        User test = new User(0, username, password, null, 0);
        User result = userDao.selectByUsernameAndPassword(test);
        
        return result;
    }
    
}
