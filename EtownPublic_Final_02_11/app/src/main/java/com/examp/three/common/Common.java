package com.examp.three.common;

import android.content.Context;
import android.net.ConnectivityManager;

public class Common {

    public static final String PARAMETER_SEPERATOR_AMP = "&";
    public static final String URL_ONLINEPAYMENT_DOMAIN = "http://etownmobile.in/EtownServicedemo/";

    // Login and Register Using .Net Services - Login and register
    public static final String URL_SOAP_DOMAIN = "http://www.etownpanchayat.com/JavaService/Service.asmx";
    public static final String NAMESPACE = "http://tempuri.org/";
    public static final String SOAP_ACTION_Register = "http://tempuri.org/FinalRegistrationDetails";
    public static final String SOAP_ACTION_Login = "http://tempuri.org/LoginDetails";


    //CC AVUENUE
    public static final String URL_RSA_KEY = "http://www.etownpanchayat.com/Android/GetRSA.aspx";
    public static final String URL_REDIRECT = "http://www.etownpanchayat.com/Android/ccavResponseHandler.aspx";
    public static final String URL_CANCEL = "http://www.etownpanchayat.com/Android/ccavResponseHandler.aspx";


    //http://etownmobile.in/EtownServicedemo/getAccessCode
    //To get all details from etown currency ,accesscode ,merchantid
    public static String CURRENCY_CC_Avuenue = "currency";
    public static String AccessCode_CC_Avuenue = "accesscode";
    public static String MERCHANTID_CC_Avuenue = "merchantid";


    //Prematix Mail and Contact No
    public static String MAIL_CREDENTIAL_PASSWORD = "inf@prematix";
    public static String MAIL_CREDENTIAL_MAILID = "info@prematix.com";
    public static String MAIL_CREDENTIAL_CONTACTNUMBER = "contactno";


    public static String baseUrlPaypre = "http://paypre.info/";

    //priya this is for node service remote config
    public static String baseUrl = "http://www.predemos.com/Etown/";


    public static String paypreUrl = "http://paypre.info/";


    //priya this is for java service remote config
    public static String baseUrlETown = "http://etownmobile.in/";


    public static String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.UHJlbWF0aXg.VnYf5L2bruAL3IhIbhOCnqW3SADSM2qjWrZAV0yrB94";

    public static String API_SAVENAMETRANSFER = baseUrl + "EPSNameTransfer?RequestNo=";
    public static String API_DISTRICT_DETAILS = baseUrl + "EPSGetDistrictDetails";

    public static String API_TOWNPANCHAYAT = baseUrl + "EPSGetPanchayatDetails?DistrictId=";
    public static String API_CCAVENUEENTRYNO = "http://etownmobile.in/EtownServicedemo/onlineEntry";
    public static String API_CCAVENUE_CHECK_BANK = "http://etownmobile.in/EtownServicedemo/getDataAcceptNew?";
    public static String API_CCAVENUE_MAILCREDENTIAL = "http://etownmobile.in/EtownServicedemo/getEmailCredentialstemp";
    public static String API_QUERY_DETAILS = baseUrl + "Trade_GetTradeQueryDetails?";
    public static String API_CCAVUENUERATEDETAILSLIST = "http://etownmobile.in/EtownServicedemo/getCCAVenueRateDetailsList";
    public static String API_CCAVUENUE_GET_ALLBANKS = "http://etownmobile.in/EtownServicedemo/getBankAll";
    public static String API_TAXBALANCEPAYMENTDETAILS = baseUrl + "EPSTaxBalancePaymentDetails?";
    public static String API_PAYMENTHISTORY_WEBVIEW = "http://www.etownpanchayat.com/PublicServices/WebView/LandingPage.aspx?";
    public static String API_FinancialDates = baseUrl + "EPSTaxMasterDetails?Type=FinYear&Input=WD-04&District=Krishnagiri&Panchayat=Mathigiri";
    public static String API_VIEW_DCB = "http://www.etownpanchayat.com/PublicServices/WebView/LandingPage.aspx?RType=DCB&";
    public static String API_NEW_TRACK_ASSESSMENTORCONNECTION = baseUrl + "EPSTrackingRequest?";
    public static String GET_ASSESSMENT_PROP = baseUrl + "EPSTaxBalancePaymentDetails?Type=PSearch&TaxNo=";
    public static String API_ORGANISATIONS = baseUrl + "EPSTaxMasterDetails?Type=Organization&Input=&District=";
    public static String API_GET_PROFESSION_DEMAND = baseUrl + "EPSProfessionDemand?District=";
    public static String API_ONLINE_FILING_SAVE = baseUrl + "EPSProfessionOnline?District=";
    public static String API_TRACKNEWASSESSMENT = baseUrl + "EPSTrackingRequest?";
    public static String API_PROPERTYTAXCALCULATION = baseUrl + "EPSPropertyTaxCalcuation?";
    public static String API_PROPERTYTAXMASTERDETAILS = baseUrl + "EPSTaxMasterDetails?";
    public static String API_ASSBY_NO = baseUrl + "EPSTaxBalancePaymentDetails?";
    public static String API_ADD_NEW_CONNECTION = baseUrl + "EPSWaterNewConnection?";
    public static String API_User_Login = "http://predemos.com/Etown/User_details?";
    public static String API_TAX_MASTER_DETAILS = baseUrl + "EPSTaxMasterDetails?";
    public static String API_NEW_PROFESSIONASS = baseUrl + "EPSProfessionNewAssessment?";
    public static String API_SAVELICENCERENEWAL = baseUrl + "Trade_OnlineTradersLicenceRenewalRequestNew?qType=LicenceRenewalWOC&ExApplicationNo=";
    public static String API_GETLICENCERENEWALFULLDATA = baseUrl + "Trade_GetTradeLicenceRenewalFullDataByUser?District=";
    public static String API_GETTRADENAMES = baseUrl + "Trade_GetTradeNameByLicenceType?District=";
    public static String API_GETWARDNO = baseUrl + "EPSTaxMasterDetails?Type=Ward&Input=&District=";
    public static String API_GETSTREETNAMES = baseUrl + "EPSTaxMasterDetails?Type=Street&Input=";
    public static String API_GETTRADEDATA = baseUrl + "Trade_GetTradeLicenceRenewalBaseDataByUser?ApplicationRefNo=";
    public static String API_GETAPPLICATIONNOLIST = baseUrl + "Trade_GetTradeLicenceRenewalListByUser?PublicUserId=";
    public static String API_ONLINEPAYMENT_BUILDINGONCLICK = baseUrl + "Online_BL_Pay?";
    public static String API_ONLINEPAYMENT_BUILDING = baseUrl + "Online_Payment?";
    public static String API_ONLINEPAYMENT_TRADE = baseUrl + "Dropdown_Load?";
    public static String API_ONLINEPAYMENT_SEARCHDETAILS = baseUrl + "Search_Deatils?";
    public static String API_ONLINE_TRADE_ONLINEBEFORETRANSACTIONDETAILS = baseUrl + "Trade_OnlineTaxBeforeTransactionDetails?qType=Insert";
    public static String API_ONLINE_Trade_OnlineTaxTransactionDetails = baseUrl + "Trade_OnlineTaxTransactionDetails?qType=Insert";
    public static String TRACKNEWASSESSMENT = baseUrl + "EPSTrackingRequest?";


