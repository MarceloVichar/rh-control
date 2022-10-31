package control;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController {
	protected static ModelAndView pageHome(Request req, Response res) {
		HashMap<String, Object> model = new HashMap();
		return new ModelAndView(model, "view/home.vm");
	}
}
