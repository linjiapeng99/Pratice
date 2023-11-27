package pratice.jdbc.pojo;

public class Student {
    private Integer classNumber;
    private Integer idNumber;
    private String name;
    private Integer password;

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Students{" +
                "classNumber=" + classNumber +
                ", idNumber=" + idNumber +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}'+"\n";
    }
}
