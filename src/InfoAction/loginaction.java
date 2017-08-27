package InfoAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.manager.ManagerInterface;

public class loginaction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	HttpSession session = request.getSession();
	String name=null;
	String password=null; 
	name=request.getParameter("username");
	password=request.getParameter("password");
	ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象
//	boolean flag=
			userMassage.login(name,password);	 
//	if(flag){		
		session.setAttribute("name", name);
		session.setAttribute("password", password);
		return mapping.findForward("success");
//	}
	
//	return mapping.findForward("fail");
	}
}