    public static boolean isMotorInstalled = true;
    public static boolean isRental = true;
    public static boolean mHaveRentalCopy = true;
    public static boolean mHaveNocDoc = true;
    public static boolean isNeedToUpdateBuildingPlan = false;


    public static String param_distrct = "district";
    public static String param_panchayat = "panchayat";
    public static String param_mobileNo = "mobileNo";
    public static String param_emailId = "emailId";
    public static String param_finyear = "finyear";
    public static String param_name = "name";


    //Intent Type
    public static String Type = "Type";


    //CC AVUENUE ACCESS CODE AND MERCHANT ID
    public static String API_CCAVENUE_MERCHANTACCESSCODE = URL_ONLINEPAYMENT_DOMAIN + "getAccessCode";


    public static final String METHOD_LOGIN = "LoginDetails";
    public static final String METHOD_REGISTRATION = "FinalRegistrationDetails";

    public static final String METHOD_NAME_GET_TAX_DETAILS = URL_ONLINEPAYMENT_DOMAIN + "getTaxDetails";
    public static final String METHOD_NAME_SAVE_ADD_TAX_DETAILS = URL_ONLINEPAYMENT_DOMAIN + "AddParticularTaxNoDetails";
    public static final String METHOD_NAME_CHECK_TAX_DETAILS = URL_ONLINEPAYMENT_DOMAIN + "CheckTaxNoExistsInPanchayat";
    public static final String METHOD_NAME_DELETE_TAX_DETAILS = URL_ONLINEPAYMENT_DOMAIN + "deleteParticularTaxDetails";
    public static final String METHOD_NAME_SAVE_PROPERTY = URL_ONLINEPAYMENT_DOMAIN + "savePropertyTaxDetailsUpdate";
    public static final String METHOD_NAME_SAVE_PROFESSION = URL_ONLINEPAYMENT_DOMAIN + "saveProfessionDetails_new";
    public static final String METHOD_NAME_SAVE_WATER = URL_ONLINEPAYMENT_DOMAIN + "saveWaterDetails_new";
    public static final String METHOD_NAME_SAVE_ONLINE_TRANS = URL_ONLINEPAYMENT_DOMAIN + "saveOnlineTaxTransactionDetails";
    public static final String METHOD_NAME_LOGS = URL_ONLINEPAYMENT_DOMAIN + "saveLogs";
    public static final String METHOD_NAME_GET_PARTICULAR_TAX = URL_ONLINEPAYMENT_DOMAIN + "getParticularTaxTypeDetails";


    // TODO NonTax  Demand Retrieve
    public static final String METHOD_NAME_GET_BAL_BLOCK_DETAILS = URL_ONLINEPAYMENT_DOMAIN + "getPanchayatBlockListNew";

    // TODO NonTax  Demand Retrieve
    public static final String METHOD_NAME_GET_NONTAXBAL_DETAILS = URL_ONLINEPAYMENT_DOMAIN + "GetNonTaxDemadDetails";

    // TODO Water Demand Retrieve
    public static final String METHOD_NAME_GET_WATERBAL_DETAILS = URL_ONLINEPAYMENT_DOMAIN + "GetWaterDemandDetails";


    // TODO Property and professional api - Demand Api User Fininacial Year to Pay retrieve api
    public static final String METHOD_NAME_GET_PPBAL_DETAILS = URL_ONLINEPAYMENT_DOMAIN + "GetPropProfBalanceDetailsUpdate";

    // TODO To get Order Id From Server to Track Payment is done or not
    public static final String METHOD_NAME_GET_ORDERID = URL_ONLINEPAYMENT_DOMAIN + "onlineEntry";

    //TODO Before Transaction in Payment Save Quick Pay
    public static final String METHOD_NAME_SAVE_ONLINE_BEFORE_TRANS = URL_ONLINEPAYMENT_DOMAIN + "saveOnlineBeforeTran";


    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
