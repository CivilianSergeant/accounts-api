package technology.grameen.gaccounting.services.menu;

import technology.grameen.gaccounting.accounting.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getAvailableMenus();

    List<Menu> getMenusByRole(String role);
}
