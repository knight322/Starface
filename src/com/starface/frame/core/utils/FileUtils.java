package com.starface.frame.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component(value ="fileUtils")
public class FileUtils {
	private static final Logger log = Logger.getLogger(FileUtils.class);
	
	@Autowired
	private HttpServletRequest request; //这里可以获取到request
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private ImageUtil imageUtil;

	/**
	 * 文件上传
	 * @param file 文件
	 * @param folder 目录
	 * @param fileName
	 * @return
	 */
	public String fileUpload(File file,String folder,String fileName){
		String result = null;
		String inputDir = null;
		String inputFileName = null;
		try {
			if(file !=null){
				InputStream inputstream =  new FileInputStream(file);
				String filePath = request.getSession().getServletContext().getRealPath("/");
				String theMonth = StringEx.dateFormate("yyyy-MM");
				File pathFile = new File(filePath + folder + "/"+ theMonth+"/");
				
				//创建文件夹
				boolean flag = true;
				if(!pathFile.exists()){
					flag = pathFile.mkdirs();
				}
				if(flag){
					File savePath = new File(pathFile,fileName);
					OutputStream outputStream = new FileOutputStream(savePath);
					inputDir = filePath+folder + "/"+ theMonth+"/";
					result = folder + "/"+ theMonth+"/" + fileName;
					inputFileName = fileName;
					byte buffer[]=new byte[1024];
					int length = 0;
					try {
						while((length=inputstream.read(buffer))> 0 ){
							
							outputStream.write(buffer, 0, length);
						}
						inputstream.close();
						outputStream.close();
					} catch (IOException e) {
						System.out.println("上传文件出错");
						e.printStackTrace();
					}
				}else{
					System.out.println("文件夹 :"+pathFile.getPath()+"创建失败.");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 上传文件
	 * @param file MultipartFile 
	 * @param folder 文件夹
	 * @return
	 */
	public String fileUpload(MultipartFile file,String folder){
		String result = null;
		try {
			if(file !=null){
				String filePath = request.getSession().getServletContext().getRealPath("/");
				String theMonth = StringEx.dateFormate("yyyy-MM");
				String folderPaht = folder + "/"+ theMonth+"/";
				File pathFile = new File(filePath + folderPaht);
				//创建文件夹
				boolean flag = true;
				if(!pathFile.exists()){
					flag = pathFile.mkdirs();
				}
				if(flag){
					String randomName = UUID.randomUUID().toString().replace("-", "");
					String fileOldName  = file.getOriginalFilename();
					System.out.println(fileOldName);
					String _ext = file.getOriginalFilename().substring(fileOldName.lastIndexOf("."));
					String fileName = randomName + _ext;
					
					File savePath = new File(pathFile,fileName);
					org.apache.commons.io.FileUtils.writeByteArrayToFile(savePath, file.getBytes());
					result = folderPaht+fileName;
				}else{
					System.out.println("文件夹 :"+pathFile.getPath()+"创建失败.");
					return null;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 上传文件并压缩
	 * @param file MultipartFile 
	 * @param folder 文件夹
	 * @return
	 */
	public FileBean fileUploadAndResized(MultipartFile file,String folder){
		FileBean fileBean = new FileBean();
		try {
			if(file !=null){
				String filePath = request.getSession().getServletContext().getRealPath("/");
				String theMonth = StringEx.dateFormate("yyyy-MM");
				String folderPaht = folder + "/"+ theMonth+"/";
				File pathFile = new File(filePath + folderPaht);
				//创建文件夹
				boolean flag = true;
				if(!pathFile.exists()){
					flag = pathFile.mkdirs();
				}
				if(flag){
					String randomName = UUID.randomUUID().toString().replace("-", "");
					String fileOldName  = file.getOriginalFilename();
					System.out.println(fileOldName);
					String _ext = file.getOriginalFilename().substring(fileOldName.lastIndexOf("."));
					String fileName = randomName + _ext;
					
					File savePath = new File(pathFile,fileName);
					org.apache.commons.io.FileUtils.writeByteArrayToFile(savePath, file.getBytes());
					String result = folderPaht+fileName;
					
					String resizedFilePath = folderPaht+randomName+"_small"+_ext;
					//压缩图片
					imageUtil.resize(result, resizedFilePath, 500, 1f);
					fileBean.setPath(result);
					fileBean.setSmallPath(resizedFilePath);
				}else{
					System.out.println("文件夹 :"+pathFile.getPath()+"创建失败.");
					return null;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileBean;
	}
	
	
	/**
	 * 删除文件
	 * @param filePath 文件全路径
	 */
	public static boolean delete_file(String filePath){
		boolean flag = true;
		if(null == filePath){
			flag = false;
			return flag;
		}
		// 路径为文件且不为空则进行删除  
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			file.delete();
		}
		return flag;
	}
	
	
	/**   
     * 文件下载   
     * @param response   
     * @param str   
     */  
    public void downFile(String basePath , String fileName) {
        try {
        	response.reset();
        	String path = basePath+fileName;
            File file = new File(path);
            if (file.exists()) {
                InputStream ins = new FileInputStream(path);    
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流    
                BufferedOutputStream bouts = new BufferedOutputStream(outs); 
                response.setContentType("application/x-download");// 设置response内容的类型    
                response.setHeader("Content-disposition","attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));// 设置头部信息    
                int bytesRead = 0;    
                byte[] buffer = new byte[8192];    
                // 开始向网络传输文件流    
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {    
                    bouts.write(buffer, 0, bytesRead);    
                }    
                bouts.flush();// 这里一定要调用flush()方法    
                ins.close();    
                bins.close();    
                outs.close();    
                bouts.close();    
            } else {    
                response.sendRedirect("../error.jsp");    
            }    
        } catch (IOException e) {    
            System.out.println("文件下载出错了");
            e.printStackTrace();
        }    
    } 
}