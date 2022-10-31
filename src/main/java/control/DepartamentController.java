package control;

import java.util.HashMap;

import dao.GenericDao;
import model.Departament;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class DepartamentController {
	protected static ModelAndView pageDepartaments(Request req, Response res) {
		HashMap<String, Object> model = new HashMap();
		GenericDao<Departament> genericDao = new GenericDao<Departament>();
		Departament departament = new Departament();
		model.put("alldepartaments", genericDao.listAll(departament));

		return new ModelAndView(model, "view/departaments/list.vm");
	}
}
