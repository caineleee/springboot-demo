package other;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @program: JavaReview
 * @description: About use I/O operation function
 * @author: Mr.Lee
 * @create: 2020-05-24 14:49
 **/


public class StreamOperation {

    public static final String FILE_NAME = "/Users/billylee/Desktop/test.txt";

    //    输入字节流 InputStream(FileInputStream) 的使用  读取文件, 适用于二进制文件
    @Test
    public void test1() throws IOException {

        File file = getTestFile();

//        读取文件中的所有内容为 byte 类型
        FileInputStream fileInputStream = new FileInputStream(file);

//        read() 方法每次只能输出一个字节, 所以需要循环才能够获取
        for(;;){
            int n = fileInputStream.read();
            if(n == -1){
                break;
            }
            System.out.println(n);

        }
        delTestFile();
    }

//    OutputStream(FileOutputStream) 的使用, 往文件中写入内容. 最好是用于二进制文件, 因为没有编码和解码的内容
    @Test
    public void test2() throws IOException {
        byte[] byteArray = {'H', 'e', 'l', 'l', 'o'};
        File file = getTestFile();

//        如果给 FileOutputStream 的文件不存在, FileOutputStream 则会自动创建文件,
//        第二个参数如果输入为 true 则追加否则就是覆盖原有内容
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        fileOutputStream.write(byteArray);
        fileOutputStream.close();
    }


//    使用 BufferedInputStream 缓冲流对输入字节流进行操作. 能有效提供梳理效率.
    @Test
    public void test3() throws IOException {
        File file = getTestFile();
        FileInputStream fileInputStream = new FileInputStream(file);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

//        获取字节数作为 size
        int size = bufferedInputStream.available();

        for (int i=0;i < size;i++){
            int ch = bufferedInputStream.read();
            System.out.println(ch);

        }
        bufferedInputStream.close();
    }


//  使用 BufferedOutputStream 输出内容. 可以有效加快效率.
    @Test
    public void test4() throws IOException {
        File file = getTestFile();

//        如果想要对文件进行追加操作, 也必须在 FileOutputStream 第二个参数设置 true
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        bufferedOutputStream.write("Hello World".getBytes(),1,8);
        bufferedOutputStream.close();

    }

//  输入转换流  InputStreamReader , 将字节类型 byte 转换为字符类型操作.
    @Test
    public void test5() throws IOException {
        File file = getTestFile();

//        创建 FileInputeStream 输入字节流
        InputStream fileInputStream = new FileInputStream(file);

//        创建输入转换流 并可以指定编码格式. 将字节流转换为字符流
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

//        inputStreamReader.read(chars);
        for (;;){
            int ch = inputStreamReader.read();
            if (ch == -1){
                break;
            }
            System.out.println((char) ch);

        }
        delTestFile();

    }

//    OutputStreamWriter 字节输输出为字符流
    @Test
    public void test6() throws IOException {
        File file = getTestFile();
        OutputStream outputStream = new FileOutputStream(file, true);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        outputStreamWriter.write(new char[]{'a', 'b', 'c', 'd'});
        outputStreamWriter.write(" Hello World.");
        outputStreamWriter.write(97);

        outputStreamWriter.close();
        outputStream.close();
//        delTestFile();
    }

//     输入字符流 Reader, 按照字符流读取处理数据
    @Test
    public void test7() throws IOException {
        File file = getTestFile();
        FileReader fileReader = new FileReader(file);

        System.out.println(fileReader.getEncoding());

        for(;;){
            int a = fileReader.read();
            if (a == -1){
                break;
            }
            System.out.println((char)a);

        }
        fileReader.close();
    }

//    使用 BufferedReader 处理字符流, 可以整行获取字符
    @Test
    public void test8() throws IOException {
        File file = getTestFile();
        FileReader fileReader = new FileReader(file);


        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String lineContent;
        while ((lineContent = bufferedReader.readLine()) != null){
            System.out.println(lineContent);
        }
        bufferedReader.close();
        fileReader.close();
    }


//    FileWriter 输出字符流 以字符流形式输出数据
    @Test
    public void test9() throws IOException {
        File file = getTestFile();
        FileWriter fileWriter = new FileWriter(file);

        String str = "ABCDEFG";
        fileWriter.write(str);

        char[] chars = {'a','b','c','d'};
        fileWriter.write(chars);

        fileWriter.close();

    }

//    使用 BufferedWriter 输出字符流
    @Test
    public void test10() throws IOException{
        File file = getTestFile();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("Hello world");
        bufferedWriter.newLine();
        bufferedWriter.write(new char[]{'a', 'b', 'c', 'd'});

        bufferedWriter.close();
        fileWriter.close();

    }







    //    为方便测试自动创建测试文件
    private static File getTestFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()){
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("This is test file ...");
                fileWriter.close();
                System.out.println("Test file has created.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    //    为方便测试自动删除测试文件
    private static void delTestFile(){
        File file = new File(FILE_NAME);
        try{
            if (file.exists()){
                if(file.delete()){
                    System.out.println("Test file has deleted.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
