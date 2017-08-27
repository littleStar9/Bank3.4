package InfoAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.manager.ManagerInterface;

import InfoForms.bankForm;

public class BankAction extends BaseAction {
	/**
	 * 如果没有传递任何标识参数（如command参数），则默认调用unspecified方法
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("ItemAction=>>unspecified()");
		ActionForward listActionForward = new ActionForward("/index.jsp", true);
		return listActionForward;
	}
	//用户 首页
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取从页面表单中提交过来的值
	    ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象		  
		//req.getRequestDispatcher("/user/inquiry1.jsp").forward(req, resp);
	    return mapping.findForward("show");
	}
	//转账
	public ActionForward transfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取从页面表单中提交过来的值
		bankForm iaf = (bankForm)form;
	    ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象		  
		userMassage.tranferMoney(iaf.getTName(),iaf.getTraMoney());
		//req.getRequestDispatcher("/user/inquiry1.jsp").forward(req, resp);
		 return mapping.findForward("inquiry");

	}
	//查询
	public ActionForward inquiry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取从页面表单中提交过来的值
		//TransferActionForm iaf = (TransferActionForm)form;
		ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象
		userMassage.inquiry();
		//request.getRequestDispatcher("/user/inquiry1.jsp").forward(request, response);
		return mapping.findForward("inquiry");
	}
	//存款
	public ActionForward deposite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取从页面表单中提交过来的值
		bankForm iaf = (bankForm)form;
		System.out.println(iaf.getDepMoney());
	    ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象		  
	    userMassage.deposit(iaf.getDepMoney());
		//req.getRequestDispatcher("/user/inquiry1.jsp").forward(req, resp);
	    return mapping.findForward("inquiry");
	}
	//取款
	public ActionForward withdraw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取从页面表单中提交过来的值
		bankForm iaf = (bankForm)form;
		ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象
		userMassage.withdrawals(iaf.getWdrMoney());

		 //req.getRequestDispatcher("/user/inquiry1.jsp").forward(req, resp);
		return mapping.findForward("inquiry");
	}
	//注销
	public ActionForward exitsystem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ManagerInterface userMassage  = ManagerImpl.getInstance();//取得业务对象
		userMassage.exitSystem(request.getSession().getAttribute("name").toString());
		request.getSession().invalidate();
		return mapping.findForward("exitsys");
//		ActionForward af = new ActionForward("/user/login.jsp",true);
//		return af;
	}


}
