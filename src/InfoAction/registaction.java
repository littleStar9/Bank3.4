package InfoAction;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.validator.constraints.Email;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.manager.ManagerInterface;
import com.cx.bank.user.model.DepositeCard;
import com.cx.bank.user.model.User;

public class registaction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	String name=null;
	String password=null;	 
	String ID=null;
	name=request.getParameter("username");
	password=request.getParameter("password");
	ID=request.getParameter("email");
	
//	 //用户类
//	 User bankUser = new User();
//	 bankUser.setUserName(name);
//	 bankUser.setIDCredit(ID); 
//	 //银行卡类
//	 DepositeCard bk = new DepositeCard();
//	 bk.setPassWord(password);
//	 Set BankCards = new HashSet();
//	 BankCards.add(bk);
	
//	 bankUser.setBankCards(BankCards);
	 ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象
	 
	//UserManager userMassage=UserManager.getInstance();
	 
//	boolean flag=
	 		userMassage.register(name,password,ID);	 
			//userMassage.register(name,password,ID);	 
//	if(flag){		
		return mapping.findForward("success");
		//request.getRequestDispatcher("/user/msg2.jsp").forward(request, response);}
//	}
//	return mapping.findForward("/user/errors.jsp");
	}
}
