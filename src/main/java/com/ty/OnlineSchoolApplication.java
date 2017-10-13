package com.ty;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ty.utils.SpringUtil;
import com.ty.utils.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@RestController
@SpringBootApplication

public class OnlineSchoolApplication {
	@Value("${com.ty.name}")
	private String name;
	@Value("${com.ty.i}")
	private int i;
	@Resource(name = "Stringtest")
	private String str;
    @Autowired
	private Student student;


	@RequestMapping("/")
	public String index(){
		System.out.print("student"+student);
		return "hello "+name+", springboot ."+i+" "+str+student;
	}
	@RequestMapping("/getbean")
	public Object getBean(){
		System.out.println("getBean");
		return SpringUtil.getBean("Stringtest");
	}
	public static void main(String[] args) {
		SpringApplication.run(OnlineSchoolApplication.class, args);
		/*Student student=(Student) SpringUtil.getBean("student");
		System.out.println(student.getName());
		student.setName("taoyong");
		Student stu=(Student) SpringUtil.getBean("student");
		System.out.println(stu.getName());*/
	}


}
