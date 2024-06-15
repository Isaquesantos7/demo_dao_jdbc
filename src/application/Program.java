package application;

import java.util.List;

import model.Department;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class Program {
	public static void main(String [] args) {
		
		DepartmentDao dep = DaoFactory.createDepartmentDao();
		
		List<Department> depart = dep.findAll();
		
		for (Department d : depart) {
			System.out.println(d.toString());
		}
	
	}
}
