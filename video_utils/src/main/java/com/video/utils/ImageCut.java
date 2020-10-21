package com.video.utils;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageCut {

    /**
     * 图片切割
     * @param imagePath  原图地址
     * @param x  目标切片坐标 X轴起点
     * @param y  目标切片坐标 Y轴起点
     * @param w  目标切片 宽度
     * @param h  目标切片 高度
     */
    public File cutImage(String imagePath, int x ,int y ,int w,int h){
        File file = null;
        File imageFile = null;
        try {
            Image img;
            ImageFilter cropFilter;
            imageFile = loadFile(imagePath);    //因为图片地址为URL，需先下载图片到本地

            // 读取源图像
            BufferedImage bi = ImageIO.read(imageFile);
            int srcWidth = bi.getWidth();      // 源图宽度
            int srcHeight = bi.getHeight();    // 源图高度

            //若原图大小大于切片大小，则进行切割
            if (srcWidth >= w && srcHeight >= h) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);

                cropFilter = new CropImageFilter(x, y, w, h);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                // 输出为文件
                file = new File(System.getProperty("user.dir")+"final"+ System.currentTimeMillis() +".jpg");   //用时间戳作为文件名可以避免并发问题
                ImageIO.write(tag, "JPEG", file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            FileUtils.deleteQuietly(imageFile);
        }
        return file;
    }

    public File loadFile(String imagePath){
        URLConnection con = null;
        OutputStream os = null;
        InputStream is = null;
        File file = null;
        try {
            // 构造URL
            URL url = new URL(imagePath);
            // 打开连接
            con = url.openConnection();
            // 输入流
            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            file = new File(System.getProperty("user.dir")+"template"+ System.currentTimeMillis() +".jpg");
            // 输出的文件流
            os = new FileOutputStream(file);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            // 完毕，关闭所有链接
            IOUtils.close(con);
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }

        return file;
    }


}