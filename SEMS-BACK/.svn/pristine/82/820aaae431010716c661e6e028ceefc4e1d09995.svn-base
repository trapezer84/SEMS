package com.ktds.sems.member.biz;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.member.dao.MemberDAO;
import com.ktds.sems.member.vo.GraduationTypeVO;
import com.ktds.sems.member.vo.HighestEducationLevelVO;
import com.ktds.sems.member.vo.MemberSearchVO;
import com.ktds.sems.member.vo.MemberTypeVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.PersonalInfoReadVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class MemberBizTest extends SemsTestCase {

	@Autowired
	private MemberBiz memberBiz;
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void changePasswordTest () {
		MemberVO member = new MemberVO();
		member.setId("sosdig1");
		member.setPassword("4c5b7ab6a121aae1acda84fc71ed4b135e9f8eb7f1a25013515845e9c7ddc9f8");
		member.setSalt("9ca0645b12e961ac");
		boolean isSuccess = memberBiz.changePassword(member);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void modifyMemberTypeByIdTest () {
		Map<String, String> modifyMemberType = new HashMap<String, String>();
		modifyMemberType.put("memberId", "sosdig1");
		modifyMemberType.put("memberTypeId", "MBR");
		boolean isSuccess = memberBiz.modifyMemberTypeById(modifyMemberType);
		
		assertTrue(isSuccess);
	}

	
	@Test
	public void getTotalMemberCountTest () { 
		
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		
		int totalCount = memberBiz.getTotalMemberCount(memberSearchVO);
		
		assertTrue(totalCount >= 0);
	}
	
	@Test
	public void getAllMemberListTest () { 
		MemberSearchVO memberSearchVO = new MemberSearchVO();
		memberSearchVO.setSearchKeyword("");
		memberSearchVO.setSearchType("");
		memberSearchVO.setConnLock("");
		memberSearchVO.setIsRgsn("");
		memberSearchVO.setModLock("");
		memberSearchVO.setStartIndex(0);
		memberSearchVO.setEndIndex(10);
		List<MemberVO> memberList = memberBiz.getAllMemberList(memberSearchVO);
		
		assertNotNull(memberList);
		if(memberList != null){
			assertTrue(memberList.size() == 10);
		}
		else{
			fail("Fail...");
		}
	}
	

	
	@Test
	public void isDuplicationIdTest(){
		String id = "test01";
		String secondId = "asdfasdf123";
		boolean isSuccess = memberBiz.isDuplicationId(id);
		boolean isFail = memberBiz.isDuplicationId(secondId);
		
		assertTrue(isSuccess);
		assertFalse(isFail);
	}
	
	@Test
	public void isVerifyPasswordTest() {
		String password = "asdfasdf123!";
		boolean isSuccess = memberBiz.isVerifyPassword(password);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void isVerifyEmailTest() {
		String email = "test01@naver.com";
		boolean isSuccess = memberBiz.isVerifyEmail(email);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void isExistEmailTest() {
		String email = "teacher01@naver.com";
		boolean isSuccess = memberBiz.isExistEmail(email);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void isVerifyPhoneNumberTest() {
		String phoneNumber = "010-1234-1234";
		boolean isSuccess = memberBiz.isVerifyPhoneNumber(phoneNumber);
		
		assertTrue(isSuccess);
	}
	
	@Test
	public void massiveDeleteMemberTest () {
		MemberVO member = new MemberVO();
		member.setId("Junit1231");
		member.setPassword("4c5b7ab6a121aae1acda84fc71ed4b135e9f8eb7f1a25013515845e9c7ddc9f8");
		member.setSalt("9ca0645b12e961ac");
		member.setName("Junit");
		member.setEmail("Junit@naver.com");
		member.setHighestEducationLevel("UNIV");
		member.setUniversityName("서울대");
		member.setMajorName("컴공");
		member.setGraduationType("ABST");
		member.setBirthDate("1991-01-01");
		member.setPhoneNumber("010-1234-5678");
		member.setMemberType("MBR");
		memberDAO.addNewMember(member);
		
		boolean isSuccess = memberBiz.massiveDeleteMember(member.getId());
		assertTrue(isSuccess);
	}
	
	/**
	 * getMemberDetailById
	 * doWriteMemberDetailInfo
	 * getSysdate
	 * getPersonalInfoIdSeq
	 * getTargetMemberEmail
	 */
	@Test
	public void getTargetMemberEmailTest() {
		String id = "test01";
		String getTargetMemberEmail  = memberBiz.getTargetMemberEmail(id);
		assertNotNull(getTargetMemberEmail);
		assertEquals(getTargetMemberEmail, "hihelloho@nate.com");
	}
	
	@Test
	public void getSysdateTest() {
		String getSysdate  = memberBiz.getSysdate();
		assertNotNull(getSysdate);
	}
	
	@Test
	public void getPersonalInfoIdSeqTest() {
		int getPersonalInfoIdSeq = memberBiz.getPersonalInfoIdSeq();
		assertNotNull(getPersonalInfoIdSeq);
		assertTrue(getPersonalInfoIdSeq > 0);
	}
	
	@Test
	public void getMemberDetailByIdTest() {
		String id = "test01";
		MemberVO memberVO = memberBiz.getMemberDetailById(id);
		assertNotNull(memberVO);
	}
	
	@Test
	public void doWriteMemberDetailInfoTest() {
		PersonalInfoReadVO personalInfoReadVO = new PersonalInfoReadVO();
		personalInfoReadVO.setId("testJunitDAO");
		personalInfoReadVO.setMemberId("junitTest");
		personalInfoReadVO.setTargetMemberId("test01");
		personalInfoReadVO.setDescription("desc");
		personalInfoReadVO.setReadDate("Junitdate");
		
		boolean doWriteMemberDetailInfo = memberBiz.doWriteMemberDetailInfo(personalInfoReadVO);
		assertNotNull(doWriteMemberDetailInfo);
		assertTrue(doWriteMemberDetailInfo);
		
		memberDAO.deleteMemberDetailInfo(personalInfoReadVO.getMemberId());
	}
	
	@Test
	public void getMemberTypesTest() {
		List<MemberTypeVO> memberTypes = memberBiz.getMemberTypes();
		
		assertNotNull(memberTypes);
		assertTrue(memberTypes.size() > 0);
	}
	
	@Test
	public void getHighestEducationLevelsTest() {
		List<HighestEducationLevelVO> highestEducationLevels = memberBiz.getHighestEducationLevels();
		
		assertNotNull(highestEducationLevels);
		assertTrue(highestEducationLevels.size() > 0);
	}
	
	@Test
	public void getGraduationTypesTest() {
		List<GraduationTypeVO> graduationTypes = memberBiz.getGraduationTypes();
		
		assertNotNull(graduationTypes);
		assertTrue(graduationTypes.size() > 0);
	}
	
	@Test
	public void getOneMemberTest(){
		String memberId = "test04";
		assertNotNull(memberBiz.getOneMember(memberId));
	}
	
	@Test
	public void isVerifyIdTest() {
		String id = "test001";
		
		boolean isSuccess = memberBiz.isVerifyId(id);
		
		assertTrue(isSuccess);
		
	}

}
