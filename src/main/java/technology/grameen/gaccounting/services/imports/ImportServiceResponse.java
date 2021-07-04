package technology.grameen.gaccounting.services.imports;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import technology.grameen.gaccounting.accounting.entity.imports.LedgerInfo;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImportServiceResponse {
    private String fileName;
    private List<LedgerInfo> ledgerInfoList;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<LedgerInfo> getLedgerInfoList() {
        return ledgerInfoList;
    }

    public void addLedgerInfo(LedgerInfo ledgerInfo) {
        if(this.ledgerInfoList == null){
            this.ledgerInfoList = new ArrayList<>();
        }
        this.ledgerInfoList.add(ledgerInfo);
    }
}
