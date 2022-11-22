package control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.protobuf.Any;

import dao.GenericDao;
import model.BasicBenefit;
import model.Departament;
import model.Employee;
import model.ExtraBenefit;
import model.RolesEnum;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import util.MyException;

public class EmployeeController {
	protected static ModelAndView pageEmployees(Request req, Response res) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		GenericDao<Employee> genericDao = new GenericDao<Employee>();
		Employee employee = new Employee();
		List<Employee> allemployees = genericDao.listAll(employee);
		model.put("allemployees", allemployees);
		
		double totalOfSalaries = 0;
		
		try {
			if (allemployees == null) {
					throw new MyException("A lista está vazia e não pode ser percorrida");
			}
			
			Iterator<Employee> it = allemployees.iterator();

			while (it.hasNext()) {
				totalOfSalaries += it.next().getSalary();
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			totalOfSalaries = 0;
		} catch (NullPointerException e) {
			totalOfSalaries = 0;
		}
		
		model.put("totalsalaries", totalOfSalaries);
		model.put("now", "data");
		
		
		new Thread() {
			@Override
			public void run() {

				synchronized (this) {
					while (true) {
						try {
							wait(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
												
						Date date = Calendar.getInstance().getTime();  
						DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
						String currentDate = dateFormat.format(date);
				
//						System.out.println(currentDate);
						
						model.replace("now", currentDate);
						

					}
				}
			}
		}.start();

		return new ModelAndView(model, "view/employees/list.vm");
	}

	protected static ModelAndView newEmployee(Request req, Response res) {
		HashMap<String, Object> model = new HashMap<String, Object>();

		GenericDao<Departament> genericDepartament = new GenericDao<Departament>();
		Departament departament = new Departament();
		model.put("alldepartaments", genericDepartament.listAll(departament));

		GenericDao<ExtraBenefit> genericExtraBenefit = new GenericDao<ExtraBenefit>();
		ExtraBenefit extraBenefit = new ExtraBenefit();
		model.put("allextrabenefits", genericExtraBenefit.listAll(extraBenefit));

		GenericDao<BasicBenefit> genericBasicenefit = new GenericDao<BasicBenefit>();
		BasicBenefit basicBenefit = new BasicBenefit();
		model.put("allbasicbenefits", genericBasicenefit.listAll(basicBenefit));

		ArrayList<String> rolesList = new ArrayList<String>();

		RolesEnum roles[] = RolesEnum.values();

		for (int i = 0; i < roles.length; i++) {
			rolesList.add(roles[i].toString());
		}

		Iterator<String> it = rolesList.iterator();

		while (it.hasNext()) {
			System.out.println(it.next().toLowerCase());
		}

		model.put("allenums", roles);

		return new ModelAndView(model, "view/employees/new.vm");
	}
}
