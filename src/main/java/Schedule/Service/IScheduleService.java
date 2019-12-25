package Schedule.Service;

public interface IScheduleService {

	
	String getCourseInfo(int courseId);
//	String getScheduleJSON(String MemberType, int Id) throws Exception;
	String getScheduleJSON(String MemberType, String Id) throws Exception;
	void testInsert();
//	void saveClassSchedule(String jsonString);
	void saveClassSchedule(String jsonString, String classPeriodId);
	
}