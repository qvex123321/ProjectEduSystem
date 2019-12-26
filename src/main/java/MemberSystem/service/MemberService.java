package MemberSystem.service;

import javax.servlet.http.Part;

import _util.model.MembersBean;

public interface MemberService {
	boolean idExists(String id);
	int saveMember(MembersBean mb);
	int updateMember(MembersBean mb);
	MembersBean queryMember(String id);
	MembersBean checkIdPassword(String userId, String password) ;
	int saveSingleMember(MembersBean mb);
	int updatePwd(String acct, String oldPwd, String newPwd);
	Integer getPeriodNumberTag(String classPeriod);
	Integer getSeatNo(String classPeriod);
	boolean saveMemberByCSV(Part part);
}
