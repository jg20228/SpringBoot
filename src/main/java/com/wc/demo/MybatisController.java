package com.wc.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wc.demo.model.Member;
import com.wc.demo.repository.MemberRepository;

@Controller
public class MybatisController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping({"/member/{id}"})
	public @ResponseBody Member findById(@PathVariable int id) {
		Member member = memberRepository.findById(id);
		return member;
	}
	@GetMapping({"/member/"})
	public @ResponseBody List<Member> findAll() {
		List<Member> members = memberRepository.findAll();
		return members;
	}
	
}
