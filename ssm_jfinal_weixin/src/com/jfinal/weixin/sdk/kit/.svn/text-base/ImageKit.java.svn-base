package com.jfinal.weixin.sdk.kit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;

import com.skywayct.util.PathUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageKit {
	public static boolean lock=false;
	
	public static String getpath() throws Exception{
		String path = PathUtil.getClasspath()+ "weixin/images/";
		return path;
	}
	
	public static String qrpath(){
		String path = PathUtil.getClasspath()+ "uploadFiles/twoDimensionCode/";
		return path;
	}
	
	/** 
	   * 功能： 拷贝image从一个地址至另一个地址 
	   * @param sourcePath  
	   *                图片初始生成地址 
	   * @param toPath 
	   *                图片要复制到的地方 
	   * @param FileName 
	   *                图片的名字 
	   * @param FileN 
	   *                生成的文件夹名字 
	   */  
	  public static boolean copyToOtherPath(String sourcePath,String toPath,String FileName,String FileN){  
		try {  
		    FileChannel srcChannel = new   FileInputStream(sourcePath).getChannel();  
	        FileChannel   dstChannel   =   new   FileOutputStream(toPath+"//"+FileName).getChannel();   
	        dstChannel.transferFrom(srcChannel,0,srcChannel.size());   
	        srcChannel.close();   
	        dstChannel.close();  
	        return true;
		} catch (Exception e) {  
		    e.printStackTrace();   
		}  
		return false;
	  }
	    
	
	public static void ImageTset(String open_id,String nickname,Long validity,String qrcode,boolean activity) throws Exception {	
		while(lock){Thread.sleep(50);}
		lock = true;
		String ROOT_CLASS_PATH = getpath();
		
		File _file1 = null;
		if(!activity) 
			_file1 = new File(ROOT_CLASS_PATH + "hyk.jpg");
		else
			_file1 = new File(ROOT_CLASS_PATH + "activity.jpg");
		File _file2 = null;
		if(qrcode==null)
			_file2 = new File(ROOT_CLASS_PATH + open_id.toLowerCase() +"_skyway.png");
		else
		{
			if(copyToOtherPath(qrpath() + qrcode,ROOT_CLASS_PATH,open_id.toLowerCase() +"_skyway.png",null)){
				_file2 = new File(ROOT_CLASS_PATH + open_id.toLowerCase() +"_skyway.png");
			}
			
		}
		File _file3 = new File(ROOT_CLASS_PATH +"logo.jpg");
		if(!_file1.exists() || !_file2.exists() || !_file3.exists()) 
		{
			lock=false;
			return;
		}
		Image qr1 = javax.imageio.ImageIO.read(_file2);
		Image qr2 = javax.imageio.ImageIO.read(_file3);
		int width = qr1.getWidth(null);// 得到背景图宽
		int height = qr1.getHeight(null);// 得到背景图长
		BufferedImage tag = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		FileOutputStream out = new FileOutputStream(ROOT_CLASS_PATH + open_id.toLowerCase() +"_skyway2.png");// 输出到文件流
		Graphics g = tag.createGraphics();
		g.fillRect(0, 0, width, height);
		g.setClip(0, 0, width, height);
		g.drawImage(qr1, 0, 0, width, height, null); //绘制背景图
		g.drawImage(qr2, 185, 185, 60, 60, null);  //绘制第二张图到背景图
		g.dispose();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(tag);// 近JPEG编码
		out.close();
		_file2.delete();
		
		
		_file2 = new File(ROOT_CLASS_PATH + open_id.toLowerCase() +"_skyway2.png");
		if(_file2.renameTo(new File(ROOT_CLASS_PATH + open_id.toLowerCase() +"_skyway.png")))
		{
			_file2 = new File(ROOT_CLASS_PATH + open_id.toLowerCase() +"_skyway.png");
			Image src = javax.imageio.ImageIO.read(_file1);// 构造Image对象
			Image src2 = javax.imageio.ImageIO.read(_file2);
			Image src3 = javax.imageio.ImageIO.read(_file3);
			width = src.getWidth(null);// 得到背景图宽
			height = src.getHeight(null);// 得到背景图长

			tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			out = new FileOutputStream(ROOT_CLASS_PATH + open_id.toLowerCase()+"_qr.png");// 输出到文件流
			g = tag.createGraphics();
			g.fillRect(0, 0, width, height);
			g.setClip(0, 0, width, height);
			g.drawImage(src, 0, 0, width, height, null); //绘制背景图
			
			//g.drawImage(src3, 305, 365, 50, 50, null); //绘制第三张图片
			Font font = new Font("宋体", Font.BOLD, 24); 
			g.setFont(font);
	        g.setColor(Color.white);
	        
	        if(!activity){
	        	g.drawImage(src2, 195, 260, 264, 264, null);  //绘制第二张图到背景图
	        	g.drawString(nickname,340,903);  //绘制文字信息
				if(qrcode==null)
					g.drawString("有效期至 "+new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(validity)), 210, 603);
				else
					g.drawString("永久有效", 280, 603);
				g.dispose();
	        }else
	        {
	        	Font font2 = new Font("宋体", Font.BOLD, 26); 
				g.setFont(font2);
		        g.setColor(Color.white);
	        	g.drawImage(src2, 220, 435, 200, 200, null);  //绘制第二张图到背景图
	        	
	        	int len = nickname.length();
	        	if(len > 5){
	        		len = 5;
	        	}
	        	g.drawString("我是 "+(nickname.substring(0,len)),80,295);  //绘制文字信息
	        }
			
			encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);// 近JPEG编码
			out.close();
			lock = false;
		}
		
	}
 
      
}
