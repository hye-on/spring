package hello.hellospring.domain;

public class Member {

    //요구사항
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
