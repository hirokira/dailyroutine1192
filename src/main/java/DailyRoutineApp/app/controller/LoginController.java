package DailyRoutineApp.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import DailyRoutineApp.app.service.UserAccountService;

@Controller
public class LoginController {


	@Autowired
	private UserAccountService sv;

	@Autowired
	private HttpSession session;

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String index(Model model) {

//		sv.registerAdmin("admin","管理者", "admin");
		model.addAttribute("iserror",false);
		model.addAttribute("msg", session.getAttribute("msg"));
		if(session.getAttribute("msg")!=null) {
			session.removeAttribute("msg");
		}
		return "login";
	}


	@RequestMapping(value="/login-error",method=RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("iserror", true);
		return "login";
	}


	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public ModelAndView hello(ModelAndView mav) {
		mav.setViewName("hello");
		return mav;
	}

}
