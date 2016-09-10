package com.starface.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.easemob.common.Constants;
import com.starface.frame.core.utils.DateUtils;
import com.starface.frame.core.utils.MailSender;
import com.starface.frame.core.utils.MailSenderInfo;
import com.starface.frame.core.utils.StringEx;

import freemarker.template.utility.DateUtil;




public class MainTest {

	public static void main(String args[]){
//		//发送邮件到邮箱
//		MailSenderInfo mailInfo = new MailSenderInfo();
//		mailInfo.setToAddress("353650544@qq.com");
//		String varCode = StringEx.getRandomString(20);
//		mailInfo.setSubject("星脸联盟-邮箱验证");
//		mailInfo.setContent("<html><head></head><body>亲爱的用户,点击下面的链接验证邮箱:<br/>"
//				+ "<a style='color:blue;' href='"+Constants.HOST_PATH+"/user/verificationEmail?code="
//				+varCode+"&user_id="+1+"'>"+Constants.HOST_PATH+"/user/verificationEmail?code="
//				+varCode+"&user_id="+1+"</a>,<br />欢迎使用,谢谢.</body>");
//		//这个类主要来发送邮件
//		MailSender sms = new MailSender();
//		sms.sendHtmlMail(mailInfo);
		
//		Date date = StringEx.getDate("201506");
//		System.out.println(date);
//		System.out.println(StringEx.getFirstDay());
//		System.out.println(StringEx.getLastDay());
//		Date month = DateUtils.StrToDate("201506", "yyyyMM");
//		Date lastDay = getLastDayOfMonth(month);
//		System.out.println(month);
//		System.out.println(lastDay);
//		Date firstDay = getFirstDayOfMonth(month);
//		System.out.println(firstDay.getTime());
//		System.out.println(lastDay.getTime());
//		Integer online = 1;
//		if(online != null && (online != 1 && online != 2)){
//			System.out.println(1);
//		}
		
//		File f = new File("/Users/chancore/Workspaces");
//		System.out.println(f.exists());
//		System.out.print(f.isDirectory());
		
		
		
//		int[] a = test();
//		for(int i = 0;i<a.length;i++){
//			System.out.print(a[i]+" ");
//		}
		
		
		
		
//		String abc = "8de7461a214a4f5383734306.6fad323f.8de7461a214a4f5383734.3066fad323f";
//		String bb = abc.replaceAll("\\.", "__");
//		System.out.println(bb);
//		
//		System.out.println(rangeInDefined(5,1,10));
		
		
		
		List listWithDup = new ArrayList();
		listWithDup.add("1");
		listWithDup.add("2");
		listWithDup.add("3");
		listWithDup.add("1");
		listWithDup.add("3");

		listWithDup = new ArrayList(new HashSet(listWithDup));
		System.out.println("list with dup:"+ listWithDup);
		System.out.println("list without dup:"+ listWithDup);
	}
	
	public static boolean rangeInDefined(int current, int min, int max)  
    {  
		System.out.println(Math.max(min, current));
		System.out.println(Math.min(current, max));
        return Math.max(min, current) == Math.min(current, max);  
    }
	
	
	
	
	public static int[] test() {
		int[] a = new int[100];
		for (int i = 0; i < 100; i++)
			a[i] = i + 1;
		
		for (int i = 99; i > 0; i--) {
			int temp = (int) (Math.random() * 100) % i;
			int t = a[temp];
			a[temp] = a[i];
			a[i] = t;
		}
		return a;
	}
	
	public static Date getLastDayOfMonth(Date   sDate1)   {  
        Calendar   cDay1   =   Calendar.getInstance();  
        cDay1.setTime(sDate1);  
        final   int   lastDay   =   cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);  
        Date   lastDate   =   cDay1.getTime();  
        lastDate.setDate(lastDay);  
        return   lastDate;  
	}
	
	public static Date getFirstDayOfMonth(Date sDate1)   {
        Calendar cDay1 = Calendar.getInstance();  
        cDay1.setTime(sDate1);  
        final int firstDay = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date firstDate = cDay1.getTime();  
        firstDate.setDate(firstDay);
        return firstDate;
	}

}
