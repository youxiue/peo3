package com.itheima.domain;


import com.itheima.domain.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Orders {

  private String id;                //无意义 主键
  private String orderNum;          //订单编号  唯一
  private Date orderTime;           //下单时间
  private Integer peopleCount;     //出行人数
  private String orderDesc;        //订单描述
  private Integer payType;         //支付方式 0 支付宝 1 微信 2  其它
  private Integer orderStatus;    //订单状态 0 和1
  private String productId;    //产品id
  private String memberId;      //会员id

  private Product product;
  private Member member;
  private List<Traveller> travellers;

  private String orderTimeStr;      //下单时间字符串
  private String orderStatusStr;    //订单状态字符串
  private String payTypeStr;        //支付方式

  public String getPayTypeStr() {
    if(payType==0){
      payTypeStr = "支付宝";
    }else if(payType==1){
      payTypeStr = "微信";
    }else {
      payTypeStr = "其它";
    }
    return payTypeStr;
  }

  public void setPayTypeStr(String payTypeStr) {
    this.payTypeStr = payTypeStr;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public List<Traveller> getTravellers() {
    return travellers;
  }

  public void setTravellers(List<Traveller> travellers) {
    this.travellers = travellers;
  }

  public String getOrderTimeStr() {
    if(orderTime!=null){
      orderTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(orderTime);
    }
    return orderTimeStr;
  }

  public void setOrderTimeStr(String orderTimeStr) {
    this.orderTimeStr = orderTimeStr;
  }

  public String getOrderStatusStr() {
    if(orderStatus==0){
      orderStatusStr = "未支付";
    }else {
      orderStatusStr = "已支付";
    }
    return orderStatusStr;
  }

  public void setOrderStatusStr(String orderStatusStr) {
    this.orderStatusStr = orderStatusStr;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  public Date getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }

  public Integer getPeopleCount() {
    return peopleCount;
  }

  public void setPeopleCount(Integer peopleCount) {
    this.peopleCount = peopleCount;
  }

  public String getOrderDesc() {
    return orderDesc;
  }

  public void setOrderDesc(String orderDesc) {
    this.orderDesc = orderDesc;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }


  @Override
  public String toString() {
    return "Orders{" +
            "id='" + id + '\'' +
            ", orderNum='" + orderNum + '\'' +
            ", orderTime=" + orderTime +
            ", peopleCount=" + peopleCount +
            ", orderDesc='" + orderDesc + '\'' +
            ", payType=" + payType +
            ", orderStatus=" + orderStatus +
            ", productId='" + productId + '\'' +
            ", memberId='" + memberId + '\'' +
            ", product=" + product +
            ", member=" + member +
            ", travellers=" + travellers +
            ", orderTimeStr='" + orderTimeStr + '\'' +
            ", orderStatusStr='" + orderStatusStr + '\'' +
            ", payTypeStr='" + payTypeStr + '\'' +
            '}';
  }
}
