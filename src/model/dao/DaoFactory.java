package model.dao;

import DB.DbConnect;
import model.dao.implementation.DepartmentDaoJDBC;
import model.dao.implementation.SellerDaoJDBC;

public class DaoFactory {
	public static SellerDao createSellerDao () {
		return new SellerDaoJDBC(DbConnect.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.DbConnect.getConnection());
	}
 }
