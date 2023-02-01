package jpacrud.cms.domain.payment.dto.res;


import jpacrud.cms.domain.payment.dto.PgGlobalDto;

public class PgPayCancelResDto extends PgGlobalDto {

    private int amount;
    private int supplyMoney;
    private int vatMoney;
    private int tipMoney;

    private String inMonth;
    private String appNo;
    private String cancelDate;
    private String orgAppNo;
    private String cardNo;
    private String cardName;
    private String cardCode;
    private String cardIssuerName;
    private String cardIssuerCode;
    private String cardAcqName;
    private String cardAcqCode;
    private String orderNo;
    private String udf1;
    private String udf2;
    private String Udf3;
}
