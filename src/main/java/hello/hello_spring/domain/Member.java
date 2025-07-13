package hello.hello_spring.domain;

public class Member {
    private Long id; // 고객이 저장하는 게 아니라, 시스템이 구분을 위해 저장하는 값
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
