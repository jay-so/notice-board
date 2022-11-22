package sku.mvc.controller;

/**
 * 
 * 사용자 요청이 서비스를 실행한 후에
 * 결과뷰 페이지와 이동방식을 관리하는 클래스
 *
 */

public class ModelAndView {
  private String viewName; //결과 뷰페이지이름
  private boolean isRedirect; //false이면 forward방식, true이면 redirect방식으로 이동
  
  public ModelAndView() {}
  
  public ModelAndView(String viewName, boolean isRedirect) {
	  super();
	  this.viewName = viewName;
	  this.isRedirect = isRedirect;
  }

public String getViewName() {
	return viewName;
}

public void setViewName(String viewName) {
	this.viewName = viewName;
}

public boolean isRedirect() {
	return isRedirect;
}

public void setRedirect(boolean isRedirect) {
	this.isRedirect = isRedirect;
  }
}