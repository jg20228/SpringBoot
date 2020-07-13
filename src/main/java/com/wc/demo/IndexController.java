package com.wc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@Autowired
	Test t;

	@GetMapping({ "index" })
	public String home() {
		return "index";
	}

	// http://localhost:8080/demo
	// http://localhost:8080/demo/
	@GetMapping({ "", "/" })
	public @ResponseBody String index() {

		System.out.println(t.num);

		return "Hello"; // ViewResolver
	}

	// x-www-form-urlencoded => key = value
	@PostMapping("/form")
	public @ResponseBody String user(String username, String password, String email) {
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		return "User"; // ViewResolver
	}

	// x-www-form-urlencoded => key = value
	// 주소를 호출하는거지 함수이름을 호출하지 않아서 맘대로 해도 된다.
	// 변수이름이 똑같아야한다.
	// mime type이 x-www-form-urlencoded 일때만 이렇게 받을 수 있다.
	@PostMapping("/form/model")
	public @ResponseBody String formModel(User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		return "User"; // ViewResolver
	}

	// 주소가 똑같아도 요청방식이 다르니까 분기가 된다.
	@GetMapping("/form/model")
	public @ResponseBody String formModelGet(User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		return "User"; // ViewResolver
	}

	// json => { "key" : value }
	// json은 get으로 받을 수 있는 방법이 없다.

	// 원래는 버퍼리더로 읽고 gson으로 해줬다.

	// 스프링부트부터는 jackson이 request(주소로 함수 사용 직전)와 response(함수가 종료될때) jack_binder가 발동됨
	// json 데이터를 null값인지 확인하고 gson으로 파싱하는 것을

	// 함수의 핵심로직만 빼고 전에 null값 검사나 후에 처리하는 일들을 빼는 것을 AOP라고 한다.
	// 이렇게 되면 핵심로직만 함수안에서 관리, 그외는 AOP로 전(준비함수), 후(끝 함수) 를 처리 할 수 있음.

	// 잭슨바인더(json 파싱해주는 애)가 -함수 실행 직전 @RequestBody를 읽어서 jackson이라는 라이브러리가
	// AOP를 직접 만들 수도 있다. ex)null 처리하는것들
	@PostMapping("/json/model")
	public @ResponseBody User jsonModel(@RequestBody User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		return user; // ViewResolver
		// 잭슨바인더가 return할때도 돌려줌
		// 조건 : @ResponseBody가 안적혀있으면 return할때 파일로 인식한다.
		// 스프링은 기본으로 return시 ViewResolver를 돌려준다.
		// 이게 기본전략이며 @ResponseBody가 붙어있으면 ViewResolver가 작동안한다.
		// 근데 잭슨이 무조건 발동되는게 아니라 object일때만 작동한다.
		// ?을 붙이면 PrintWriter가 작동하는 이때 gson같은걸 작동시켜서 json으로 던져줌

		// 안드로이드는 form으로 요청을 못함, 그래서 data body에 담을때 json으로 받는다.
		// json이니까 @RequestBody

		// 특수한 경우에 key=value로 달라고 하면 "username="+user.getUsername()+"&"...으로 가능
	}
}
