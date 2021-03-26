package it.world.service.utils;


import it.world.common.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 对excle读写与导出
 */
@Slf4j
public class ExcelUtils {
    /***
     * 读取Excel文件  =>类中的属性顺序要与excel中的cell顺序一致！
     * @param filePath  文件路径
     * @return List<List<Object>>     读取到的数据
     * @throws Exception
     */
    public static List read(String filePath, Class clz) throws Exception {
        CheckUtils.fileCheck(filePath,"xls","xlsx");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("开始读取文件："+simpleDateFormat.format(System.currentTimeMillis()));

        List<Object> list = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet=workbook.getSheetAt(i);
            if(sheet==null){
                continue;
            }
            Row row ;
            Cell cell ;
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                // 读取一行
                row = sheet.getRow(j);
                // 去掉空行和表头
                /*if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }*/
                if(row==null){
                    continue;
                }
                // 遍历所有的列

                Object obj=clz.newInstance();
                Field[] fields=obj.getClass().getDeclaredFields();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    Field field=fields[y];
                    field.setAccessible(true);
                    if(field.getName().equals("id")){
                        continue;
                    }
                    field.set(obj, getCellValue(row.getCell(y)));
                }
                list.add(obj);
            }

        }
        return list;
    }
    /**
     * 描述：对表格中数值进行格式化
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0"); // 格式化字符类型的数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); // 日期格式化
        DecimalFormat df2 = new DecimalFormat("0"); // 格式化数字
        if (cell.getCellType() == CellType.STRING) {
            value = cell.getRichStringCellValue().getString();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                value = df.format(cell.getNumericCellValue());
            } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                value = sdf.format(cell.getDateCellValue());
            } else {
                value = df2.format(cell.getNumericCellValue());
            }
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            value = cell.getBooleanCellValue();
        } else if (cell.getCellType() == CellType.BLANK) {
            value = "";
        }
        return value;
    }



    /**
     * 导出数据成Excel文件
     * @param filePath
     * @param list
     * @throws IOException
     */
    public static void write(String filePath, List<?> list) throws IOException {
        File file=new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("开始导出数据到Excle文件："+simpleDateFormat.format(System.currentTimeMillis()));

        String suffiex=filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
        Workbook workbook;
        if ("xls".equals(suffiex.toLowerCase())) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        for (int rowNum=0;rowNum<list.size();rowNum++){
            Row row = sheet.createRow(rowNum);
            Object obj= list.get(rowNum);
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int cellNum=0;cellNum<fields.length;cellNum++){
                Field field=fields[cellNum];
                field.setAccessible(true);
                try {
                    Cell cell = row.createCell(cellNum);
                    Object val = field.get(obj);
                    /*if(val instanceof Date){
                        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                        val =formatter.format((Date)field.get(obj));
                    }*/
                    cell.setCellValue(String.valueOf(val));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        log.info("结束导出数据到Excle文件："+simpleDateFormat.format(System.currentTimeMillis()));
    }

}
