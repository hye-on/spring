package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    //요구사항
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//db가 알아서 생성->IDENTITY
    private Long Id;//고객이 저장 X 시스템이 구분하기 위해


    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
