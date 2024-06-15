package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConnect;
import DB.DbException;
import model.Department;
import model.dao.DepartmentDao;

public class DepartmentDaoJDBC implements DepartmentDao{
	private Connection connection;
	
	private PreparedStatement st;
	private ResultSet rs;
	
	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		try {
			st = this.connection.prepareStatement("SELECT * FROM tb_department WHERE id = ?;");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				
				return dep;
			}
		} catch (SQLException e) {
			
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DbConnect.closeConnection();
			DbConnect.closeStatement(st);
			DbConnect.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Department> findAll() {
		try {
			st = this.connection.prepareStatement("SELECT * FROM tb_department;");
			
			rs = st.executeQuery();
			
			List<Department> listDepartments = new ArrayList<Department>();
			
			while (rs.next()) {
				Department obj = new Department();
				
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				
				listDepartments.add(obj);
			}
			
			return listDepartments;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DbConnect.closeConnection();
			DbConnect.closeStatement(st);
			DbConnect.closeResultSet(rs);
		}
	}

}
