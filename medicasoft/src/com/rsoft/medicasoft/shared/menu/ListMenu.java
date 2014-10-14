package com.rsoft.medicasoft.shared.menu;

//import com.rsoft.easyprod.shared.model.UserGroupDetail;
import java.util.ArrayList;
//Decorateur
public class ListMenu extends ArrayList<Menu> {

    private static final long serialVersionUID = 779431231016207028L;
  /*  private List<UserGroupDetail> gds;

    public ListMenu(List<UserGroupDetail> gds) {
        super();
        this.gds = gds;
    }
*/
    @Override
    public boolean add(Menu e) {
        boolean added = false;
/*        if (e != null && Menu.authorizeAdd(gds, e.getMenuId())) {
            super.add(e);
        } else {
        }
*/        return added;
    }
}
