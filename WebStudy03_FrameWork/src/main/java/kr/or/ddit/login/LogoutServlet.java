package kr.or.ddit.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.isNew()) {
			resp.sendError(400);
			return;
		}
		session.invalidate();
		String message = URLEncoder.encode("로그아웃", "UTF-8");
		resp.sendRedirect(String.format("%s%s%s", req.getContextPath(), "/?message=", message));
	}
}
