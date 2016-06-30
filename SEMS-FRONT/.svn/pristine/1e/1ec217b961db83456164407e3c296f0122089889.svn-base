package com.ktds.sems.validator.member;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ktds.sems.member.vo.MemberVO;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberVO member = (MemberVO) target;

		String id = member.getId();
		if ( id == null || id.length() == 0 ) {
			errors.rejectValue("id", "field.required", "error default message");
		}
		
		String password = member.getPassword();
		if ( password == null || password.length() == 0) {
			errors.rejectValue("password", "field.required", "error default message");
		}
		
		String name = member.getName();
		if ( name == null || name.length() == 0) {
			errors.rejectValue("name", "field.required", "error default message");
		}
		
		String email = member.getEmail();
		if ( email == null || email.length() == 0) {
			errors.rejectValue("email", "field.required", "error default message");
		}
		
		String birthDate = member.getBirthDate();
		if ( birthDate == null || birthDate.length() == 0) {
			errors.rejectValue("birthDate", "field.required", "error default message");
		}
		
		String phoneNumber = member.getPhoneNumber();
		if ( phoneNumber == null || phoneNumber.length() == 0) {
			errors.rejectValue("phoneNumber", "field.required", "error default message");
		}
					
		String memberType = member.getMemberType();
		if ( memberType == null || memberType.length() == 0) {
			errors.rejectValue("memberType", "field.required", "error default message");
		}
	}
}
