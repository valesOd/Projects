package com.vales.socialSupport.service;

import com.vales.socialSupport.entity.Login;
import com.vales.socialSupport.entity.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;
    public Login findByLoginName(String name) {

    String sql = "SELECT * FROM Login WHERE Login.Login = ?";

    Connection conn = null;

    try {
        conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        Login login = null;
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            login = new Login(
                    rs.getString("Login"),
                    rs.getString("Password")
            );
        }
        rs.close();
        ps.close();
        return login;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}

    @Override
     public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Login login = findByLoginName(name);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(login.getLogin(), login.getPassword(), roles);
        return userDetails;
    }

}
