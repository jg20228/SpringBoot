package com.wc.demo.repository;

import java.util.List;

import com.wc.demo.model.Member;

public interface MemberRepository {
	public Member findById(int id);
	public List<Member> findAll();
}
