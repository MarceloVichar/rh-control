package control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dao.GenericDao;
import dao.StudentDao;
import model.Student;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class Main {

	public static void main(String[] args) {

//	//	StudentDao dao = new StudentDao();
//	 Student student = new Student();
//	 student.setAge(32);
//	 student.setEmail("batata@legumes.com.br");
//	 student.setName("batata salsa silva");
//	 GenericDao<Student> genericDao = new GenericDao<Student>();
//	 genericDao.save(student); 
	 //genericDao.delete(student);

		VelocityTemplateEngine engine = new VelocityTemplateEngine();
		staticFiles.location("/public"); //informando ao velocity sobre o css

		// get("/hello", (req, res) -> "hello world");
		get("/", Main::pageHome, engine);
		get("/student/:id", Main::pageDetail, engine);
		get("/students/new", Main::pageNew, engine);
		post("/students", Main::createStudent);

	}

	private static ModelAndView pageHome(Request req, Response res) {
		HashMap<String, Object> model = new HashMap();

		// teste hashmap
//		model.put("username", "Emerson");
//		model.put("pass", 12345);
		GenericDao<Student> genericDao = new GenericDao<Student>();
		Student student = new Student();
		model.put("allstudents", genericDao.listAll(student));

		return new ModelAndView(model, "view/home.vm");
	}

	private static ModelAndView pageDetail(Request req, Response res) {
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

	private static ModelAndView pageNew(Request req, Response res) {
		HashMap<String, Object> model = new HashMap();
		return new ModelAndView(model, "view/new.vm");
	}
	private static Object createStudent(Request req, Response res) {
		
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
