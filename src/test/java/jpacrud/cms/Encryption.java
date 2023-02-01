//package jpacrud;
//
//import application.global.jpacrud.Sha256Hash;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.security.InvalidAlgorithmParameterException;
//import java.security.NoSuchAlgorithmException;
//
//@ActiveProfiles("local")
//@SpringBootTest
//@Transactional
//@Slf4j
//class Encryption {
//
//    private Sha256Hash sha256Hash;
//
//    // 로거 설정
//    Logger logger = LoggerFactory.getLogger(Encryption.class);
//
//    @Test
//    public void doEncryption() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
//        String result = sha256Hash.encrypt("admin");
//        logger.debug("암호화 결과 : {}",result);
//    };
//
//}
