package com.ilmare.carbonbank.cmn.mgr;

import org.springframework.stereotype.Component;

import ch.qos.logback.core.util.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionManager
{
    private SessInfo sess = null;
 
    private HttpSession httpSession;
    protected final static String ILMARE_SESS_INFO = "SESS_INFO";
 
    public boolean isFindSession( HttpServletRequest req, String sessName )
    {
        if (httpSession != null)
        {
            if (isSession(sessName)) return true;
        }
        return false;
    }
 
 
    public void deleteSession( HttpServletRequest req )
    {
        createSession( req, false );
        if ( httpSession != null )
            httpSession.invalidate();
    }
 
    public void createSessionInfo( HttpServletRequest req, SessInfo sessinfo )
    {
        sessionInit( req, sessinfo );
    }
 
 
    private HttpSession getHttpSession()
    {
        return httpSession;
    }
 
 
    public boolean isSession( String sessName )
    {
        if ( getHttpSession() != null && getHttpSession().getAttribute(sessName) != null ) {
            return true;
        } else return false;
    }
 
 
    public boolean isSession()
    {
        if ( isSession(ILMARE_SESS_INFO) ) {
            if( StringUtil.isNullOrEmpty( getSessInfo().getMbrId()) ) return false;
            return true;
        }
        return false;
    }
 
 
    public SessInfo getSessInfo() {
        return getSessInfo( false );
    }
 
 
    private SessInfo getSessInfo( boolean isNew )
    {
        if (sess == null) {
            if ( getHttpSession() == null ) return sess;
            sess = (SessInfo) getHttpSession().getAttribute(ILMARE_SESS_INFO);
            if ( sess == null & isNew ) {
                sess = new SessInfo();
                setSessInfo(sess);
            }
        }
        return sess;
    }
    
    public Object getEtcSession(String sessionName) {
        if ( isSession(sessionName) ) {
            return getHttpSession().getAttribute(sessionName);
        }
        return null;
    }
 
 
    protected void setSessInfo( SessInfo SessInfo ) {
        getHttpSession().setAttribute(ILMARE_SESS_INFO, SessInfo);
    }
    
    public void setEtcSession( String sessionName, Object sessionVal ) {
        getHttpSession().setAttribute(sessionName, sessionVal);
    }
 
    public void removeEtcSession( String sessionName) {
        getHttpSession().removeAttribute(sessionName);
    }

    public void sessionInit( HttpServletRequest req, SessInfo sessinfo )
    {
    	deleteSession( req );
    	httpSession = req.getSession();
 
    	httpSession.setAttribute("mbrId", (StringUtil.nullStringToEmpty(sessinfo.getMbrId())));       // 아이디
    	httpSession.setAttribute("mbrNm", (StringUtil.nullStringToEmpty(sessinfo.getMbrNm())));       // 이름
    	httpSession.setAttribute("partyCd",  (StringUtil.nullStringToEmpty(sessinfo.getPartyCd())));     // 소속코드    
    	httpSession.setAttribute("dgtQrCd", (StringUtil.nullStringToEmpty(sessinfo.getDgtQrCd())));    //디지털 QR      
    	httpSession.setAttribute("PprQrCd",  (StringUtil.nullStringToEmpty(sessinfo.getPprQrCd())));    //종이 QR         
    	httpSession.setAttribute("lstLgnDtm",  (StringUtil.nullStringToEmpty(sessinfo.getLstLgnDtm())));	//최근 로그인 일시로그인    
    	httpSession.setAttribute("storeId",  (StringUtil.nullStringToEmpty(sessinfo.getStoreId())));	//가입된 가맹점 ID      
    	httpSession.setAttribute("loginInd",  (StringUtil.nullStringToEmpty(sessinfo.getLoginInd())));	//로그인 구분:M:회원, S:가맹점, A:회원, 가맹점 둘다 가입 

    }
 
    public void createSession( HttpServletRequest req, boolean create )
    {
        httpSession = req.getSession( create );
    }

    
    public void createSession( HttpServletRequest req, SessInfo sessinfo )
    {
    	httpSession  = req.getSession();
 
    	httpSession.setAttribute("mbrId", (StringUtil.nullStringToEmpty(sessinfo.getMbrId())));       // 아이디
    	httpSession.setAttribute("mbrNm", (StringUtil.nullStringToEmpty(sessinfo.getMbrNm())));       // 이름
    	httpSession.setAttribute("partyCd",  (StringUtil.nullStringToEmpty(sessinfo.getPartyCd())));     // 소속코드    
    	httpSession.setAttribute("dgtQrCd", (StringUtil.nullStringToEmpty(sessinfo.getDgtQrCd())));    //디지털 QR      
    	httpSession.setAttribute("PprQrCd",  (StringUtil.nullStringToEmpty(sessinfo.getPprQrCd())));    //종이 QR         
    	httpSession.setAttribute("lstLgnDtm",  (StringUtil.nullStringToEmpty(sessinfo.getLstLgnDtm())));	//최근 로그인 일시로그인     
    	httpSession.setAttribute("storeId",  (StringUtil.nullStringToEmpty(sessinfo.getStoreId())));	//가입된 가맹점 ID      
    	httpSession.setAttribute("loginInd",  (StringUtil.nullStringToEmpty(sessinfo.getLoginInd())));	//로그인 구분:M:회원, S:가맹점, A:회원, 가맹점 둘다 가입 

    }
    

    public SessInfo getSession( HttpServletRequest req )
    {
    	httpSession  = req.getSession();
    	SessInfo sessinfo = new SessInfo(); 
    	if ( httpSession == null)  return sessinfo;
 
    	sessinfo.setMbrId(StringUtil.nullStringToEmpty((String)httpSession.getAttribute("mbrId")));       // 아이디
    	sessinfo.setMbrNm(StringUtil.nullStringToEmpty((String)httpSession.getAttribute("mbrNm")));       // 이름
    	sessinfo.setPartyCd(StringUtil.nullStringToEmpty((String)httpSession.getAttribute("partyCd")));     // 소속코드   
    	sessinfo.setDgtQrCd(StringUtil.nullStringToEmpty((String)httpSession.getAttribute("dgtQrCd")));     // 디지털 QR      
    	sessinfo.setPprQrCd(StringUtil.nullStringToEmpty((String)httpSession.getAttribute("pprQrCd")));    // 종이 QR  
    	sessinfo.setLstLgnDtm(StringUtil.nullStringToEmpty((String)httpSession.getAttribute("lstLgnDtm")));    //최근 로그인 일시로그인
    	sessinfo.setStoreId(StringUtil.nullStringToEmpty((String)httpSession.getAttribute("storeId")));    //가입된 가맹점 ID 
    	sessinfo.setLoginInd(StringUtil.nullStringToEmpty((String)httpSession.getAttribute("loginInd")));    //로그인 구분:M:회원, S:가맹점, A:회원, 가맹점 둘다 가입 
    	return sessinfo;

    }
    
    public boolean isSession(HttpServletRequest req)
    {
    	httpSession  = req.getSession();
    	if ( httpSession == null)  return false;
        if ( StringUtil.nullStringToEmpty((String)httpSession.getAttribute("mbrId")).length() < 2 ) {
            return false;
        }
        return true;
    }
 
    
}
