package action;

import org.springframework.stereotype.Controller;

/**
 * Created by Seven on 16/03/2017.
 */
@Controller
public class MainAction extends BaseAction {

    @Override
    public String execute(){
        return "main";
    }
}
