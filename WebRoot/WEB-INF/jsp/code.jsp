<%@ page contentType="image/png" 
import="java.awt.*,
java.awt.image.*,
cn.fam1452.Constant ,
java.util.*,
javax.imageio.*" %>

<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	
	int width=50;
	int height=20;
	
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	Graphics g = image.getGraphics();              
	g.setColor(new Color(238,243,205));//背景色
	g.fillRect(0, 0, width, height);                //
	
	//g.setColor(new Color(153, 153, 153));           //边框色
	//g.drawRect(0, 0, width-1, height-1);            //
	
	g.setFont(new Font("Arial", Font.PLAIN, 16));   //输出字体设置
	Random random = new Random();                   //
	StringBuffer sbRan = new StringBuffer();        //
	for (int i=0; i<4; i++){
	    String ranNum = String.valueOf(random.nextInt(10));
	    sbRan.append(ranNum);
	    
	  	//字体随即颜色
	    g.setColor(new Color(random.nextInt(250), random.nextInt(250), random.nextInt(250)));
	    g.drawString(ranNum, 10 * i + 5, 16);
	}
	g.dispose();
	
	session.setAttribute(Constant.HT_LOGIN_CODE_SESSION, sbRan.toString());           //
	
	ImageIO.write(image, "PNG", response.getOutputStream());    //
	out.clear();
	out = pageContext.pushBody();

%>