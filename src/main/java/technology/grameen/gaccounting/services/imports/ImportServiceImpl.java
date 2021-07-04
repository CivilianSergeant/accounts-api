package technology.grameen.gaccounting.services.imports;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import technology.grameen.gaccounting.accounting.entity.imports.LedgerInfo;
import technology.grameen.gaccounting.exceptions.CustomException;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class ImportServiceImpl implements ImportService {


    private ImportServiceResponse response;

    public ImportServiceImpl(ImportServiceResponse response) {
        this.response = response;
    }

    @Override
    public ImportServiceResponse parseXlsxFile(MultipartFile file) throws IOException, CustomException {

        response.setFileName(file.getOriginalFilename());

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for(Row row : sheet){

            if(row == null || row.getCell(0) == null){
                break;
            }
            if(row.getRowNum()==0){
                continue;
            }

            LedgerInfo info = parseXLSX(row);
            response.addLedgerInfo(info);
        }
        return response;
    }

    private LedgerInfo parseXLSX(Row row){
        LedgerInfo info;

        String chartAccountType = (row.getCell(0)!=null)? row.getCell(0).getStringCellValue():"";
        String caLevel = (row.getCell(1).getCellType()==CellType.NUMERIC)?
                String.valueOf((int)row.getCell(1).getNumericCellValue()) :
                row.getCell(1).getStringCellValue();

        String parentGroupCode = (row.getCell(2).getCellType()==CellType.NUMERIC)?
                String.valueOf((int)row.getCell(2).getNumericCellValue()):
                row.getCell(2).getStringCellValue();
        String ledgerName = row.getCell(3).getStringCellValue();
        String ledgerCode = "";
        if(row.getCell(4).getCellType() == CellType.NUMERIC) {
            ledgerCode = String.valueOf(row.getCell(4).getNumericCellValue());
            String[] lc = ledgerCode.split("\\.");
            int lcn = Integer.parseInt(lc[1]);
            ledgerCode = lc[0] + ((lcn>0)? "."+lc[1]: "");
        }else{
            ledgerCode = row.getCell(4).getStringCellValue();
        }
        String openingBalanceDr = (row.getCell(5)!=null)?(row.getCell(5).getCellType()==CellType.NUMERIC)?
                String.valueOf(row.getCell(5).getNumericCellValue()) :
                row.getCell(5).getStringCellValue() : null;
        String openingBalanceCr = (row.getCell(6)!=null)?(row.getCell(6).getCellType()==CellType.NUMERIC)?
                String.valueOf(row.getCell(6).getNumericCellValue()) :
                row.getCell(6).getStringCellValue():null;

        System.out.println(openingBalanceDr);

        info = new LedgerInfo();
        info.setChartAccountType(chartAccountType);
        info.setCaLevel(caLevel);
        info.setParentGroupCode(parentGroupCode);
        info.setLedgerName(ledgerName);
        info.setLedgerCode(ledgerCode);

        double drBalance = (openingBalanceDr!=null && !openingBalanceDr.isEmpty())? Double.valueOf(openingBalanceDr) : 0;
        double crBalance = (openingBalanceCr!=null && !openingBalanceCr.isEmpty())? Double.valueOf(openingBalanceCr) : 0;

        info.setOpeningBalanceDr(BigDecimal.valueOf(drBalance));
        info.setOpeningBalanceCr(BigDecimal.valueOf(crBalance));

        System.out.println("Type: "+chartAccountType+
                " level: "+caLevel + " groupCode: "+parentGroupCode+
                " ledgerName: "+ ledgerName+ " ledgerCode: "+ledgerCode);

        return info;
    }
}
