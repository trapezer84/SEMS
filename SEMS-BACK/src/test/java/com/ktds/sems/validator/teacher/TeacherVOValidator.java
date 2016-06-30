package com.ktds.sems.validator.teacher;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ktds.sems.teacher.vo.TeacherVO;

public class TeacherVOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TeacherVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TeacherVO teacherVO = (TeacherVO) target;

		int annual = teacherVO.getAnnual();
		if (annual < 0) {
			errors.rejectValue("annual", "field.required", "error default message");
		}
	}
}