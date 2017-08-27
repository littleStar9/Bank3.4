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
	 * ���û�д����κα�ʶ��������command����������Ĭ�ϵ���unspecified����
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("ItemAction=>>unspecified()");
		ActionForward listActionForward = new ActionForward("/index.jsp", true);
		return listActionForward;
	}
	//�û� ��ҳ
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
	    ManagerInterface userMassage  = ManagerImpl.getInstance();//ȡ��ҵ�����		  
		//req.getRequestDispatcher("/user/inquiry1.jsp").forward(req, resp);
	    return mapping.findForward("show");
	}
	//ת��
	public ActionForward transfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		bankForm iaf = (bankForm)form;
	    ManagerInterface userMassage  = ManagerImpl.getInstance();//ȡ��ҵ�����		  
		userMassage.tranferMoney(iaf.getTName(),iaf.getTraMoney());
		//req.getRequestDispatcher("/user/inquiry1.jsp").forward(req, resp);
		 return mapping.findForward("inquiry");

	}
	//��ѯ
	public ActionForward inquiry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		//TransferActionForm iaf = (TransferActionForm)form;
		ManagerInterface userMassage  = ManagerImpl.getInstance();//ȡ��ҵ�����
		userMassage.inquiry();
		//request.getRequestDispatcher("/user/inquiry1.jsp").forward(request, response);
		return mapping.findForward("inquiry");
	}
	//���
	public ActionForward deposite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		bankForm iaf = (bankForm)form;
		System.out.println(iaf.getDepMoney());
	    ManagerInterface userMassage  = ManagerImpl.getInstance();//ȡ��ҵ�����		  
	    userMassage.deposit(iaf.getDepMoney());
		//req.getRequestDispatcher("/user/inquiry1.jsp").forward(req, resp);
	    return mapping.findForward("inquiry");
	}
	//ȡ��
	public ActionForward withdraw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ��ҳ������ύ������ֵ
		bankForm iaf = (bankForm)form;
		ManagerInterface userMassage  = ManagerImpl.getInstance();//ȡ��ҵ�����
		userMassage.withdrawals(iaf.getWdrMoney());

		 //req.getRequestDispatcher("/user/inquiry1.jsp").forward(req, resp);
		return mapping.findForward("inquiry");
	}
	//ע��
	public ActionForward exitsystem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ManagerInterface userMassage  = ManagerImpl.getInstance();//ȡ��ҵ�����
		userMassage.exitSystem(request.getSession().getAttribute("name").toString());
		request.getSession().invalidate();
		return mapping.findForward("exitsys");
//		ActionForward af = new ActionForward("/user/login.jsp",true);
//		return af;
	}


}
