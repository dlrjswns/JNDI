package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;

public class BoardAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      BoardVO vo=new BoardVO(); // controller.jsp 7번라인
      vo.setBid(Integer.parseInt(request.getParameter("bid")));// 8번라인
      BoardDAO dao=new BoardDAO(); // 6번라인
      BoardVO data=dao.selectOne(vo); // 30번라인
      
      request.setAttribute("data", data); // 32번라인
      
      ActionForward forward=new ActionForward();
      forward.setPath("board.jsp");
      forward.setRedirect(false);
      return forward;
   }

}