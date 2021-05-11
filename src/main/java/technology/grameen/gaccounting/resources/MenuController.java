package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.menu.MenuService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {

    private MenuService menuService;

    MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/available-menus")
    public ResponseEntity<IResponse> getAvailableMenus(){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(), menuService.getAvailableMenus()
        ), HttpStatus.OK);
    }

    @GetMapping("/available-menus/{role}")
    public ResponseEntity<IResponse> getAvailableMenus(@PathVariable("role") String role) throws UnsupportedEncodingException {
        String _role = URLDecoder.decode(role,"UTF-8");
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(), menuService.getMenusByRole(_role)
        ), HttpStatus.OK);
    }
}
