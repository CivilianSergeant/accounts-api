package technology.grameen.gaccounting.services;

import org.springframework.stereotype.Service;
import technology.grameen.gaccounting.accounting.entity.GeneralSetting;
import technology.grameen.gaccounting.accounting.repositories.GeneralSettingRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UtilServiceImpl implements  UtilService {

    private GeneralSettingRepository generalSettingRepository;
    String[] yearEndStrArr;
    String financialStartDate;
    String financialEndDate;
    Optional<GeneralSetting> yearStartOp;
    Optional<GeneralSetting> yearEndOp;

    UtilServiceImpl(GeneralSettingRepository generalSettingRepository){

        this.generalSettingRepository = generalSettingRepository;



    }

    @Override
    public void loadData() {
        yearStartOp = generalSettingRepository.findByAlias("financial-year-start");
        yearEndOp = generalSettingRepository.findByAlias("financial-year-end");
        yearEndStrArr = yearEndOp.get().getValue().split("-");
        this.getFinancialDate();
    }




    private void getFinancialDate() {
        LocalDateTime localDateTime = LocalDateTime.now();

        int month = localDateTime.getMonth().getValue();
        int date = localDateTime.getDayOfMonth();


        int yearEndMonth = Integer.parseInt(yearEndStrArr[0]);
        int yearEndDate = Integer.parseInt((yearEndStrArr[1]));

        int endYear = (date > yearEndDate ||  month>yearEndMonth )? (localDateTime.getYear()+1) : localDateTime.getYear();
        int startYear = (endYear > localDateTime.getYear())? localDateTime.getYear() : (localDateTime.getYear()-1);

        this.financialStartDate = startYear+"-"+yearStartOp.get().getValue();
        this.financialEndDate = endYear+"-"+yearEndOp.get().getValue();
    }

    @Override
    public String getFinancialStartDate() {
        return this.financialStartDate;
    }

    @Override
    public String getFinancialEndDate() {
        return this.financialEndDate;
    }
}
