package control;

import java.util.HashMap;

import dao.GenericDao;
import model.Employee;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmployeeController {
	protected static ModelAndView pageEmployees(Request req, Response res) {
		HashMap<String, Object> model = new HashMap();
		GenericDao<Employee> genericDao = new GenericDao<Employee>();
		Employee employee = new Employee();
		model.put("allemployees", genericDao.listAll(employee));

		return new ModelAndView(model, "view/employees/list.vm");
	}
}
