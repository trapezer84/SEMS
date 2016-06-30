package com.ktds.sems.member.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

public class PersonalInfoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PersonalInfoReadVO personalInfoReadVO = (PersonalInfoReadVO) target;

		String memberId = personalInfoReadVO.getMemberId();
		if ( memberId == null || memberId.length() == 0 ) {
			errors.rejectValue("memberId", "field.required", "error default message");
		}
		
		String targetMemberId = personalInfoReadVO.getTargetMemberId();
		if ( targetMemberId == null || targetMemberId.length() == 0) {
			errors.rejectValue("targetMemberId", "field.required", "error default message");
		}
		
		String description = personalInfoReadVO.getDescription();
		if ( description == null || description.length() == 0) {
			errors.rejectValue("description", "field.required", "error default message");
		}
	}
}
