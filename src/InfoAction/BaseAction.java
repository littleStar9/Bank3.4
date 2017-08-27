package InfoAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BaseAction extends DispatchAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getSession().getAttribute("name") == null) {
			//验证是否登录成功。session是否有用户
			return mapping.findForward("index");
		}
		//用户登录成功则调用dispatchAction的excute方法，将请求映射到相应的方法上
		return super.execute(mapping, form, request, response);
	}

}
