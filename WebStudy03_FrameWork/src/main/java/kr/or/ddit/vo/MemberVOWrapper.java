package kr.or.ddit.vo;

import java.security.Principal;

import lombok.ToString;

public class MemberVOWrapper implements Principal {
	private MemberVO realMember;
	
	public MemberVOWrapper(MemberVO adaptee) {
		this.realMember = adaptee;
	}
	
	@Override
	public String getName() {
		return realMember.getMemId();
	}
	
	public MemberVO getRealMember() {
		return realMember;
	}
}
