package com.ktds.sems.member.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;
import com.ktds.sems.Testable;
import com.ktds.sems.member.vo.CodeMngVO;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.MemberVO;

@Transactional
public class MemberDAOTest extends SemsTestCase {

	@Autowired
	private MemberDAO memberDAO;

	@Before
	public void setUp() {
		
		testHelper(new Testable() {

			@Override
			public void preparedTest() {
				CodeMngVO codeMngVO = new CodeMngVO();
				codeMngVO.setCdId("TEST_CM2");
				codeMngVO.setCdNm("테스트");
				codeMngVO.setCdTp("TE");
				codeMngVO.setCdTp2("ST");
				memberDAO.doCodeMngInsert(codeMngVO);
				
				MbrTpVO mbrTpVO = new MbrTpVO();
				mbrTpVO.setCdId("TEST");
				mbrTpVO.setCdNm("JUNITTEST");
				memberDAO.doInsertMbrTp(mbrTpVO);
			}
		});
	}

	@After
	public void tearDown() {
		testHelper(new Testable() {

			@Override
			public void preparedTest() {
				memberDAO.doCodeMngDelete("TEST_CM2");
				memberDAO.doMbrTpDelete("TEST");
			}
		});
	}

	/**
	 * SALT 얻어오기
	 */
	@Test
	public void getSaltByIdTest() {
		
		
		
		String id = "cocomo12";
		String salt = memberDAO.getSaltById(id);
		assertNotNull(salt);
	}

	/**
	 * 로그인
	 */
	@Test
	public void loginTest() {
		MemberVO loginVO = new MemberVO();
		loginVO.setId("cocomo12");
		loginVO.setPassword("afd547fcea7810b7fb5512c0eff23922b5a0cdfa0c731c4447e7d9af31f7e955");

		MemberVO memberVO = memberDAO.login(loginVO);
		if (memberVO != null) {
			assertNotNull(memberVO.getId());
			assertNotNull(memberVO.getPassword());
			assertNotNull(memberVO.getName());
			assertNotNull(memberVO.getEmail());
			assertNotNull(memberVO.getBirthDate());
			assertNotNull(memberVO.getPhoneNumber());
			assertNotNull(memberVO.getMemberType());
			assertNotNull(memberVO.getSalt());
			assertNotNull(memberVO.getLoginFailCount());
			assertNotNull(memberVO.getIsResign());
			assertNotNull(memberVO.getModifyFailCount());
			assertNotNull(memberVO.getIsModifyLock());
		} else {
			fail("fail");
		}
	}

	/**
	 * 계정 잠겼는지 확인
	 */
	@Test
	public void isAccountLockTest() {
		String id = "cocomo12";
		boolean isSuccess = memberDAO.isAccountLock(id);
		assertTrue(!isSuccess);
	}

