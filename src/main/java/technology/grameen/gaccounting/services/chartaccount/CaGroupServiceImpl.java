package technology.grameen.gaccounting.services.chartaccount;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.accounting.entity.ChartAccountGroup;
import technology.grameen.gaccounting.accounting.repositories.CaGroupRepository;

@Service
public class CaGroupServiceImpl implements CaGroupService{

    CaGroupRepository caGroupRepository;

    CaGroupServiceImpl(CaGroupRepository caGroupRepository){
        this.caGroupRepository = caGroupRepository;
    }

    @Override
    @Transactional
    public ChartAccountGroup addCaGroup(ChartAccount chartAccount) {
        ChartAccountGroup chartAccountGroup = new ChartAccountGroup();
        chartAccountGroup.setChartAccount(chartAccount);
        return caGroupRepository.save(chartAccountGroup);
    }
}
