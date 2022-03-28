package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={},age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username={},age={}", memberName, memberAge);
        return "ok";//http바디에 넣어서 응답해버림(@ResponseBody)
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, //("name")이 생략가능하다. 대신, 파라미터 네임과 동일한 이름 부여해야함
            @RequestParam int age //int 값 안넣으면 오류남, Integer로 바꿔줘야함
    ) {
        log.info("username={},age={}", username, age);
        return "ok";//http바디에 넣어서 응답해버림(@ResponseBody)
    }


    /**
     * (아래 코드와 같이) 이렇게 에노테이션을 완전히 생략해도 되는데, 너무 없는 것도 약간 과한 것 같은 주관적 생각.
     * @RequestParam 이 있으면 명확하게 요청 파라미터에서 데이터를 읽는 다는 것을 알 수 있다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {//@어노테이션 마저 생략이 가능하다. 역시 파라미터 네임과 동일해야함
        log.info("username={},age={}", username, age);
        return "ok";//http바디에 넣어서 응답해버림(@ResponseBody)
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = false) String username,//requried 생략시 true가 기본
            @RequestParam(required = true) int age) {//필수값(없으면 오류남)
        log.info("username={},age={}", username, age);
        return "ok";//http바디에 넣어서 응답해버림(@ResponseBody)
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = false, defaultValue = "guest") String username,//값이 없으면 guest로 하겠다. + 빈문자열까지 인식함
            @RequestParam(required = true, defaultValue = "-1")int age) {
        log.info("username={},age={}", username, age);
        return "ok";//http바디에 넣어서 응답해버림(@ResponseBody)
    }

    /**
     * Map, MultiValueMap으로 값을 모두 꺼낼 수 있다.
     * Map(key=value)
     * MultiValueMap(key=[value1, value2,...])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={},age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";//http바디에 넣어서 응답해버림(@ResponseBody)
    }

    /**
     * @ModelAttribute
     * HelloData객체 생성
     * 요청 파라미터 이름으로 HelloData 객체의 프로퍼티(setXxx,getXxx)를 찾는다.
     * 그리고 해당 프로퍼티의 setter를 호출해서 파라미터 값을 입력(바인딩)한다.
     * 파라미터 이름이 username이면 setUsername()메서드를 찾아서 호출하면서 값을 입력한다.
     * ※주의: 타입이 맞지 않는 경우 BindException 발생 (ex: int에 문자열 넣을 경우)
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={},age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);//@Data로 인해 toString자동

        return "ok";
    }

    /**
     * @ModelAttribute: 생략가능
     * 스프링은 해당 생략시 다음과 같은 규칙을 적용한다.
     * int, String Integer 같은 단순 타입은 @RequestParam
     * 기타 나머지는 @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={},age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }


}

