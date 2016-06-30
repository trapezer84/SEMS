package com.ktds.sems.member.biz;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberBizTest extends SemsTestCase {

	@Autowired
	private MemberBiz memberBiz;

	@Before
	public void setUp() {
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				CodeMngVO codeMngVO = new CodeMngVO();
				codeMngVO.setCdId("TEST");
				codeMngVO.setCdNm("TEST");
				codeMngVO.setCdTp("TEST");
				codeMngVO.setCdTp2("TEST");
				memberBiz.doCodeMngInsert(codeMngVO);
				
				MbrTpVO mbrTpVO = new MbrTpVO();
				mbrTpVO.setCdId("TEST");
				mbrTpVO.setCdNm("JUNITTEST");
				memberBiz.doInsertMbrTp(mbrTpVO);
			}
		});
	}
	
	@After
	public void tearDown() {
		
		testHelper(new Testable() {
			
			@Override
			public void preparedTest() {
				memberBiz.doCodeMngDelete("TEST");
				memberBiz.doMbrTpDelete("TEST");
			}
		});
	}
	
	/**
	 * 계정 잠겨있는지 확인
	 * 
	 */
	@Test
	public void isAccountLockTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.isAccountLock(id);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인
	 * 
	 */
	@Test
	public void loginTest() {

		MockHttpSession session = new MockHttpSession();

		MemberVO memberVO = new MemberVO();
		memberVO.setId("cocomo12");
		memberVO.setPassword("123qwe!@#qwe");

		boolean isSuccess = memberBiz.login(session, memberVO);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인 성공 확인
	 * 
	 */
	@Test
	public void loginSuccessTest() {

		String id = "cocomo12";
		boolean isSuccess = memberBiz.loginSuccess(id);
		assertTrue(isSuccess);
	}

	/**
	 * 로그인 실패 횟수 증가
	 * 
	 */
	@Test
	public void plusLoginFailCountTest() {

		String id = "cocomo12";
		boolean isSuccess = memberBiz.plusLoginFailCount(id);
		assertTrue(isSuccess);
	}

	/**
	 * 계정 잠금상태 최신화
	 * 
	 */
	@Test
	public void updateAccountLockTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.updateAccountLock(id);
		assertTrue(isSuccess);
	}

	/**
	 * 30일이 지나서 비밀번호 바꿔야 하는지 확인
	 * 
	 */
	@Test
	public void needToChangPasswordTest() {

		String id = "cocomo12";
		boolean isSuccess = !memberBiz.needToChangPassword(id);
		assertTrue(isSuccess);
	}

	/**
	 * 존재하는 아이디 인지 확인
	 * 
	 */
	@Test
	public void isExistIdTest() {
		String id = "cocomo12";
		boolean isSuccess = !memberBiz.isExistId(id);
		assertTrue(isSuccess);
	}

	/**
	 * 탈퇴한 회원인지 확인
	 * 
	 */
	@Test
	public void isResignTest() {
		String id = "cocomo12";
		boolean isSuccess = !memberBiz.isResign(id);
		assertTrue(isSuccess);
	}
	
	
	@Test
	public void getAllGrdtListTest(){
		List<GrdtTpVO> grdtTpList = memberBiz.getAllGrtdList();
		assertNotNull(grdtTpList);
		assertTrue(grdtTpList.size() >= 0);
	}
	
	@Test
	public void doGrdtInsertTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("GIT8");
		grdtTpVO.setCdNm("GIT8");
		
		int checkGrdtInsert = memberBiz.doGrdtInsert(grdtTpVO);
		assertTrue(checkGrdtInsert >= 1);
	}
	
	@Test
	public void doGrdtInsertTest2(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TES2");
		grdtTpVO.setCdNm("휴학");
		
		int checkGrdtInsert = memberBiz.doGrdtInsert(grdtTpVO);
		assertFalse(checkGrdtInsert == 0);
	}
	
	@Test
	public void doGrdtModifyTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TEST");
		grdtTpVO.setCdNm("JUNITTEST");
		
		int checkGrdtModify = memberBiz.doGrdtModify(grdtTpVO);
		assertTrue(checkGrdtModify >= 1);
	}
	
	@Test
	public void doGrdtDeleteTest(){
		String cdId = "TEST";
		int checGrdtkDelete = memberBiz.doGrdtDelete(cdId);
		assertTrue(checGrdtkDelete >= 1);
	}
	
	@Test
	public void isExistDataTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("ABST");
		grdtTpVO.setCdNm("휴학");
		
		int checkExistData = memberBiz.isExistData(grdtTpVO);
		assertTrue(checkExistData > 0);
	}
	
	@Test
	public void isExistCdNmDataTest(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdNm("휴학");
		
		int checkExistCdNmData = memberBiz.isExistCdNmData(grdtTpVO);
		assertTrue(checkExistCdNmData > 0);
	}
	
	@Test
	public void isExistCdNmDataTest2(){
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdNm("실패");
		
		int checkExistCdNmData = memberBiz.isExistCdNmData(grdtTpVO);
		assertTrue(checkExistCdNmData == 0);
	}
	
	@Test
	public void getAllMbrTpListTest(){
		List<MbrTpVO> mbrTpList = memberBiz.getAllMbrTpList();
		assertNotNull(mbrTpList);
		assertTrue(mbrTpList.size() >= 0);
	}
	
	@Test
	public void doInsertMbrTpTest(){
		
		tearDown();
		
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("TEST");
		mbrTpVO.setCdNm("JUNITTEST");
		
		int checkMbrInsert = memberBiz.doInsertMbrTp(mbrTpVO);
		assertTrue(checkMbrInsert >= 1);
	}
	
	@Test
	public void doMbrTpModifyTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("STD");
		mbrTpVO.setCdNm("수강생2");
		
		int checkMbrTpModify = memberBiz.doMbrTpModify(mbrTpVO);
		assertTrue(checkMbrTpModify >= 1);
		
		mbrTpVO.setCdId("STD");
		mbrTpVO.setCdNm("수강생");
		memberBiz.doMbrTpModify(mbrTpVO);
	}
	
	@Test
	public void doMbrTpDeleteTest(){
		String cdId = "TEST";
		int checkMbrTpDelete = memberBiz.doMbrTpDelete(cdId);
		assertTrue(checkMbrTpDelete > 0);
	}
	
	@Test
	public void isExistMbrTpDataTest(){
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("STD");
		mbrTpVO.setCdNm("수강생");
		
		int checkExistData = memberBiz.isExistMbrTpData(mbrTpVO);
		assertTrue(checkExistData >= 1);
	}
	
	/**
	 * getAllCodeMngListTest
	 */
	@Test
	public void getAllCodeMngListTest() {

		List<CodeMngVO> codeMngList = memberBiz.getAllCodeMngList();
		
		if( codeMngList != null ) {
			for (CodeMngVO codeMngVO : codeMngList) {
				assertNotNull(codeMngVO.getCdId());
				assertNotNull(codeMngVO.getCdNm());
				assertNotNull(codeMngVO.getCdTp());
				assertNotNull(codeMngVO.getCdTp2());
			}
		} else {
			fail("fail");
		}
	}

	@Test
	public void doCodeMngInsertTest() {
		
		CodeMngVO codeMngVO = new CodeMngVO();
		codeMngVO.setCdId("TES2");
		codeMngVO.setCdNm("TES2");
		codeMngVO.setCdTp("TES2");
		codeMngVO.setCdTp2("TES2");
		
		boolean isSuccess = memberBiz.doCodeMngInsert(codeMngVO);
		assertTrue(isSuccess);
	}
	
	@Test
	public void doCodeMngModifyTest() {
		
		CodeMngVO codeMngVO = new CodeMngVO();
		codeMngVO.setCdId("TEST");
		codeMngVO.setCdNm("TEST2");
		codeMngVO.setCdTp("TEST2");
		codeMngVO.setCdTp2("TEST2");
		
		boolean isSuccess = memberBiz.doCodeMngModify(codeMngVO);
		assertTrue(isSuccess);
	}
	
	@Test
	public void doCodeMngDeleteTest() {
		
		String cdId = "TEST";
		boolean isSuccess = memberBiz.doCodeMngDelete(cdId);
		assertTrue(isSuccess);
	}
	
	@Test
	public void getAllHighestEduListTest() {
		List<HighestEduTpVO> testList = memberBiz.getAllHighestEduList();
		if(testList != null) {
			for (HighestEduTpVO highestEduTpVO : testList) {
				assertNotNull(highestEduTpVO.getCdId());
				assertNotNull(highestEduTpVO.getCdNm());
			}
		} else {
			fail("fail");
		}
	}

	@Test
	public void doHighestEduDeleteTest() {
		String cdId = "TEST";
		boolean isSuccess = memberBiz.doHighestEduDelete(cdId);
		assertTrue(isSuccess);
	}

	@Test
	public void doHighestEduModifyTest() {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId("TEST");
		highestEduTpVO.setCdNm("TEST2");
		
		boolean isSuccess = memberBiz.doHighestEduModify(highestEduTpVO);
		assertTrue(isSuccess);
	}

	@Test
	public void isExistHighestEduDataTest() {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId("TBWX");
		highestEduTpVO.setCdNm("TYXE");
		
		int executeQuery = memberBiz.isExistHighestEduData(highestEduTpVO);
		assertTrue(executeQuery < 1);
	}
	
	@Test
	public void isExistHighestEduDataErrorTest() {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId("TEST");
		highestEduTpVO.setCdNm("TEST");
		
		int executeQuery = memberBiz.isExistHighestEduData(highestEduTpVO);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void doHighestEduInsertTest() {
		
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId("X2X2");
		highestEduTpVO.setCdNm("X3X3");
		
		int executeQuery = memberBiz.doHighestEduInsert(highestEduTpVO);
		assertTrue(executeQuery > 0);
	}
}
