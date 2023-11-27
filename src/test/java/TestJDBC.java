import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pratice.jdbc.mapper.StudentMapper;
import pratice.jdbc.pojo.Student;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class TestJDBC {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这一行后面参数true是指自动提交事务，如果不写true的话，那么后期执行dml语句后要提交事务即sqlsession.commit
        SqlSession sqlSession=sqlSessionFactory.openSession(true);


        //获取代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println("==================学生作业提交系统===================");
        System.out.println("功能：");
        System.out.println("1.注册账号");
        System.out.println("2.查询账号");
        System.out.println("3.修改账号");
        System.out.println("4.删除账号");
        System.out.println("5.退出系统");
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入您要选择的功能：");
        int choose = sc.nextInt();
        while (choose != 5) {
            switch (choose) {
                case 1:
                    insertStudent(studentMapper, sc);
                    break;
                case 2:
                    selectStudents( studentMapper, sc);
                    break;
                case 3:
                    modify(studentMapper, sc);
                    break;
                case 4:
                    delectStudent(studentMapper, sc);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("您输入的信息有误");
            }
            System.out.println("1.注册账号");
            System.out.println("2.查询账号");
            System.out.println("3.修改账号");
            System.out.println("4.删除账号");
            System.out.println("5.退出系统");
            System.out.print("请输入您要选择的功能：");
            choose = sc.nextInt();
        }
        sqlSession.close();
    }
    //删除账号
    private static void delectStudent(StudentMapper studentMapper, Scanner sc) {
        System.out.print("请输入要删除的账号：");
        int idNumber=sc.nextInt();
        //删除成功返回执行成功的语句数目
        int count = studentMapper.deleteByIdNumber(idNumber);
        if(count>0){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
    }
    //修改账号
    private static void modify(StudentMapper studentMapper, Scanner sc) {
        System.out.println("-----------------------修改功能-------------------------------");
        System.out.println("请输入要修改的账号");
        int idNumber=sc.nextInt();
        Student student = studentMapper.selectStudentsByidNumber(idNumber);
        if(student==null){
            System.out.println("该账号不存在！");
        }else {
            Student students1=new Student();
            students1.setIdNumber(idNumber);
            System.out.print("请输入新的班级代码：");
            int newclassNumber=sc.nextInt();
            students1.setClassNumber(newclassNumber);
            System.out.print("请输入新的姓名：");
            String newName=sc.next();
            students1.setName(newName);
            System.out.print("请输入新的密码：");
            int password=sc.nextInt();
            System.out.print("请再次输入新的密码：");
            int newPassword=sc.nextInt();
            while (password!=newPassword){
                System.out.println("两次密码输入不一致，请重新输入！");
                System.out.print("请重新输入新的密码：");
                password=sc.nextInt();
                System.out.print("请再次重新输入新的密码：");
                newPassword=sc.nextInt();
            }
            students1.setPassword(newPassword);
            int count = studentMapper.modifyStudents(students1);
            if(count>0){
                System.out.println("修改成功！");
            }else {
                System.out.println("修改失败！");
            }
        }

    }
    //查询账号
    private static void selectStudents(StudentMapper studentsMapper, Scanner sc) {

        //根据单条件查询
        System.out.println("-----------------------------查询功能-------------------------------");
        System.out.println("1.根据班级查询");
        System.out.println("2.根据学号查询");
        System.out.println("3.根据姓名查询");
        System.out.println("4.查询所有账号");
        System.out.print("请输入您的选择:");
        Student students1 = new Student();
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                System.out.println("您选择根据班级查询：");
                System.out.print("请输入班级代码：");
                int classNumber = sc.nextInt();
                students1.setClassNumber(classNumber);
            {
                List<Student> studentsList = studentsMapper.selectByConditionSingle(students1);
                System.out.println(studentsList);
                break;
            }
            case 2:
                System.out.println("您选择根据学号查询：");
                System.out.print("请输入学生学号：：");
                int idNumber = sc.nextInt();
                students1.setIdNumber(idNumber);
            {
                List<Student> studentsList = studentsMapper.selectByConditionSingle(students1);
                System.out.println(studentsList);
                break;
            }
            case 3:
                System.out.println("您选择根据姓名查询：");
                System.out.print("请输入学生姓名：");
                String name = sc.next();
                students1.setName(name);
            {
                List<Student> studentsList = studentsMapper.selectByConditionSingle(students1);
                System.out.println(studentsList);
                break;
            }
            case 4:
            {
                List<Student> studentsList = studentsMapper.selectAll();
                System.out.println(studentsList);
            }
            default:
                System.out.println("您输入的信息有误");
        }
    }
    //注册账号
    private static void insertStudent(StudentMapper studentMapper, Scanner sc) {
        System.out.println("-----------------------注册添加功能-------------------------------");
        Student student1 = new Student();
        System.out.print("请输入班级代码：");
        int classNumber = sc.nextInt();
        student1.setClassNumber(classNumber);
        System.out.print("请输入学号：");
        int idNumber = sc.nextInt();
        Student students = studentMapper.selectStudentsByidNumber(idNumber);
        if (students != null) {
            System.out.println("当前学号已经存在，不能重复添加！");
            //System.exit(0);
        } else {
            student1.setIdNumber(idNumber);
            System.out.print("请输入姓名：");
            String name = sc.next();
            student1.setName(name);
            System.out.print("请设置密码：");
            int password = sc.nextInt();
            System.out.print("请确认密码：");
            int newpassword = sc.nextInt();
            while (password != newpassword) {
                System.out.println("您输入的两次密码不一致，请重新设置密码！");
                System.out.print("请设置密码：");
                password = sc.nextInt();
                System.out.print("请确认密码：");
                newpassword = sc.nextInt();
            }
            student1.setPassword(newpassword);
            int count2 = studentMapper.insertStudent(student1);
            if (count2 > 0) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }
        }
    }
}
