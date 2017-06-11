package action;

/**
 * Created by Seven on 22/03/2017.
 */
public class BaseAction {

    public class BaseAction extends ActionSupport implements SessionAware,
            ServletRequestAware, ServletResponseAware {

        private static final long serialVersionUID = 1L;

        public HttpServletRequest   request;
        public HttpServletResponse  response;
        @SuppressWarnings("unchecked")
        public Map session;

        public void setSession(Map session) {
            this.session = session;
        }

        public void setServletRequest(HttpServletRequest request) {
            this.request = request;
        }

        public void setServletResponse(HttpServletResponse response) {
            this.response = response;
        }
}