	/**
	 * 로그인 성공 확인
	 */
	@Test
	public void loginSuccessTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.loginSuccess(id);
		assertTrue(executeQuery > 0);
	}

	/**
	 * 로그인 실패 횟수 증가
	 */
	@Test
	public void plusLoginFailCountTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.plusLoginFailCount(id);
		assertTrue(executeQuery > 0);
	}

	/**
	 * 계정 잠금 상태 최신화
	 */
	@Test
	public void updateAccountLockTest() {
		String id = "cocomo12";
		int executeQuery = memberDAO.updateAccountLock(id);
		assertTrue(executeQuery == 0);
	}

	/**
	 * 로그인한지 30일 지났는지 확인
	 */
	@Test
	public void needToChangPasswordTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.needToChangPassword(id);
		assertNull(checkStr);
	}

	/**
	 * 존재하는 아이디인지 확인
	 */
	@Test
	public void isExistIdTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.isExistId(id);
		assertNotNull(checkStr);
	}

	/**
	 * 탈퇴한 회원인지 확인
	 */
	@Test
	public void isResignTest() {
		String id = "cocomo12";
		String checkStr = memberDAO.isResign(id);
		assertNull(checkStr);
	}

	@Test
	public void getAllGrdtListTest() {
		List<GrdtTpVO> grdtTpList = memberDAO.getAllGrtdList();
		assertNotNull(grdtTpList);
		assertTrue(grdtTpList.size() >= 0);
	}

	@Test
	public void doGrdtInsertTest() {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("T2T2");
		grdtTpVO.setCdNm("JUNT");

		int checkGrdtInsert = memberDAO.doGrdtInsert(grdtTpVO);
		assertTrue(checkGrdtInsert > 0);
	}

	@Test
	public void doGrdtModifyTest() {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("TEST");
		grdtTpVO.setCdNm("JUNITTEST");

		int checkGrdtModify = memberDAO.doGrdtModify(grdtTpVO);
		assertTrue(checkGrdtModify >= 1);
	}

	@Test
	public void doGrdtDeleteTest() {
		String cdId = "TEST";
		int checGrdtkDelete = memberDAO.doGrdtDelete(cdId);
		assertTrue(checGrdtkDelete >= 1);
	}

	@Test
	public void isExistDataTest() {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId("ABST");
		grdtTpVO.setCdNm("휴학");

		int checkExistData = memberDAO.isExistData(grdtTpVO);
		assertTrue(checkExistData >= 1);
	}

	@Test
	public void isExistCdNmDataTest() {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdNm("휴학");

		int checkExistCdNmData = memberDAO.isExistCdNmData(grdtTpVO);
		assertTrue(checkExistCdNmData >= 1);
	}

	@Test
	public void getAllMbrTpListTest() {
		List<MbrTpVO> mbrTpList = memberDAO.getAllMbrTpList();
		assertNotNull(mbrTpList);
		assertTrue(mbrTpList.size() >= 0);
	}

	@Test
	public void doInsertMbrTpTest() {
		
		tearDown();
		
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("TEST");
		mbrTpVO.setCdNm("JUNITTEST");

		int checkMbrTpInsert = memberDAO.doInsertMbrTp(mbrTpVO);
		assertTrue(checkMbrTpInsert >= 1);
	}

	@Test
	public void doMbrTpModifyTest() {
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("MBR");
		mbrTpVO.setCdNm("일반회");

		int checkMbrTpModify = memberDAO.doMbrTpModify(mbrTpVO);
		assertTrue(checkMbrTpModify >= 1);
		mbrTpVO.setCdId("MBR");
		mbrTpVO.setCdNm("일반회원");
		memberDAO.doMbrTpModify(mbrTpVO);
	}

	@Test
	public void doMbrTpDeleteTest() {
		String cdId = "TEST";
		int checkMbrTpDelete = memberDAO.doMbrTpDelete(cdId);
		assertTrue(checkMbrTpDelete > 0);
	}

	@Test
	public void isExistMbrTpDataTest() {
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId("STD");
		mbrTpVO.setCdNm("수강생");

		int checkExistMbrTpData = memberDAO.isExistMbrTpData(mbrTpVO);
		assertTrue(checkExistMbrTpData >= 1);
	}

	@Test
	public void getAllCodeMngListTest() {
		List<CodeMngVO> codeMngList = memberDAO.getAllCodeMngList();
		if (codeMngList != null) {
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
	public void doCodeMng_InsertTest() {

		CodeMngVO codeMngVO = new CodeMngVO();
		codeMngVO.setCdId("TEST_CM3");
		codeMngVO.setCdNm("테스트");
		codeMngVO.setCdTp("TE");
		codeMngVO.setCdTp2("ST4");

		int testInt = memberDAO.doCodeMngInsert(codeMngVO);
		assertTrue(testInt > 0);
	}

	@Test
	public void doCodeMng_ModifyTest() {

		CodeMngVO codeMngVO = new CodeMngVO();
		codeMngVO.setCdId("TEST_CM2");
		codeMngVO.setCdNm("테스트");
		codeMngVO.setCdTp("TE");
		codeMngVO.setCdTp2("ST4");

		int testInt = memberDAO.doCodeMngModify(codeMngVO);
		assertTrue(testInt > 0);
	}

	@Test
	public void doCodeMng_DeleteTest() {
		String cdId = "TEST_CM2";

		int testInt = memberDAO.doCodeMngDelete(cdId);
		assertTrue(testInt > 0);
	}

	@Test
	public void getAllHighestEduListTest() {
		List<HighestEduTpVO> testList = memberDAO.getAllHighestEduList();
		if (testList != null) {
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
		int executeQuery = memberDAO.doHighestEduDelete(cdId);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void doHighestEduModifyTest() {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId("TEST");
		highestEduTpVO.setCdNm("TEST2");

		int executeQuery = memberDAO.doHighestEduModify(highestEduTpVO);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void isExistHighestEduDataTest() {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId("TBWX");
		highestEduTpVO.setCdNm("TYXE");

		int executeQuery = memberDAO.isExistHighestEduData(highestEduTpVO);
		assertTrue(executeQuery < 1);
	}

	@Test
	public void isExistHighestEduDataErrorTest() {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId("TEST");
		highestEduTpVO.setCdNm("TEST");

		int executeQuery = memberDAO.isExistHighestEduData(highestEduTpVO);
		assertTrue(executeQuery > 0);
	}

	@Test
	public void doHighestEduInsertTest() {
		
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId("X2X2");
		highestEduTpVO.setCdNm("X3X3");

		int executeQuery = memberDAO.doHighestEduInsert(highestEduTpVO);
		assertTrue(executeQuery > 0);
	}
}
