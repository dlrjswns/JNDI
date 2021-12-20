package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
// 사용자의 모든 요청을 FC로 향하게해야함!!!
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      actionDO(request,response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      actionDO(request,response);
   }

   private void actionDO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 1. 요청을 파악
      String uri=request.getRequestURI();
      System.out.println(uri);
      String cp=request.getContextPath();
      System.out.println(cp);
      String command=uri.substring(cp.length());
      System.out.println(command);
      
      ActionForward forward=null;
      // 2. 각 요청에 대한 Controller 처리를 수행
      if(command.equals("/main.do")) {
         try {
            forward= new MainAction().execute(request, response);
            // 1. 데이터 전송여부 == 데이터 전달방식
            // 2. 향하는 페이지정보
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if(command.equals("/board.do")) {
         try {
            forward=new BoardAction().execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      else if(command.equals("/insert.do")) {
         try {
            forward=new InsertAction().execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      if(forward!=null) { // 예외등이 발생하면 null일수도있음!
         if(forward.isRedirect()) { // 널포인터익셉션발생가능성있음!!!!!
            response.sendRedirect(forward.getPath());
         }
         else {
            // pageContext.forward();
            // 전달해야할 정보가 존재함을 의미!!!
            // [RequestDispatcher API를 사용]
            //  : 요청 재지정 기능을 구현해주는 API
            //  : 클라이언트로부터 요청받은 사항을 직접 응답하지않고
            //    다른 자원이 넘겨받아 처리 결과를 대신 응답하는 것
            //    == 클라이언트의 요청에 대하여 서버의 다른 자원들로 그 요청을 처리하도록 재지정하는 것
            RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
            dispatcher.forward(request, response);
         }
      }
      
   }
}