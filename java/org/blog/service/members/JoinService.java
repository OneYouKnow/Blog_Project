package org.blog.service.members;

import org.blog.controllers.members.JoinRequest;
import org.blog.entity.Member;
import org.blog.repository.MemberRepository;
import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.mindrot.bcrypt.BCrypt;


@Service
public class JoinService {
	
	@Autowired
	private MemberRepository repo;
	
	public void process(JoinRequest joinRequest) {
		
		String hash = BCrypt.hashpw(joinRequest.getMemPw(), BCrypt.gensalt(12));
		
		Member member = new Member();
		member.setMemId(joinRequest.getMemId());
		member.setMemNm(joinRequest.getMemNm());
		member.setEmail(joinRequest.getEmail());
		member.setMobile(joinRequest.getMobile());
		member.setMemPw(joinRequest.getMobile());
		member.setMemPw(hash);
		
		repo.save(member);
	}
}
