package MemberSystem.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import MemberSystem.dao.MemberDao;
import MemberSystem.dao.impl.MemberDaoImpl;
import MemberSystem.service.MemberService;
import _util.model.AdminStaffBean;
import _util.model.MembersBean;
import _util.model.StudentBean;
import _util.model.TeacherBean;

public class MemberServiceImpl implements MemberService {
	MemberDao dao;
	private Connection conn = null;

//	public MemberServiceImpl() {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/EduSystemDB");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new RuntimeException(ex.getMessage());
//		}
//		this.dao = new MemberDaoImpl(conn);
//	}
	
	public void getConn() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/EduSystemDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
			
	}

	@Override
	public int saveSingleMember(MembersBean mb) {
		getConn();
		dao  = new MemberDaoImpl(conn);
		int insertMember = dao.saveSingleMember(mb);
		// 取得memberId
		int memberId = dao.searchMemberId(mb.getAccountName());
		int memberType = mb.getPrivilegeId();
		System.out.println(memberType);
		switch (memberType) {
		case 1:
			StudentBean sb = (StudentBean)mb;
			System.out.println("SeatNo: "+sb.getSeatNo()); 
			dao.insertStudent((StudentBean) mb, memberId);
			break;
		case 2:
			dao.insertTeacher((TeacherBean) mb, memberId);
			break;
		case 3:
			dao.insertAdminStaff((AdminStaffBean) mb, memberId);
			break;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertMember;
	}

	// 這顆到底用在哪裡= =
	@Override
	public int saveMember(MembersBean mb) {
		getConn();
		dao  = new MemberDaoImpl(conn);
		int n = dao.saveMember(mb);
		// 取得memberId
//		int memberId = dao.searchMemberId(mb.getAccountName());
//		int memberType = mb.getPrivilegeId();
//
//		switch (memberType) {
//		case 1:
//			dao.insertStudent((StudentBean) mb, memberId);
//			break;
//		case 2:
//			dao.insertTeacher((TeacherBean) mb, memberId);
//			break;
//		case 3:
//			dao.insertAdminStaff((AdminStaffBean) mb, memberId);
//			break;
//		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public boolean idExists(String id) {
		getConn();
		dao  = new MemberDaoImpl(conn);
		boolean isExists= dao.idExists(id);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExists;
	}

	@Override
	public MembersBean queryMember(String id) {
		getConn();
		dao  = new MemberDaoImpl(conn);
		MembersBean member = dao.queryMember(id);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public MembersBean checkIdPassword(String accountName, String password) {
		getConn();
		dao = new MemberDaoImpl(conn);
		MembersBean mb = dao.checkIdPassword(accountName, password);
		
		// 取得身份
		mb = dao.getIdentityBean(mb, mb.getPrivilegeId());
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mb;
	}

	@Override
	public int updateMember(MembersBean mb) {
		getConn();
		dao  = new MemberDaoImpl(conn);
		int status = 0;
		status = dao.updateMember(mb);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int updatePwd(String acct, String oldPwd, String newPwd) {
		return 0;
	}
	
	@Override
	public Integer getSeatNo(String classPeriod) {
		getConn();
		dao  = new MemberDaoImpl(conn);
		Integer seatNo = dao.getSeatNo(classPeriod);
		return seatNo;
	}

	@Override
	public Integer getPeriodNumberTag(String classPeriod) {
		getConn();
		dao  = new MemberDaoImpl(conn);
		int tag=dao.getPeriodNumberTag(classPeriod);
		System.out.println(tag);
		return tag;
	}

	

}
