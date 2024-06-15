package application;

import java.util.List;

import DB.DbConnect;
import model.Department;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class Program {
	public static void main(String [] args) {
		
		DepartmentDao dep = DaoFactory.createDepartmentDao();
		
		Department dep1 =  new Department(null, "RH");
		dep.insert(dep1);
		
		List<Department> depart = dep.findAll();
		
		for (Department d : depart) {
			System.out.println(d.toString());
		}
		
		Department dep0 = dep.findById(1);
		
		System.out.println(dep0.toString());
		
		DbConnect.closeConnection();
	}
}
