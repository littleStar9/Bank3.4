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

public class registaction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	String name=null;
	String password=null;	 
	name=request.getParameter("username");
	password=request.getParameter("password");
	ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象
	//UserManager userMassage=UserManager.getInstance();
	 
//	boolean flag=
			userMassage.register(name,password);	 
//	if(flag){		
		return mapping.findForward("success");
		//request.getRequestDispatcher("/user/msg2.jsp").forward(request, response);}
//	}
//	return mapping.findForward("/user/errors.jsp");
	}
}
