package ee.bcs.valiit.tasks.solution.controller;

import ch.qos.logback.core.net.server.Client;
import ee.bcs.valiit.tasks.solution.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        String password = bankRepository.findByUserName(userName);
        return User.withUsername(userName) //(client.getUserName())
                .password(password) //password(client.getPassword())
                .roles("USER").build();
    }

//          teha repositorysse meetodi findByUserName(). kliendi tabelisse parooli väli hashi kujul ja kasutajanimi väli. bcrypt generator.

}


