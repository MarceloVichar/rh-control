package control;

import java.util.HashMap;

import dao.GenericDao;
import model.BasicBenefit;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BasicBenefitsController {
	protected static ModelAndView pageBasicBenefits(Request req, Response res) {
		HashMap<String, Object> model = new HashMap();
		GenericDao<BasicBenefit> genericDao = new GenericDao<BasicBenefit>();
		BasicBenefit basicBenefit = new BasicBenefit();
		model.put("allbasicbenefits", genericDao.listAll(basicBenefit));

		return new ModelAndView(model, "view/basic-benefits/list.vm");
	}
}
