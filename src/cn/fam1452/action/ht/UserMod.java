/**
 * 描述：
 */
package cn.fam1452.action.ht;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.Scanpic;
import cn.fam1452.service.UserService;
import cn.fam1452.utils.FileUtil;
import cn.fam1452.utils.MD5Util;
import cn.fam1452.utils.RandomImage;
import cn.fam1452.utils.StringUtil;

/**
 * Class User
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 18, 2012 10:59:23 PM $
 */
@IocBean
public class UserMod  extends BaseMod{
	
	@Inject("refer:userService")
	private UserService userservice ;

	@At("/ht/index")
    @Ok("jsp:jsp.ht.login")
    public void loadlogin(HttpServletRequest req){
		
	}
	
	/**
	 * 权限不足 警告页面
	 * @param req
	 */
	@At("/ht/warning")
    @Ok("jsp:jsp.ht.warning")
    public void warning(HttpServletRequest req){ }
	
	@At("/ht/lang/?")
	@Ok("redirect:/ht/index.do") 
    public void lang(String lang , HttpSession session ){
		//log.info(lang) ;
		//Mvcs.setLocaleName(session, lang) ;
		//Mvcs.setLocale(session , lang) ;
		
		Mvcs.setLocalizationKey(lang) ;
		return  ;
	}
	
	@At("/ht/loadhd")
	@Ok("jsp:jsp.ht.header") 
    public void loadHead(){  }
	
	public void loginCode(){}
	/**
	 * 用户登出
	 */
	@At("/ht/logout")
	@Ok("redirect:/ht/index.do")
	public void logout(HttpSession session) {
		session.removeAttribute(Constant.HT_USER_SESSION);
		session.invalidate() ;
		//Mvcs.deposeSession(session) ;
	}
	
	@At("/ht/login")
	@Ok("json")
	@POST
	public JSONObject login(HttpSession session ,HttpServletRequest req, @Param("..")Administrator admin){
		//Map<String,String> map = (Map<String,String>)req.getAttribute ("msg") ;
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		String code = (String)session.getAttribute(Constant.LOGIN_VALIDATE_STRING) ;
		if(StringUtil.checkNotNull(admin.getCode()) && admin.getCode().equalsIgnoreCase(code)){
			
			if(StringUtil.checkNotNull(admin.getLoginId()) &&
					StringUtil.checkNotNull(admin.getPassword())){
				Administrator db = userservice.queryAdmin(admin.getLoginId()) ;
				if(null == db ){
					json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_nameerror")) ;
				} else{
					if(db.getPassword().equals(MD5Util.tomd5(admin.getPassword())) ){
						json.put(Constant.SUCCESS, true) ;
						db.setLogin(true) ;
						session.setAttribute(Constant.HT_USER_SESSION, db) ;
					}else{
						json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_passerror")) ;
					}
				}
					
			}
		}else{
			json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_codeerror")) ;
		}
		
		
		return json;
	}
	
	/**
	 * 登录验证码
	 */
	@At("/ht/logincode")
	@Ok("raw")
	public void imageCode(HttpServletRequest req , HttpServletResponse response)throws ServletException, IOException{
		//生成100px*22px的包含6个字符的验证码
    	HttpSession session = req.getSession();
        RandomImage validateImage = new RandomImage(5, 100, 22);
        
        OutputStream bos = response.getOutputStream();
        response.setHeader("cache-control", "no-store");
        ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
        ImageIO.write(validateImage.getValidateImage(), "JPEG", ios);
        session.setAttribute(Constant.LOGIN_VALIDATE_STRING,validateImage.getValidateString());
        
        ios.close();
        bos.close();
	}
	
	@At("/qt/getimage")
    @Ok("raw")
	public void getImage(HttpServletResponse response,String imageid ,String tab)throws ServletException, IOException{
		//默认图片
		String image = "" ;
		if(StringUtil.checkNotNull(imageid)){
			if("pgt".equals(tab)){
				IronoGram ig = userservice.dao.fetch(IronoGram.class, imageid) ;
				if(null != ig){
					image = ig.getGramPath() ;
				}
			}else if("sac".equals(tab)){
				Scanpic sac = userservice.dao.fetch(Scanpic.class, imageid) ;
				if(null != sac){
					image = sac.getGramPath() ;
				}
			}
		}
		
		OutputStream bos = response.getOutputStream();
        response.setHeader("cache-control", "no-store");
        ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
        
        File imagefile = new File(image) ;
        if(imagefile.exists() && imagefile.isFile()){
        	ImageIO.write(FileUtil.getImg(imagefile), "JPEG", ios);//FileUtil.getFileSuffix(imagefile) ;
        }else{
        	int width = 600 ;
        	int height = 400 ;
        	BufferedImage bfimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// 在内存中创建图象
            Graphics2D raphics = (Graphics2D) bfimage.getGraphics();// 获取图形上下文
            raphics.setColor(new Color(250,250,250));// 设定为白色背景色
            raphics.fillRect(0, 0, width, height);
            raphics.setFont(new Font("Dialog", Font.ITALIC, 22));// 设定字体
            raphics.setColor(Color.BLACK);// 设置为黑色字体
            raphics.drawString("图片没找到", 30, 100);
            raphics.dispose(); // 图象生效
            
            ImageIO.write(bfimage, "JPEG", ios);
        }
        
        ios.close();
        bos.close();
	}
	
	
}
