package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dao.GenericDao;
import model.Departament;
import model.Employee;
import model.RolesEnum;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmployeeController {
	protected static ModelAndView pageEmployees(Request req, Response res) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		GenericDao<Employee> genericDao = new GenericDao<Employee>();
		Employee employee = new Employee();
		model.put("allemployees", genericDao.listAll(employee));
		
		new Thread() {
			@Override
			public void run() {
				
				double totalOfSalaries = 0;
				
				List<Employee> employeeList = genericDao.listAll(employee);
				Iterator<Employee> it = employeeList.iterator();
				
				while(it.hasNext()) {
					totalOfSalaries += it.next().getSalary();
					}
				
				model.put("totalsalaries", totalOfSalaries);
			}
		}.start();

		return new ModelAndView(model, "view/employees/list.vm");
	}

	protected static ModelAndView newEmployee(Request req, Response res) {
		HashMap<String, Object> model = new HashMap<String, Object>();

		GenericDao<Departament> genericDepartament = new GenericDao<Departament>();
		Departament departament = new Departament();
		model.put("alldepartaments", genericDepartament.listAll(departament));

		ArrayList<String> rolesList = new ArrayList<String>();

		RolesEnum roles[] = RolesEnum.values();

		for (int i = 0; i < roles.length; i++) {
			rolesList.add(roles[i].toString());
		}

		Iterator<String> it = rolesList.iterator();

		System.out.println("Esses são os níveis de empregados disponíveis no momento de cadastrar um novo empregado");

		while (it.hasNext()) {
			System.out.println(it.next().toLowerCase());
		}

		model.put("allenums", roles);

		return new ModelAndView(model, "view/employees/new.vm");
	}
}
