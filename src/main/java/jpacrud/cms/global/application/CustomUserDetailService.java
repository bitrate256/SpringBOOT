//package jpacrud.global.application;
//
//import entity.user.domain.jpacrud.User;
//import repository.user.domain.jpacrud.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class CustomUserDetailService implements UserDetailsService{
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String riderId) throws UsernameNotFoundException {
//
//        User user = userRepository.findById(Long.valueOf(riderId)).orElse(null);
//
//        return user;
//    }
//}
// 불필요함