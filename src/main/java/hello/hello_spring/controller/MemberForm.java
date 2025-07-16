package hello.hello_spring.controller;

public class MemberForm {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // MemberForm을 호출하면, MemberForm의 name과 createMemberForm의 name이 같아짐


}
