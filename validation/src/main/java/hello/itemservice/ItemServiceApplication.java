package hello.itemservice;

import hello.itemservice.web.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItemServiceApplication /*implements WebMvcConfigurer*/ {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	//WebMvcConfigurer implements해서 구현해야함
	//검증 1 중 Validator 분리 2 관련 -> ItemValidator 글로벌 설정으로 만들 수 있음
	//그러면 @InitBinder public void init(WebDataBinder dataBinder) {~} 로 addValidators 하는 메서드 없어도 됨
	//*****이 글로벌 설정 사용하면 BeanValidator 자동 등록 안 되므로 유의
	//***글로벌 설정 직접 사용하는 경우는 드묾
//	@Override
//	public Validator getValidator() {
//		return new ItemValidator();
//	}
}
