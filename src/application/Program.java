package application;

import java.util.List;

import DB.DbConnect;
import model.Department;
import model.Seller;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;

public class Program {
	public static void main(String [] args) {
		
		//DepartmentDao dep = DaoFactory.createDepartmentDao();
		
		//Department dep1 =  new Department(null, "Business");
		//dep.insert(dep1);
		
		//dep.deleteById(4);
		
		//List<Department> depart = dep.findAll();
		
		//for (Department d : depart) {
		//	System.out.println(d.toString());
		//}
		
		//Department dep0 = dep.findById(1);		
		//System.out.println(dep0.toString());
		
		SellerDao seller = DaoFactory.createSellerDao();
		
		List<Seller> listSeller = seller.findAll();
		
		for (Seller s: listSeller) {
			System.out.println(s);
		}
		
		DbConnect.closeConnection();
	}
}
