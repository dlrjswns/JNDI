package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncFilter
 */
@WebFilter("*.jsp")
public class EncFilter implements Filter {
	
	private String encoding; // 외부의 환경설정파일(web.xml) = 톰캣이 보는 환경설정파일의 값을 저장할 멤버변수
    /**
     * Default constructor. 
     */
    public EncFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding); //사용자의 모든 .jsp url요청에 대해 request뺏어서 인코딩처리
											   //하드코딩되었기때문에 초기화매개변수를 이용
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding = fConfig.getServletContext().getInitParameter("encoding");
	}

}
