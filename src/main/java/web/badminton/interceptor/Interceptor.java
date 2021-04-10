// package web.badminton.interceptor;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;

// import web.badminton.constant.Constant;
// import web.badminton.vo.User;

// @Component
// public class Interceptor implements HandlerInterceptor {
// 	@Autowired
// 	HttpSession session;
	
// 	@Override
// 	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
		
// 		User user = (User)session.getAttribute(Constant.USER);
// 		if(request.getRequestURI().equals("/profile") && user == null) {
// 			response.sendRedirect(request.getContextPath()+"/");
// 		}
		
// 		if(request.getRequestURI().equals("/pay") && user == null) {
// 			response.sendRedirect(request.getContextPath()+"/");
// 		}	
// 		return true;		
// 	}
// }
