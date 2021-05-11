package technology.grameen.gaccounting.services.chartaccount;

import org.springframework.stereotype.Service;
import technology.grameen.gaccounting.accounting.entity.ChartAccountType;
import technology.grameen.gaccounting.accounting.repositories.CaTypeRepository;
import technology.grameen.gaccounting.projection.authserver.CaTypeList;

import java.util.List;

@Service
public class CaTypeServiceImpl implements CaTypeService{

    CaTypeRepository caTypeRepository;

    CaTypeServiceImpl(CaTypeRepository caTypeRepository){
        this.caTypeRepository = caTypeRepository;
    }

    @Override
    public List<CaTypeList> chartAccountTypeList() {
        return caTypeRepository.findAllByStatus(true);
    }
}
