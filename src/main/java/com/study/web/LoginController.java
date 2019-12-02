package com.study.web;

import com.study.domain.User;
import com.study.service.UserService;
import com.study.util.ImageVerificationCode;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    public final static int USERNAME_NOT_FOUND = -12;
    public final static int VERIFYCODE_ERROR =-20;
    public final static int PASSWORD_ERROR = -30;
    public final static int SUCCESS = 1;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/loginCheck",method = RequestMethod.POST)
    @ResponseBody
    public int loginCheck(@RequestBody User user, WebRequest request, HttpServletRequest req){
        /*System.out.println(user.toString());
        System.out.println(request.getParameter("code"));
        System.out.println(req.getSession().getAttribute("verifyCodeValue"));*/
        User userRes = userService.selectByUserName(user.getUsername());

        if(!request.getParameter("code").equals(req.getSession().getAttribute("verifyCodeValue"))){
            return VERIFYCODE_ERROR;
        }else if(userRes == null){
            return USERNAME_NOT_FOUND;
        }else if(!user.getPassword().equals(userRes.getPassword())){
            return PASSWORD_ERROR;
        }
        /*req.getSession().setAttribute("username", true);
        req.getSession().setMaxInactiveInterval(30*60);*/
        /*登录成功，用户信息放到session中*/
        HttpSession session = req.getSession();
        session.setAttribute("username",userRes.getUsername());
        session.setAttribute("uid",userRes.getUid());
        /*System.out.println("成功");
        System.out.println("user类：："+userRes.toString());*/
        return SUCCESS;
    }

    @RequestMapping("/getVerifyCode")
    /*
     * 后端生成验证码
     * */
    public void generate(HttpServletResponse response, HttpSession session) {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String verifyCodeValue = drawImg(output);
        // 将校验码保存到session中
        session.setAttribute("verifyCodeValue", verifyCodeValue);

        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            logger.info("<--验证码前端写出.出现异常-->");
            e.printStackTrace();

        }
    }

    /* 绘制验证码 */
    private String drawImg(ByteArrayOutputStream output) {
        String code = "";
        // 随机产生4个字符
        for (int i = 0; i < 4; i++) {
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        // 调用Graphics2D绘画验证码
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66, 2, 82);
        g.setColor(color);
        g.setBackground(new Color(226, 226, 240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /* 获取随机参数 */
    private char randomChar() {
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }
}
