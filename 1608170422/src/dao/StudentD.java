package dao;

import vo.Student;
import vo.User;

import java.sql.*;
import java.util.ArrayList;

public class StudentD {

    private Connection conn = null;

    public Student checkAccount(String user, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from student where id = '" + user + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        Student stu = getStudent(rs);
        closeConnection();
        return stu;
    }

    public Student findWithId(String id) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from student where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Student stu = getStudent(rs);
        closeConnection();
        return stu;
    }

    public ArrayList<User> findWithName(String usernama) throws Exception{
        ArrayList<User> al = new ArrayList<>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from user where username = '" + usernama + "'";
        ResultSet rs = stat.executeQuery(sql);
        getMoreStudent(al, rs);
        closeConnection();
        return al;
    }

    public boolean insertStudent(String id, String name, String sex, String school_date, String major,String content) throws Exception{
        initConnection();
        String sql = "insert into student(id, name, sex, school_date, major,content) values(?, ?, ?, ?, ?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, name);
        ps.setString(3, sex);
        ps.setString(4, school_date);
        ps.setString(5, major);
        ps.setString(6, content);
        int i = ps.executeUpdate();
        closeConnection();
        return i == 1;
    }

    public boolean deleteStudent(String id) throws Exception{

        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from user where id='"+id+"'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i==1;
    }

    public ArrayList<User> getOnePage(int page, int size) throws Exception{
        ArrayList<User> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM user limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page-1)*size);
        ps.setInt(2, size);
        ResultSet rs =  ps.executeQuery();
        getMoreStudent(al, rs); 
        closeConnection();
        return al;
    }

    public int getStudentCount() throws Exception{
        initConnection();
        String sql = "select count(*) from user";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    public void updateStudentInfo(String id, String name, String sex, String major,String content) throws Exception{

        initConnection();
        String sql = "update student set name=?, sex=?, major=?,content=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, sex);
        ps.setString(3, major);
        ps.setString(4, content);//备忘录内容
        ps.setString(5,id );//根据ID进行更新
        ps.executeUpdate();
        closeConnection();
    }

    public void updateStudentSecurity(String id, String email, String password) throws Exception{

        initConnection();
        String sql = "update student set password=?, email=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, email);
        ps.setString(3, id);
        ps.executeUpdate();
        closeConnection();
    }

    private Student getStudent(ResultSet rs) throws SQLException {
        Student stu = null;
        if (rs.next()){
            stu = new Student();
            stu.setId(rs.getString("id"));
            stu.setPassword(rs.getString("password"));
            stu.setName(rs.getString("name"));
            stu.setSex(rs.getString("sex"));
            stu.setSchool_date(rs.getString("school_date"));
            stu.setMajor(rs.getString("major"));
            stu.setEmail(rs.getString("email"));
        }
        return stu;
    }

    private void getMoreStudent(ArrayList<User> al, ResultSet rs) throws SQLException {
        while (rs.next()){
            User stu = new User();
            stu.setId(rs.getInt("id"));
            stu.setPassword(rs.getString("password"));
            stu.setUsername(rs.getString("username")); 
			/*
			 * stu.setSex(rs.getString("sex"));
			 * stu.setSchool_date(rs.getString("school_date"));
			 * stu.setMajor(rs.getString("major")); stu.setEmail(rs.getString("email"));
			 * stu.setContent(rs.getString("content"));
			 */
            al.add(stu);
        }
    }

    private void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yinhaoyan?useSSL=false", "root", "123456");
    }

    private void closeConnection() throws Exception{
        conn.close();
    }
}
