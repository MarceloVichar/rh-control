package control;

import java.util.HashMap;

import dao.GenericDao;
import model.ExtraBenefit;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ExtraBenefitsController {
	protected static ModelAndView pageExtraBenefits(Request req, Response res) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		GenericDao<ExtraBenefit> genericDao = new GenericDao<ExtraBenefit>();
		ExtraBenefit extraBenefit = new ExtraBenefit();
		model.put("allextrabenefits", genericDao.listAll(extraBenefit));

		return new ModelAndView(model, "view/extra-benefits/list.vm");
	}
}
