package com.example.protector.SQl;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class TestData extends DataSupport {
    private int id;
    private Date date;//时间
    private String gongwei;//工位
    private String cecheng;//测程
    private String ceshishichang;//测试时长
    private String chanpinbianma;//产品编码
    private String ajiguzhang;//A机故障测试结果
    private String bjiguzhang;//B机故障测试结果
    private String baojin;//报警状态
    private String xianquanchuanlian1;//线圈串联电压1
    private String xianquanchuanlian2;//线圈串联电压2
    private String xianquanchuanlian3;//线圈串联电压3
    private String xianquanchuanlian4;//线圈串联电压4
    private String xianquanchuanlian5;//线圈串联电压5
    private String xianquanbinglian;//线圈并联电压
    private String ajiwucha;//A机驱动电压误差值
    private String bjiwucha;//B机驱动电压误差值
    private String axiangawucha;//A机A相电流误差值
    private String axiangbwucha;//A机B相电流误差值
    private String axiangcwucha;//A机C相电流误差值
    private String bxiangawucha;//B机A相电流误差值
    private String bxiangbwucha;//B机B相电流误差值
    private String bxiangcwucha;//B机C相电流误差值
    private String aduanxiangdianya;//A相断相后驱动电压
    private String bduanxiangdianya;//B相断相后驱动电压
    private String cduanxiangdianya;//C相断相后驱动电压
    private String axiangceyajiang;//A相一次侧压降
    private String bxiangceyajiang;//B相一次侧压降
    private String cxiangceyajiang;//C相一次侧压降
    private String qidongshijian;//启动时间
    private String aduanxiangxiangying;//A相断相响应时间
    private String bduanxiangxiangying;//B相断相响应时间
    private String cduanxiangxiangying;//C相断相响应时间
    private String m13xianshishijian;//13秒限时时间
    private String m30xianshishijian;//30秒限时时间
    private String abxiangjianjueyuan;//AB相间绝缘电阻
    private String acxiangjianjueyuan;//AC相间绝缘电阻
    private String bcxiangjianjueyuan;//BC相间绝缘电阻
    private String axiangduidijueyuan;//A相对地绝缘电阻
    private String bxiangduidijueyuan;//B相对地绝缘电阻
    private String cxiangduidijueyuan;//C相对地绝缘电阻
    private String axiangduixianquanjueyuan;//A相对线圈绝缘电阻
    private String bxiangduixianquanjueyuan;//B相对线圈绝缘电阻
    private String cxiangduixianquanjeuyuan;//C相对线圈绝缘电阻
    private String xianquanduidijueyuan;//线圈对地绝缘电阻

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGongwei() {
        return gongwei;
    }

    public void setGongwei(String gongwei) {
        this.gongwei = gongwei;
    }

    public String getCecheng() {
        return cecheng;
    }

    public void setCecheng(String cecheng) {
        this.cecheng = cecheng;
    }

    public String getCeshishichang() {
        return ceshishichang;
    }

    public void setCeshishichang(String ceshishichang) {
        this.ceshishichang = ceshishichang;
    }

    public String getChanpinbianma() {
        return chanpinbianma;
    }

    public void setChanpinbianma(String chanpinbianma) {
        this.chanpinbianma = chanpinbianma;
    }

    public String getAjiguzhang() {
        return ajiguzhang;
    }

    public void setAjiguzhang(String ajiguzhang) {
        this.ajiguzhang = ajiguzhang;
    }

    public String getBjiguzhang() {
        return bjiguzhang;
    }

    public void setBjiguzhang(String bjiguzhang) {
        this.bjiguzhang = bjiguzhang;
    }

    public String getBaojin() {
        return baojin;
    }

    public void setBaojin(String baojin) {
        this.baojin = baojin;
    }

    public String getXianquanchuanlian1() {
        return xianquanchuanlian1;
    }

    public void setXianquanchuanlian1(String xianquanchuanlian1) {
        this.xianquanchuanlian1 = xianquanchuanlian1;
    }

    public String getXianquanchuanlian2() {
        return xianquanchuanlian2;
    }

    public void setXianquanchuanlian2(String xianquanchuanlian2) {
        this.xianquanchuanlian2 = xianquanchuanlian2;
    }

    public String getXianquanchuanlian3() {
        return xianquanchuanlian3;
    }

    public void setXianquanchuanlian3(String xianquanchuanlian3) {
        this.xianquanchuanlian3 = xianquanchuanlian3;
    }

    public String getXianquanchuanlian4() {
        return xianquanchuanlian4;
    }

    public void setXianquanchuanlian4(String xianquanchuanlian4) {
        this.xianquanchuanlian4 = xianquanchuanlian4;
    }

    public String getXianquanchuanlian5() {
        return xianquanchuanlian5;
    }

    public void setXianquanchuanlian5(String xianquanchuanlian5) {
        this.xianquanchuanlian5 = xianquanchuanlian5;
    }

    public String getXianquanbinglian() {
        return xianquanbinglian;
    }

    public void setXianquanbinglian(String xianquanbinglian) {
        this.xianquanbinglian = xianquanbinglian;
    }

    public String getAjiwucha() {
        return ajiwucha;
    }

    public void setAjiwucha(String ajiwucha) {
        this.ajiwucha = ajiwucha;
    }

    public String getBjiwucha() {
        return bjiwucha;
    }

    public void setBjiwucha(String bjiwucha) {
        this.bjiwucha = bjiwucha;
    }

    public String getAxiangawucha() {
        return axiangawucha;
    }

    public void setAxiangawucha(String axiangawucha) {
        this.axiangawucha = axiangawucha;
    }

    public String getAxiangbwucha() {
        return axiangbwucha;
    }

    public void setAxiangbwucha(String axiangbwucha) {
        this.axiangbwucha = axiangbwucha;
    }

    public String getAxiangcwucha() {
        return axiangcwucha;
    }

    public void setAxiangcwucha(String axiangcwucha) {
        this.axiangcwucha = axiangcwucha;
    }

    public String getBxiangawucha() {
        return bxiangawucha;
    }

    public void setBxiangawucha(String bxiangawucha) {
        this.bxiangawucha = bxiangawucha;
    }

    public String getBxiangbwucha() {
        return bxiangbwucha;
    }

    public void setBxiangbwucha(String bxiangbwucha) {
        this.bxiangbwucha = bxiangbwucha;
    }

    public String getBxiangcwucha() {
        return bxiangcwucha;
    }

    public void setBxiangcwucha(String bxiangcwucha) {
        this.bxiangcwucha = bxiangcwucha;
    }

    public String getAduanxiangdianya() {
        return aduanxiangdianya;
    }

    public void setAduanxiangdianya(String aduanxiangdianya) {
        this.aduanxiangdianya = aduanxiangdianya;
    }

    public String getBduanxiangdianya() {
        return bduanxiangdianya;
    }

    public void setBduanxiangdianya(String bduanxiangdianya) {
        this.bduanxiangdianya = bduanxiangdianya;
    }

    public String getCduanxiangdianya() {
        return cduanxiangdianya;
    }

    public void setCduanxiangdianya(String cduanxiangdianya) {
        this.cduanxiangdianya = cduanxiangdianya;
    }

    public String getAxiangceyajiang() {
        return axiangceyajiang;
    }

    public void setAxiangceyajiang(String axiangceyajiang) {
        this.axiangceyajiang = axiangceyajiang;
    }

    public String getBxiangceyajiang() {
        return bxiangceyajiang;
    }

    public void setBxiangceyajiang(String bxiangceyajiang) {
        this.bxiangceyajiang = bxiangceyajiang;
    }

    public String getCxiangceyajiang() {
        return cxiangceyajiang;
    }

    public void setCxiangceyajiang(String cxiangceyajiang) {
        this.cxiangceyajiang = cxiangceyajiang;
    }

    public String getQidongshijian() {
        return qidongshijian;
    }

    public void setQidongshijian(String qidongshijian) {
        this.qidongshijian = qidongshijian;
    }

    public String getAduanxiangxiangying() {
        return aduanxiangxiangying;
    }

    public void setAduanxiangxiangying(String aduanxiangxiangying) {
        this.aduanxiangxiangying = aduanxiangxiangying;
    }

    public String getBduanxiangxiangying() {
        return bduanxiangxiangying;
    }

    public void setBduanxiangxiangying(String bduanxiangxiangying) {
        this.bduanxiangxiangying = bduanxiangxiangying;
    }

    public String getCduanxiangxiangying() {
        return cduanxiangxiangying;
    }

    public void setCduanxiangxiangying(String cduanxiangxiangying) {
        this.cduanxiangxiangying = cduanxiangxiangying;
    }

    public String getM13xianshishijian() {
        return m13xianshishijian;
    }

    public void setM13xianshishijian(String m13xianshishijian) {
        this.m13xianshishijian = m13xianshishijian;
    }

    public String getM30xianshishijian() {
        return m30xianshishijian;
    }

    public void setM30xianshishijian(String m30xianshishijian) {
        this.m30xianshishijian = m30xianshishijian;
    }

    public String getAbxiangjianjueyuan() {
        return abxiangjianjueyuan;
    }

    public void setAbxiangjianjueyuan(String abxiangjianjueyuan) {
        this.abxiangjianjueyuan = abxiangjianjueyuan;
    }

    public String getAcxiangjianjueyuan() {
        return acxiangjianjueyuan;
    }

    public void setAcxiangjianjueyuan(String acxiangjianjueyuan) {
        this.acxiangjianjueyuan = acxiangjianjueyuan;
    }

    public String getBcxiangjianjueyuan() {
        return bcxiangjianjueyuan;
    }

    public void setBcxiangjianjueyuan(String bcxiangjianjueyuan) {
        this.bcxiangjianjueyuan = bcxiangjianjueyuan;
    }

    public String getAxiangduidijueyuan() {
        return axiangduidijueyuan;
    }

    public void setAxiangduidijueyuan(String axiangduidijueyuan) {
        this.axiangduidijueyuan = axiangduidijueyuan;
    }

    public String getBxiangduidijueyuan() {
        return bxiangduidijueyuan;
    }

    public void setBxiangduidijueyuan(String bxiangduidijueyuan) {
        this.bxiangduidijueyuan = bxiangduidijueyuan;
    }

    public String getCxiangduidijueyuan() {
        return cxiangduidijueyuan;
    }

    public void setCxiangduidijueyuan(String cxiangduidijueyuan) {
        this.cxiangduidijueyuan = cxiangduidijueyuan;
    }

    public String getAxiangduixianquanjueyuan() {
        return axiangduixianquanjueyuan;
    }

    public void setAxiangduixianquanjueyuan(String axiangduixianquanjueyuan) {
        this.axiangduixianquanjueyuan = axiangduixianquanjueyuan;
    }

    public String getBxiangduixianquanjueyuan() {
        return bxiangduixianquanjueyuan;
    }

    public void setBxiangduixianquanjueyuan(String bxiangduixianquanjueyuan) {
        this.bxiangduixianquanjueyuan = bxiangduixianquanjueyuan;
    }

    public String getCxiangduixianquanjeuyuan() {
        return cxiangduixianquanjeuyuan;
    }

    public void setCxiangduixianquanjeuyuan(String cxiangduixianquanjeuyuan) {
        this.cxiangduixianquanjeuyuan = cxiangduixianquanjeuyuan;
    }

    public String getXianquanduidijueyuan() {
        return xianquanduidijueyuan;
    }

    public void setXianquanduidijueyuan(String xianquanduidijueyuan) {
        this.xianquanduidijueyuan = xianquanduidijueyuan;
    }
}
