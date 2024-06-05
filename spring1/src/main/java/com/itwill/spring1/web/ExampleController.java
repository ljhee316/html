package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring1.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

//POCO(Plain Old C# Object)
//POJO(Plain Old Java Object):간단한 오래된 자바객체.
//  -특정클래스를 상속하거나 특정 인터페이스를 구현(implenments)할 필요가 없는 
//   (상위타입의 특정메서드들을 반드사 오버라이딩할 필요가 없는) 평범한 자바 객체.
//스프링 MVC프레임워크에서는 POJO로 작성된 클래스를 컨트롤러로 사용할 수 있음!
//---(비교) HttpServlet을 상속받는 클래스에서는 doGet(req, resp), doPost(req, resp)를 반드시 재정의 해야 웹서비스 요청처리가 가능했음.

@Slf4j //->private static final Logger log = LoggerFactory.getLogger(ExampleController.class);
@Controller //디스패쳐 서블릿에게 컨트롤러 컴포넌트임을 알려줌.  사용법:
//1.servlet-context.xml 파일에서는 <context:component-scan .../> 설정해야함
//2.컨트롤러 클래스에서는 @Controller 애너테이션을 사용해야함.
//->디스패쳐 서블릿이 컨트롤러 객체를 생성. 관리
public class ExampleController {
	
	@GetMapping("/") //Map<key, value> 핸들러맵핑이 관리함.  {GET [/]}: home(Model)
	public String home(Model model) { //디스패쳐에서 객체생성된 model임.
		log.debug("home()");
		
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now",now); //model은 뷰에 전달할 데이터. --> org.springframework.ui.Model;
		//Model 객체는 컨트롤러에서 뷰로 데이터를 전달할때 사용.
		//request.setAtrribute(name,object)랑 비슷한 기능.
		
		return "home";  //jsp 파일이름. (뷰리졸버한테 jsp이름을 물어봄.)  /WEB-INF/views/home.jsp  파일동기화
		//컨트롤러 메서드가 문자열을 리턴하면, 디스패쳐 서블릿이 뷰의 이름을 찾는데 사용.
		//디스패쳐 서블릿이 뷰 리졸버를 이용해서 /WEB-INF/views/returnValue.jsp 경로를 찾을 수 있다.(servlet.context.xml에서 경로설정했음.)
	}
	
	@GetMapping("/example")
	public void controllerExample() {  //리턴값이 없을때는 요청주소가 들어감.
		log.debug("controllerExample()");
	} //컨트롤러 메서드가 리턴값이 없는 경우(void로 선언된경우),
	  // 요청주소가 뷰의 이름이 됨.
	
	@GetMapping("/ex1")
	public void example1(@RequestParam(name="username") String username, @RequestParam(name="age", defaultValue = "0") int age, Model model) {
		log.debug("example1(username={}, age={})", username, age);
		//컨트롤러 메서드 파라미터를 선언할때 @RequestParam 애너테이션을 사용해서
		//디스패쳐 서블릿이 컨트롤러 메서드를 호출할때,
		//request.getParameter("username"), Integer.parseInt(request.getParameter("age")) 호출해서
		//요청파라미터 값들을 읽고, 컨트롤러 메서드의 아규먼트로 전달해줌.
		
		//요청파라미터값들로 UserDto 객체 생성
		UserDto user = UserDto.builder().username(username).age(age).build();
		//UserDto 객체를 뷰로 전달
		model.addAttribute("user", user);
	}
	
	@PostMapping("/ex2")
	public String ex2(@ModelAttribute(name="user") UserDto dto) {
		log.debug("ex2(dto={})", dto);
		// 디스패쳐 서블릿은 컨트롤러 메서드를 호출하기 위해서
        // UserDto 클래스 기본 생성자를 호출하고, 요청 파라미터 이름으로 setter를 찾아서 호출.
        // 생성된 객체를 컨트롤러 메서드의 아규먼트로 전달.
		
		//@ModelAttribute(name="user") UserDto dto 파라미터 선언은   UserDto dto 그대로 뷰에 전달해야할때.
		//model.addAttribute("user", dto); 코드 작성과 같음. (아규먼트를  Model model 주었을때)
		//컨트롤러에서 뷰로 전달하는 데이터.
	
		return "ex1"; //->뷰 이름을 줌. ex1.jsp 파일로 이동함.
	}
	
	@GetMapping("/test")
	public void test() {
		log.debug("test()");		
	}
	
	@GetMapping("/test2")
	public String forward() {
		log.debug("forward()");
		return "forward:/test";
		//컨트롤러 메서드가 "forward:"으로 시작하는 문자열을 리턴.  ->포워드방식의 이동.
		//포워드방식의 페이지 이동은 최초요청주소가 바뀌지 않음!
	}
	
	@GetMapping("/test3")  //post-redirect-get
	public String redirect() {
		log.debug("redirect()");
		return "redirect:/test";
		//컨트롤러 메서드가 "redirect:"으로 시작하는 문자열을 리턴.  -> 리다이렉트방식의 이동.
		//리다이렉트방식의 페이지 이동은 최초요청주소가 리다이렉트되는 주소로 바뀜!!!
	}
	
	
	
}
