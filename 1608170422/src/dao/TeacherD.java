package dao;

import vo.User;

import java.sql.*;


public class TeacherD {
    private Connection conn = null;

    public User checkAccount(String id, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from user where username = '" + id + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        User tea = getTeacher(rs);
        closeConnection();
        return tea;
    }

    public User findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from user where username = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        User tea = getTeacher(rs);
        closeConnection();
        return tea;
    }

    public User insertTeacher(String username, String password) throws Exception {
        initConnection();
        String sql = "insert into user(username, password) values(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        //ps.setString(3, email);
        ps.executeUpdate();
        User teacher = findWithId(username);
        closeConnection();
        return teacher;
    }

    public User updateTeacher(String id, String username, String password) throws Exception{

        initConnection();
        String sql = "update user set username=?,password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, id);
        ps.executeUpdate();
        User teacher = findWithId(id);
        closeConnection();
        return teacher;
    }

    public void updateTeacherPassword(String id, String password) throws Exception{

        initConnection();
        String sql = "update user set password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, id);
        ps.executeUpdate();
        closeConnection();
    }

    private User getTeacher(ResultSet rs) throws SQLException {
        User tea = null;
        if (rs.next()) {
            tea = new User();
			  tea.setUsername(rs.getString("username"));
			  tea.setPassword(rs.getString("password"));			 
        }
        return tea;
    }

    private void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yinhaoyan?useSSL=false", "root", "123456");
    }

    private void closeConnection() throws Exception {
        conn.close();
    }
}
