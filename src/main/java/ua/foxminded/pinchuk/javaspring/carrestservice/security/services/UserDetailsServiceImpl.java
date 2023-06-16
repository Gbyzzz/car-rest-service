package ua.foxminded.pinchuk.javaspring.carrestservice.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userService.findByEmail(username);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return UserDetailsImpl.build(user);
	}

}
