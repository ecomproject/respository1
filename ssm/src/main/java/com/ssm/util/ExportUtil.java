package com.ssm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

// xmlbeans-2.3.0.jar
public class ExportUtil {

	/**
	 * 导出人员列表
	 * @param arrayList
	 * @return
	 */
	public String exportBaseInfo(HttpServletRequest request, String arrayList){
	
        List<PersonBaseInfoExport> list = new  ArrayList<PersonBaseInfoExport>();
        PersonBaseInfoExport info = new PersonBaseInfoExport();
        
        info.org_name = "财务部";
        info.user_name = "张**";
        info.postion_name = "处长";
        list.add(info);
        
		exportExcel(request,list);
		return "true";
	}	
	
	/**
	 * 导出人员信息公用类
	 * 
	 * @param params
	 * @return
	 */
	public String exportExcel(HttpServletRequest request, List params) {
		// 文件命名【人员信息[201703171121]】
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = format.format(new Date());
		String str = "人员信息[" + date + "].xlsx";
		// 获得项目的相对路径
//		String contextPath = request.getSession().getServletContext().getRealPath("/");
		// D:\rsxtkf1\test\trunk\war\excelfile
		 String contextPath = "D:/";
		String courcePath = contextPath + "personBaseInfo.xlsx";
		String destPath = contextPath + "excelfileExport/";
		boolean flag = copyfile(str, courcePath, destPath);
		if (flag == false) {
			return "复制Excel模板失败，或文件已存在！";
		}
		List<PersonBaseInfoExport> list = params;
		try {
			OutputStream out = new FileOutputStream((destPath + str).trim());

			XSSFWorkbook workbook = new XSSFWorkbook();
			// 设置内容样式
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 居中
			XSSFFont font = workbook.createFont(); // 设置字体
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 10);
			style.setFont(font);
			// 设置标题行样式
			XSSFCellStyle style1 = workbook.createCellStyle();
			style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			XSSFFont font1 = workbook.createFont();
			font1.setFontName("宋体");
			font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体
			font1.setFontHeightInPoints((short) 12);
			style1.setFont(font1);

			// 生成一个表格
			XSSFSheet sheet = workbook.createSheet("人员列表");
			XSSFRow row = null;

			// 产生表格标题行
			row = sheet.createRow(0);
			String[] headers = { "部门", "姓名", "职务", "任现职时间", "任同级时间", "任同职时间", "专业技术职务", "性别", "名族", "出生年月", "年龄", "籍贯",
					"学历", "学位", "参加工作时间", "入党时间", "来厅时间", "来本部门时间" };
			// String[] params = { "Org_name", "User_name", "Postion_name",
			// "Decide_approve_post_time","Hold_currentpost_time",
			// "Same_level_time","The_qualification_name","Sex","Race","Birthday","Age","Nativeplace","Education_code","EduDegreeColumn","Work_time","Joinparth_time","Come_here_time","Join_same_org_time"
			// };
			for (short i = 0; i < headers.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(style1);

				XSSFRichTextString text = new XSSFRichTextString(headers[i]);
				cell.setCellValue(text);
				sheet.setColumnWidth(i, 2500);// 设置宽度
				if ("姓名".equals(headers[i]) || "性别".equals(headers[i]) || "名族".equals(headers[i])
						|| "年龄".equals(headers[i]) || "籍贯".equals(headers[i]) || "学历".equals(headers[i])
						|| "学位".equals(headers[i])) {
					sheet.setColumnWidth(i, 1500);
				}
				// 设置边框
				style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
				style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
				style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
				style1.setBorderRight(XSSFCellStyle.BORDER_THIN);

			}
			// 遍历集合数据，产生数据行
			Iterator<PersonBaseInfoExport> it = list.iterator();
			int index = 0;
			while (it.hasNext()) {
				index++;
				// 设置边框
				style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
				style.setBorderTop(XSSFCellStyle.BORDER_THIN);
				style.setBorderRight(XSSFCellStyle.BORDER_THIN);

				row = sheet.createRow(index);
				PersonBaseInfoExport t = (PersonBaseInfoExport) it.next();
				// 部门
				XSSFCell cell = row.createCell(0);
				cell.setCellStyle(style);
				XSSFRichTextString richString = new XSSFRichTextString(t.getOrg_name());
				cell.setCellValue(richString);
				// 姓名
				XSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(style);
				XSSFRichTextString richString1 = new XSSFRichTextString(t.getUser_name());
				cell1.setCellValue(richString1);
				// 职位
				XSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(style);
				XSSFRichTextString richString2 = new XSSFRichTextString(t.getPostion_name());
				cell2.setCellValue(richString2);
			}
			System.out.println("excel导出成功！");
			workbook.write(out);
			out.close();
			//request.getSession().setAttribute("baseinfoFileName", str);
		} catch (SecurityException e) {
			e.printStackTrace();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "导出失败";
	}

	/**
	 * 复制excel模板
	 * 
	 * @param str
	 * @param courcePath
	 * @param destPath
	 * @return
	 */
	public boolean copyfile(String str, String courcePath, String destPath) {
		boolean flg = false;
		String destFilePath = destPath + str;
		String courceFilePath = courcePath;
		try {
			File newDir = new File(destFilePath.substring(0, destFilePath.lastIndexOf('/')));
			File sourceFile = new File(courceFilePath.trim());
			if (!sourceFile.exists())
				return flg;
			File destFile = new File(destFilePath.trim());
			if (!newDir.exists())
				newDir.mkdirs();
			if (!sourceFile.isDirectory()) {
				InputStream in = new FileInputStream(sourceFile);
				FileOutputStream out = new FileOutputStream(destFile);
				byte[] buffer = new byte[1024];
				int ins;
				while ((ins = in.read(buffer)) != -1) {
					out.write(buffer, 0, ins);
				}
				in.close();
				out.flush();
				out.close();
				flg = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flg;
	}

}
