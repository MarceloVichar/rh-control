package control;

import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class Main {

	public static void main(String[] args) {

		VelocityTemplateEngine engine = new VelocityTemplateEngine();
		staticFiles.location("/public");
		
		get("/", MainController::pageHome, engine);
		
		get("/student/:id", StudentController::pageDetail, engine);
		get("/students/new", StudentController::pageNew, engine);
		post("/students", StudentController::createStudent);
		
		get("/employees", EmployeeController::pageEmployees, engine);
		get("/employees/new", EmployeeController::newEmployee, engine);
		
		get("/departaments", DepartamentController::pageDepartaments, engine);
		
		get("/basic-benefits", BasicBenefitsController::pageBasicBenefits, engine);
		
		get("/extra-benefits", ExtraBenefitsController::pageExtraBenefits, engine);

	}
}
