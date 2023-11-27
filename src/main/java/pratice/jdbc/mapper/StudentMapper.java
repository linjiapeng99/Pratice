package pratice.jdbc.mapper;

import pratice.jdbc.pojo.Student;

import java.util.List;

public interface StudentMapper {
    //查询所有
    List<Student> selectAll();
    //根据所有查询
    List<Student> selectAllCondition(Student students);
    //多选一条件查询
    List<Student>selectByConditionSingle(Student students);
    //添加注册账号
    int insertStudent(Student students);
    //根据学号查询
    Student selectStudentsByidNumber(int idNumber);
    //修改账号信息
    int modifyStudents(Student students);
    //删除账号
    int deleteByIdNumber(int idNumber);
}
