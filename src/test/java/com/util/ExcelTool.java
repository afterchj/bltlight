package com.util;

import com.tpadsz.after.entity.Pid;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.mybatis.spring.SqlSessionTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongjian.chen on 2018/11/26.
 */
public class ExcelTool {

    /**
     * 将excel文件导出到数据库表中
     * fileName excel文件位置
     */
    public static List<Pid> exportExcel(SqlSessionTemplate sessionTemplate, String filePath) throws IOException, BiffException, WriteException {
        List<Pid> list = new ArrayList();
        Workbook rwb = null;
        try {
            InputStream is = new FileInputStream(filePath);//定义文本输入流
            rwb = Workbook.getWorkbook(is);//打开Workbook
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取Excel表的Sheet1区域的数据
        Sheet sht = rwb.getSheet(0);
        int col = sht.getColumns(); //获得Excel列
        int row = sht.getRows(); //获得Excel行
        System.out.println("row=" + row);

        //先将数据按行装入一个一维数组中， 然后将数组逐个加入到ArrayList
        for (int i = 0; i < row; i++) {
            Pid pid = new Pid();
            for (int j = 0; j < col; j++) {
                pid.setPkey(Integer.parseInt(sht.getCell(0, i).getContents()));
                String str = sht.getCell(1, i).getContents();
                pid.setPid(str);
                String ad_zone_id = str.substring(str.lastIndexOf("_")+1, str.length());
                pid.setAdzone_id(ad_zone_id);
            }
            list.add(pid);
        }
        sessionTemplate.insert("tbk.insertPid", list);

        return list;
    }

    /**
     * 将数据库表数据导入到excel文件中
     * fileName 文件保存位置
     */
    public static void importExcel(SqlSessionTemplate session, String fileName) {
        List<Pid> list = session.selectList("tbk.getPids");
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(new File(fileName));
            WritableSheet sheet = book.createSheet("test", 0);
            sheet.addCell(new Label(0, 0, "key"));
            sheet.addCell(new Label(1, 0, "pid"));
            sheet.addCell(new Label(2, 0, "zone"));
            for (int i = 0; i < list.size(); i++) {
                sheet.addCell(new jxl.write.Number(0, i + 1, list.get(i).getPkey()));
                sheet.addCell(new Label(1, i + 1, list.get(i).getPid()));
                sheet.addCell(new Label(2, i + 1, list.get(i).getAdzone_id()));
            }
            book.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }
}
