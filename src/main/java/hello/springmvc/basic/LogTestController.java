package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j//롬복 제공
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

//        log.trace("trace log=" + name);//이렇게 사용하면 안됨: 연산이 일어나면서 메모리 사용하게 됨.(사용유무와 관계없이 쓸모없는 리소스를 사용하게됨)


        /**
         * log: 로그 레벨이 따라 개발 서버에서는 모든 로그를 출력하고, 운영서버에서는 출력하지 않는 등
         * 로그를 상황에 맞게 조절할 수 있다.
         * 성능도 일단 System.out 보다 좋다. (내부 버퍼링, 멀티 쓰레드 등등) 그래서 실무에서는 꼭 로그를 사용해야 한다.
         */
        log.trace("trace log={}", name);//
        log.debug("debug log={}", name);//개발서버(개발서버)
        log.info("info log={}", name);//비즈니스정보, 운영시스템 정보 (운영 서버)
        log.warn("warn log={}", name);//경고(위험)
        log.error("error log-{}", name);//에러로고


        return "OK";//RestController: Http 메시지 바디에 바로 들어감. 따라서 실행 결과로 ok 메시지를 받을 수 있다.
    }

}
