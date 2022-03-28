package hello.springmvc.basic;

import lombok.Data;

@Data//Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode, Value
public class HelloData {
    private String username;
    private int age;
}
