package control;

import java.util.HashMap;

import dao.GenericDao;
import model.Student;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class StudentController {
	protected static ModelAndView pageDetail(Request req, Response res) {
		HashMap<String, Object> model = new HashMap();
		GenericDao<Student> genericDao = new GenericDao<Student>();
		Student student = new Student();
        long idreq = Long.parseLong(req.params("id"));	
        try {
			student = genericDao.getObjectById(student, idreq);
		} catch (Exception e) {
			System.out.println("deu craps da leitura do banco");
		}
		model.put("student", student);

		return new ModelAndView(model, "view/detail.vm");
	}
	
	protected static ModelAndView pageNew(Request req, Response res) {
		HashMap<String, Object> model = new HashMap();
		return new ModelAndView(model, "view/new.vm");
	}
	
	protected static Object createStudent(Request req, Response res) {
		
		String name = req.queryParams("name");
		int age = Integer.parseInt(req.queryParams("age"));
		String email = req.queryParams("email");
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		student.setEmail(email);
		GenericDao<Student> dao = new GenericDao<Student>();
			
        try {
			dao.save(student);
		} catch (Exception e) {
			System.out.println("deu craps na gravação do banco");
			return("Internal Server Craps");
		}
		
		res.redirect("/");
		
		return "ok";
		
	}
}
