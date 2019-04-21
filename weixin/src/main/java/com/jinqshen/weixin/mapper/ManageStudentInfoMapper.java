package com.jinqshen.weixin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.StudentInfoCondition;

/**
 * 学生信息管理持久层
 * @author jinqshen
 *
 */
@Repository
public interface ManageStudentInfoMapper {

	/**
	 * 查询学生信息总数
	 * @return
	 */
	public Integer findCountOfAllStudentInfo();
	
	/**
	 * 分页查询学生信息
	 * @param currentPage 当前页
	 * @param pageSize 每页条数
	 * @return
	 */
	public List<StudentInfo> findStudentInfoByPageBean(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

	/**
	 * 根据学号删除学生信息
	 * @param student_number 学号
	 */
	public void deleteStudentInfoByStudentNumber(@Param("student_number") int student_number);

	/**
	 * 根据学号查询学生信息
	 * @param student_number 学号
	 */
	public StudentInfo findStudentInfoByStudentNumber(@Param("student_number") int student_number);

	/**
	 * 更新学生信息
	 * @param studentInfo 学生信息对象
	 */
	public void updateOneStudentInfoByEntity(StudentInfo studentInfo);

	/**
	 * 插入学生信息
	 * @param studentInfo 学生信息对象
	 * @return
	 */
	public int insertStudentInfo(StudentInfo studentInfo);

	/**
	 * 查询符合条件的学生信息数目
	 * @param student_number 学号
	 * @param name 姓名
	 * @param academy 所在学院
	 * @return
	 */
	public int findCountOfStudentInfoBySomeCondition(@Param("student_number")int student_number, @Param("name")String name, @Param("academy")String academy);

	/**
	 * 查询符合条件的学生信息
	 * @param i 起始条数
	 * @param pageSize 数据数目
	 * @param student_number 学号
	 * @param name 姓名
	 * @param academy 所在学院
	 * @return
	 */
	public List<StudentInfo> findStudentInfoBySomeCondition(@Param("currentPage")int i, @Param("pageSize")int pageSize,@Param("student_number")int student_number, @Param("name")String name, 
			@Param("academy")String academy);

	/**
	 * 查询符合高级条件查询的学生信息的数目
	 * @param highConditions
	 * @return
	 */
	public int findCountOfStudentInfoByHighCondition(List<HighCondition> highConditions);

	/**
	 * 查询符合高级条件查询的学生信息
	 * @param i
	 * @param pageSize
	 * @param highConditions
	 * @return
	 */
	public List<StudentInfo> findStudentInfoByHighCondition(@Param("currentPage") int i, @Param("pageSize") int pageSize,@Param("list") List<HighCondition> highConditions);
}
