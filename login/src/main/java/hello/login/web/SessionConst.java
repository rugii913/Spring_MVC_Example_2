package hello.login.web;

public interface SessionConst {
    String LOGIN_MEMBER = "loginMember";
    //상수만 사용하려고 만든 파일이므로, 클래스 인스턴스 생성하지 않게 하려면
    //보통 클래스로 만들어서 public static final 붙이기보다는
    //abstract class로 만들거나
    //interface로 만드는 게 낫다 -> public static 안 붙여도 알아서 public static으로 됨
}
