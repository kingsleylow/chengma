package com.mt4.api.bean;

public class ConGroup {

	 //--- common settings
	   private String               group;                   // group name ：分组名称
	   private int               enable;                      // enable group ：
	   private int               timeout;                     // trade confirmation timeout (seconds) ：超时
	   private int               otpMode;                    // one-time password mode
	   //--- statements
	   private String               company;                // company name ：公司名称
	   private String               signature;              // statements signature ：说明
	   private String               supportPage;           // company support page
	   private String               smtpServer;             // statements SMTP server
	   private String               smtpLogin;              // statements SMTP login
	   private String               smtpPassword;           // statements SMTP password
	   private String               supportEmail;           // support email
	   private String               templates ;              // path to directory with custom templates
	   private int               copies;                      // copy statements on support email
	   private int               reports;                     // enable statements
	   //--- default settings
	   private int               defaultLeverage;            // default leverage (user don't specify leverage himself)
	   private double            defaultDeposit;             // default deposit  (user don't specify balance  himself)
	   //--- securities
	   private int               maxsecurities;               // maximum simultaneous securities
	   //ConGroupSec       secgroups[MAX_SEC_GROUPS];   // security group settings
	  // ConGroupMargin    secmargins[MAX_SEC_GROPS_MARGIN]; // special securities settings
	   private int               secmarginsTotal;            // count of special securities settings
	   //--- margin & private interest
	   private String               currency;                // deposit currency
	   private double            credit;                      // virtual credit
	   private int               marginCall;                 // margin call level (percents)
	   private int               marginMode;                 // margin mode-MARGIN_DONT_USE,MARGIN_USE_ALL,MARGIN_USE_PROFIT,MARGIN_USE_LOSS
	   private int               marginStopout;              // stop out level
	   private double            interestrate;                // annual private interest rate (percents)
	   private int               useSwap;                    // use rollovers & private interestrate
	   //--- rights
	   private int               news;                        // news mode
	   private int               rights;                      // rights bit mask-ALLOW_FLAG_EMAIL
	   private int               checkIePrices;             // check IE prices on requests
	   private int               maxpositions;                // maximum orders and open positions
	   private int               closeReopen;                // partial close mode (if !=0 original position will be fully closed and remain position will be fully reopened)
	   private int               hedgeProhibited;            // hedge prohibition flag
	   private int               closeFifo;                  // fifo rule 
	   private int               hedgeLargeleg;              // use large leg margin for hedged positions
	   private int               unusedRights;            // reserved

