package com.example.protector.util;

import android.util.Log;


import com.example.protector.SQl.TestData;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import android_serialport_api.SerialPort;

/**
 * @author by AllenJ on 2018/4/20.
 *
 * 通过串口用于接收或发送数据
 */

public class SerialPortUtil {
    private SerialPort serialPort = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private ReceiveThread mReceiveThread = null;
    private boolean isStart = false;
    private TestData testData ;
    /**
     * 打开串口，接收数据
     * 通过串口，接收单片机发送来的数据
     */
    public void openSerialPort() {
        try {
            serialPort = new SerialPort(new File("/dev/ttyS1"), 9600, 0);
            //调用对象SerialPort方法，获取串口中"读和写"的数据流
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
            isStart = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        getSerialPort();
    }

    /**
     * 关闭串口
     * 关闭串口中的输入输出流
     */
    public void closeSerialPort() {
        Log.i("test", "关闭串口");
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            isStart = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送数据
     * 通过串口，发送数据到单片机
     *
     * @param data 要发送的数据
     */
    public void sendSerialPort(String data) {
        try {
            byte[] sendData = data.getBytes();
            outputStream.write(sendData);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSerialPort() {
        if (mReceiveThread == null) {

            mReceiveThread = new ReceiveThread();
        }
        mReceiveThread.start();
    }

    /**
     * 接收串口数据的线程
     */

    private class ReceiveThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (isStart) {
                if (inputStream == null) {
                    return;
                }
                byte[] readData = new byte[1024];
                try {
                    int size = inputStream.read(readData);
                    if (size > 0) {
//                        String readString2 = new String(readData).trim();
//                        DataUtils utils = new DataUtils();
//                        List<String> strings = utils.getDivLines(readString2,2);
//                        if(strings.get(3).equals("10")){
//                            testData = new TestData();
//                            testData.setDate(new Date());
//                            testData.setGongwei(new DataUtils().HexToInt(strings.get(5))+"");
//                            testData.setCecheng(new DataUtils().HexToInt(strings.get(6))+"");
//                            testData.setCeshishichang(new DataUtils().HexToInt(strings.get(7)+strings.get(8))+"");
//                            testData.setChanpinbianma(new DataUtils().HexToInt(strings.get(9)+strings.get(10)+strings.get(11)+strings.get(12))+"");
//                            /*testData.setAjiguzhang(new DataUtils().HexToInt(strings.get(5))+"");
//                            testData.setBjiguzhang(new DataUtils().HexToInt(strings.get(5))+"");
//                            testData.setBaojin(new DataUtils().HexToInt(strings.get(5))+"");*/
//                            testData.setXianquanchuanlian1(new DataUtils().HexToInt(strings.get(17)+strings.get(18))+"");
//                            testData.setXianquanchuanlian2(new DataUtils().HexToInt(strings.get(19)+strings.get(20))+"");
//                            testData.setXianquanchuanlian3(new DataUtils().HexToInt(strings.get(21)+strings.get(22))+"");
//                            testData.setXianquanchuanlian4(new DataUtils().HexToInt(strings.get(23)+strings.get(24))+"");
//                            testData.setXianquanchuanlian5(new DataUtils().HexToInt(strings.get(25)+strings.get(26))+"");
//                            testData.setXianquanbinglian(new DataUtils().HexToInt(strings.get(27)+strings.get(28))+"");
//                            testData.setAjiwucha(new DataUtils().HexToInt(strings.get(29))+"");
//                            testData.setBjiwucha(new DataUtils().HexToInt(strings.get(30))+"");
//                            testData.setAxiangawucha(new DataUtils().HexToInt(strings.get(31))+"");
//                            testData.setAxiangbwucha(new DataUtils().HexToInt(strings.get(32))+"");
//                            testData.setAxiangcwucha(new DataUtils().HexToInt(strings.get(33))+"");
//                            testData.setBxiangawucha(new DataUtils().HexToInt(strings.get(34))+"");
//                            testData.setBxiangbwucha(new DataUtils().HexToInt(strings.get(35))+"");
//                            testData.setBxiangcwucha(new DataUtils().HexToInt(strings.get(36))+"");
//                            testData.setAduanxiangdianya(new DataUtils().HexToInt(strings.get(37))+"");
//                            testData.setBduanxiangdianya(new DataUtils().HexToInt(strings.get(38))+"");
//                            testData.setCduanxiangdianya(new DataUtils().HexToInt(strings.get(39))+"");
//                            testData.setAxiangceyajiang(new DataUtils().HexToInt(strings.get(40)+strings.get(41))+"");
//                            testData.setBxiangceyajiang(new DataUtils().HexToInt(strings.get(42)+strings.get(43))+"");
//                            testData.setCxiangceyajiang(new DataUtils().HexToInt(strings.get(44)+strings.get(45))+"");
//                            testData.setQidongshijian(new DataUtils().HexToInt(strings.get(46)+strings.get(47))+"");
//                            testData.setAduanxiangxiangying(new DataUtils().HexToInt(strings.get(48)+strings.get(49))+"");
//                            testData.setBduanxiangxiangying(new DataUtils().HexToInt(strings.get(50)+strings.get(51))+"");
//                            testData.setCduanxiangxiangying(new DataUtils().HexToInt(strings.get(52)+strings.get(53))+"");
//                            testData.setM13xianshishijian(new DataUtils().HexToInt(strings.get(54)+strings.get(55))+"");
//                            testData.setM30xianshishijian(new DataUtils().HexToInt(strings.get(56)+strings.get(57))+"");
//                            testData.setAbxiangjianjueyuan(new DataUtils().HexToInt(strings.get(58)+strings.get(59))+"");
//                            testData.setAcxiangjianjueyuan(new DataUtils().HexToInt(strings.get(60)+strings.get(61))+"");
//                            testData.setBcxiangjianjueyuan(new DataUtils().HexToInt(strings.get(62)+strings.get(63))+"");
//                            testData.setAxiangduidijueyuan(new DataUtils().HexToInt(strings.get(64)+strings.get(65))+"");
//                            testData.setBxiangduidijueyuan(new DataUtils().HexToInt(strings.get(66)+strings.get(67))+"");
//                            testData.setCxiangduidijueyuan(new DataUtils().HexToInt(strings.get(68)+strings.get(69))+"");
//                            testData.setAxiangduixianquanjueyuan(new DataUtils().HexToInt(strings.get(70)+strings.get(71))+"");
//                            testData.setBxiangduixianquanjueyuan(new DataUtils().HexToInt(strings.get(72)+strings.get(73))+"");
//                            testData.setCxiangduixianquanjeuyuan(new DataUtils().HexToInt(strings.get(74)+strings.get(75))+"");
//                            testData.setXianquanduidijueyuan(new DataUtils().HexToInt(strings.get(76)+strings.get(77))+"");
//                            testData.save();
//                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}