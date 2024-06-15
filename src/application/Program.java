package application;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import DB.DbConnect;
import model.Department;
import model.Seller;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;

public class Program {
	public static void main(String [] args) {
		Locale.setDefault(Locale.US);
		
		DepartmentDao dep = DaoFactory.createDepartmentDao();
		
		//Department dep1 =  new Department(null, "Business");
		//dep.insert(dep1);
		
		//dep.deleteById(4);
		
		//List<Department> depart = dep.findAll();
		
		//for (Department d : depart) {
		//	System.out.println(d.toString());
		//}
		
		Department dep0 = dep.findById(1);		
		System.out.println(dep0.toString());
		
		SellerDao seller = DaoFactory.createSellerDao();
		
		Seller seller0 = new Seller(null, "Isaque Santos Pinto", "isaquesantos.second.1998@gmail.com", new Date(), 2304.28, dep0);
		seller.insert(seller0);
		
		List<Seller> listSeller = seller.findAll();
		
		for (Seller s: listSeller) {
			System.out.println(s);
		}
		
		DbConnect.closeConnection();
	}
}