	   private String               securitiesHash;         // private internal data
	   //---
	   private int               marginType;                 // margin controlling type { MARGIN_TYPE_PERCENT,  MARGIN_TYPE_CURRENCY }
	   //--- archives
	   private int               archivePeriod;              // inactivity period after which account moves to archive base (in days)
	   private int               archiveMaxBalance;         // maxumum balance of accounts to move in archive base
	   //---
	   private int               stopoutSkipHedged;         // skip fully hedged accounts when checking for stopout
	   private int               archivePendingPeriod;      // pendings clean period
	   //--- allowed news languages
	   //Uprivate int              news_languages[8];           // LANGID array
	   // Uprivate int              news_languages_total;        // news languages total
	   //--- reserved
	   private int               reserved;
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getOtpMode() {
		return otpMode;
	}
	public void setOtpMode(int otpMode) {
		this.otpMode = otpMode;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSupportPage() {
		return supportPage;
	}
	public void setSupportPage(String supportPage) {
		this.supportPage = supportPage;
	}
	public String getSmtpServer() {
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	public String getSmtpLogin() {
		return smtpLogin;
	}
	public void setSmtpLogin(String smtpLogin) {
		this.smtpLogin = smtpLogin;
	}
	public String getSmtpPassword() {
		return smtpPassword;
	}
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}
	public String getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	public String getTemplates() {
		return templates;
	}
	public void setTemplates(String templates) {
		this.templates = templates;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public int getReports() {
		return reports;
	}
	public void setReports(int reports) {
		this.reports = reports;
	}
	public int getDefaultLeverage() {
		return defaultLeverage;
	}
	public void setDefaultLeverage(int defaultLeverage) {
		this.defaultLeverage = defaultLeverage;
	}
	public double getDefaultDeposit() {
		return defaultDeposit;
	}
	public void setDefaultDeposit(double defaultDeposit) {
		this.defaultDeposit = defaultDeposit;
	}
	public int getMaxsecurities() {
		return maxsecurities;
	}
	public void setMaxsecurities(int maxsecurities) {
		this.maxsecurities = maxsecurities;
	}
	public int getSecmarginsTotal() {
		return secmarginsTotal;
	}
	public void setSecmarginsTotal(int secmarginsTotal) {
		this.secmarginsTotal = secmarginsTotal;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public int getMarginCall() {
		return marginCall;
	}
	public void setMarginCall(int marginCall) {
		this.marginCall = marginCall;
	}
	public int getMarginMode() {
		return marginMode;
	}
	public void setMarginMode(int marginMode) {
		this.marginMode = marginMode;
	}
	public int getMarginStopout() {
		return marginStopout;
	}
	public void setMarginStopout(int marginStopout) {
		this.marginStopout = marginStopout;
	}
	public double getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}
	public int getUseSwap() {
		return useSwap;
	}
	public void setUseSwap(int useSwap) {
		this.useSwap = useSwap;
	}
	public int getNews() {
		return news;
	}
	public void setNews(int news) {
		this.news = news;
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
	public int getCheckIePrices() {
		return checkIePrices;
	}
	public void setCheckIePrices(int checkIePrices) {
		this.checkIePrices = checkIePrices;
	}
	public int getMaxpositions() {
		return maxpositions;
	}
	public void setMaxpositions(int maxpositions) {
		this.maxpositions = maxpositions;
	}
	public int getCloseReopen() {
		return closeReopen;
	}
	public void setCloseReopen(int closeReopen) {
		this.closeReopen = closeReopen;
	}
	public int getHedgeProhibited() {
		return hedgeProhibited;
	}
	public void setHedgeProhibited(int hedgeProhibited) {
		this.hedgeProhibited = hedgeProhibited;
	}
	public int getCloseFifo() {
		return closeFifo;
	}
	public void setCloseFifo(int closeFifo) {
		this.closeFifo = closeFifo;
	}
	public int getHedgeLargeleg() {
		return hedgeLargeleg;
	}
	public void setHedgeLargeleg(int hedgeLargeleg) {
		this.hedgeLargeleg = hedgeLargeleg;
	}
	public int getUnusedRights() {
		return unusedRights;
	}
	public void setUnusedRights(int unusedRights) {
		this.unusedRights = unusedRights;
	}
	public String getSecuritiesHash() {
		return securitiesHash;
	}
	public void setSecuritiesHash(String securitiesHash) {
		this.securitiesHash = securitiesHash;
	}
	public int getMarginType() {
		return marginType;
	}
	public void setMarginType(int marginType) {
		this.marginType = marginType;
	}
	public int getArchivePeriod() {
		return archivePeriod;
	}
	public void setArchivePeriod(int archivePeriod) {
		this.archivePeriod = archivePeriod;
	}
	public int getArchiveMaxBalance() {
		return archiveMaxBalance;
	}
	public void setArchiveMaxBalance(int archiveMaxBalance) {
		this.archiveMaxBalance = archiveMaxBalance;
	}
	public int getStopoutSkipHedged() {
		return stopoutSkipHedged;
	}
	public void setStopoutSkipHedged(int stopoutSkipHedged) {
		this.stopoutSkipHedged = stopoutSkipHedged;
	}
	public int getArchivePendingPeriod() {
		return archivePendingPeriod;
	}
	public void setArchivePendingPeriod(int archivePendingPeriod) {
		this.archivePendingPeriod = archivePendingPeriod;
	}
	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	   
	   
	   
	   
}
