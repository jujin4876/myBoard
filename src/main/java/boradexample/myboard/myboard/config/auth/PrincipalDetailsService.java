package boradexample.myboard.myboard.config.auth;

import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member entity = memberRepository.searchByUserName(username);
        System.out.println("aaa: " + entity);
        if(entity == null){
            //System.out.println("username: "+ username);
            throw new UsernameNotFoundException(username);
        }
        return new PrincipalDetails(entity);
    }

}
