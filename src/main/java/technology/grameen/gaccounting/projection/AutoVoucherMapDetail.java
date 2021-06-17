package technology.grameen.gaccounting.projection;

public interface AutoVoucherMapDetail {

    interface ChartAccount{
        Long getId();
        String getTitle();
    }

    interface VoucherType{
        Integer getId();
        String getName();
    }

    Long getId();
    String getName();
    String getModuleName();

    ChartAccount getCrHeader();

    ChartAccount getDrHeader();

    VoucherType getVoucherType();
}
