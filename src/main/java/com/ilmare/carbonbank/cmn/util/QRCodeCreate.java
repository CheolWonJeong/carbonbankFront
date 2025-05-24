package com.ilmare.carbonbank.cmn.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeCreate {

	private static int width = 300;
	private static int height = 300;
	
    public static void main(String[] args) throws Exception { 
    	try
		{
        	String outputDir = "D:\\app\\data\\upload";
		    String qrContent = "M0000000001";
		    String logoPath = "D:\\myProject\\Carbon(카본)\\설계\\받은디자인\\carbonbank\\resources\\images\\appico.png";
		    byte[] qrImgBytes = generateQRCodeImage(qrContent, null);
		    
		    String fileName = "qrMain_" + System.currentTimeMillis() + ".png";
		    String tmpDir = outputDir +"\\"+ fileName;
            // 파일 저장
		    try (FileOutputStream fos = new FileOutputStream(tmpDir)) {
	            fos.write(qrImgBytes);
	            System.out.println("이미지 저장 완료: " + outputDir);
	        }		    
/*
		    String fileName = "qrMain_" + System.currentTimeMillis() + ".png";
            Path filePath = Paths.get(outputDir, fileName);
            Files.createDirectories(filePath.getParent());
            ImageIO.write(qrBytes, "png", filePath.toFile());
*/            

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}        
	public static  byte[] generateQRCodeImage(String content, String logoPath) throws Exception {
            //int width = 300;
            //int height = 300;
        	String outputDir = "D:\\app\\data\\upload";
            BitMatrix matrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageConfig config = new MatrixToImageConfig(0xFF0000FF, 0xFFFFFFFF); // Blue QR with white bg
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix, config);

            // 로고 삽입
            if (logoPath != null) {
                BufferedImage logo = ImageIO.read(new File(logoPath));
                int deltaHeight = qrImage.getHeight() - logo.getHeight();
                int deltaWidth = qrImage.getWidth() - logo.getWidth();

                Graphics2D g = qrImage.createGraphics();
                g.drawImage(logo, deltaWidth / 2, deltaHeight / 2, null);
                g.dispose();
            }

/*
            // 파일 저장
            String fileName = "qr_" + System.currentTimeMillis() + ".png";
            Path filePath = Paths.get(outputDir, fileName);
            Files.createDirectories(filePath.getParent());
            ImageIO.write(qrImage, "png", filePath.toFile());
*/
            // byte[]로 변환
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "png", baos);
            byte[] qrBytes = baos.toByteArray();

            return qrBytes;
        }
    
}
