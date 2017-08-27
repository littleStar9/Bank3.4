package Exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.util.ModuleException;

public class ErrorCodeExceptionHandler extends ExceptionHandler {

    public ActionForward execute(
            Exception ex,
            ExceptionConfig ae,
            ActionMapping mapping,
            ActionForm formInstance,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException {
    		System.out.println("hhhhh");
    		if (!(ex instanceof ErrorCodeException)) {
    			System.out.println("supeerrrrr");
    			return super.execute(ex, ae, mapping, formInstance, request, response);
    		}
            ActionForward forward = null;
            ActionMessage error = null;
            String property = null;

            // Build the forward from the exception mapping if it exists
            // or from the form input
            if (ae.getPath() != null) {
            	System.err.println(ae.getPath());
                forward = new ActionForward(ae.getPath());
            } else {
                forward = mapping.getInputForward();
            }

            // Figure out the error
            if (ex instanceof ModuleException) {
                error = ((ModuleException) ex).getActionMessage();
                property = ((ModuleException) ex).getProperty();
            } else {
            	ErrorCodeException ece = (ErrorCodeException)ex;
            	//自定义异常数组类的异常信息的key
            	String errorCode = ece.getErrorCode();
            	//自定义异常数组类的异常信息的动态填充数组
            	Object[] args = ece.getArgs();
            	error = new ActionMessage(errorCode, args);
            	property = error.getKey();
            	
                //error = new ActionMessage(ae.getKey(), ex.getMessage());
                //property = error.getKey();
            }

            this.logException(ex);

            // Store the exception
            request.setAttribute(Globals.EXCEPTION_KEY, ex);
            this.storeException(request, property, error, forward, ae.getScope());
            System.out.println(forward);
            return forward;

        }	
}