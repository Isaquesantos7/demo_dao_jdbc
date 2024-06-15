package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DB.DbConnect;
import DB.DbException;
import model.Department;
import model.Seller;
import model.dao.SellerDao;

public class SellerDaoJDBC implements SellerDao {

	private Connection connection;
	
	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		
		try {
			st = this.connection.prepareStatement("INSERT INTO tb_seller(name, email, birthDate, baseSalary, departmentId) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffect = st.executeUpdate();
			
			if (rowsAffect > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}				
				DbConnect.closeResultSet(rs);
			}
			
		} catch (SQLException e) {
		
			throw new DbException("Error: " +  e.getMessage());
		} finally {
			DbConnect.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = this.connection.prepareStatement("SELECT seller.id, seller.name, seller.email, seller.birthDate, seller.baseSalary, seller.departmentId, dep.name as departmentName FROM tb_seller AS seller LEFT JOIN tb_department dep ON seller.departmentId = dep.id ORDER BY seller.name;");
			rs = st.executeQuery();
			
			List<Seller> listSeller = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("departmentId"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("departmentId"), dep);
					
				}
				
				Seller seller = instantiateSeller(rs, dep);
				listSeller.add(seller);
			}
			
			return listSeller;
		} catch (SQLException e) {
			
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DbConnect.closeStatement(st);
			DbConnect.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("departmentId"));
		seller.setName(rs.getString("name"));
		seller.setEmail(rs.getString("email"));
		seller.setBirthDate(rs.getDate("birthDate"));
		seller.setBaseSalary(rs.getDouble("baseSalary"));
		seller.setDepartment(dep);
		
		return seller;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("departmentId"));
		dep.setName(rs.getString("departmentName"));
		
		return dep;
	}
}
