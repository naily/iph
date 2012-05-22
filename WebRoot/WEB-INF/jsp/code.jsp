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

int width=40;   
int height=18;  

BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

Graphics g = image.getGraphics();              
g.setColor(new Color(238,243,205));           //
g.fillRect(0, 0, width, height);                //

//g.setColor(new Color(153, 153, 153));           //
g.drawRect(0, 0, width-1, height-1);            //
g.setFont(new Font("Arial", Font.PLAIN, 16));   //
Random random = new Random();                   //
//
StringBuffer sbRan = new StringBuffer();        //
for (int i=0; i<3; i++){
    String ranNum = String.valueOf(random.nextInt(10));
    sbRan.append(ranNum);
    g.setColor(new Color(000, 000, 000));
    g.drawString(ranNum, 10 * i + 5, 16);
}

g.dispose();

session.setAttribute(Constant.HT_LOGIN_CODE_SESSION, sbRan.toString());           //

ImageIO.write(image, "PNG", response.getOutputStream());    //
out.clear();
out = pageContext.pushBody();

%>