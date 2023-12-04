
package com.security.springboot.demoSecurity.service;

import com.security.springboot.demoSecurity.entity.User;
import com.security.springboot.demoSecurity.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	void save(WebUser webUser);

}
