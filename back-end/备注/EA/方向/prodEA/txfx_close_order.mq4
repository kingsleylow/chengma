//+------------------------------------------------------------------+
//|                                                         txfx.mq4 |
//|                                                            totti |
//|                                           https://www.txasfx.com |
//+------------------------------------------------------------------+
#include <stdlib.mqh>
#include <MQLMySQL.mqh>
#property copyright "totti"
#property link      "https://www.txasfx.com"
#property version   "1.00"
#property strict
//+------------------------------------------------------------------+
//| Expert initialization function                                   |
//+------------------------------------------------------------------+

//string ip="127.0.0.1";string db_user="root";string db_pass="123";
string ip="47.52.199.109";string db_user="dev";string db_pass="dev123";


//



//



string db_name="crm";

//extern double StopLose=660;//止损点数

//extern double StopEarn=470;//止盈点数

extern double profit=50; //一手营利
extern double lose=5; //一手盈亏




int OnInit()
  {
  
 
  
  
  
  
  
//--- create timer
   EventSetTimer(2);
      
//---
   return(INIT_SUCCEEDED);
  }
//+------------------------------------------------------------------+
//| Expert deinitialization function                                 |
//+------------------------------------------------------------------+
void OnDeinit(const int reason)
  {
//--- destroy timer
   EventKillTimer();
      
  }
//+------------------------------------------------------------------+
//| Expert tick function                                             |
//+------------------------------------------------------------------+
void OnTick()
  {
//---
   
   
   
   
   
   
   
   
   
  }
//+------------------------------------------------------------------+
//| Timer function                                                   |
//+------------------------------------------------------------------+
void OnTimer()
  {
//---
   int DB = MySqlConnect(ip, db_user, db_pass, db_name, 3306, 0, "");
   if (DB == -1) { 
   
       Print ("连接失败!错误信息: "+MySqlErrorDescription);
       
   } else { 
   
     Print ("连接成功#",DB);
     string symbol = Symbol();
     //string sql="select d.id,d.rorderid,a.id,a.isz from ry_bbb_orderdetail d,ry_bbb_account a where d.is_real='1' and  a.id=d.buser_id and d.result='-1' and a.isreal='0' ";
     string sql="select c_id, i_order_no, c_account, c_overnight from t_tlb_trade where c_closed = 'N' and c_symbol = '" + symbol + "'";
    // Print ("insql:"+sql);
     int cursor = MySqlCursorOpen(DB, sql);
    // Print ("游标:"+cursor);
     
     if (cursor >= 0){
         int rows = MySqlCursorRows(cursor);
         
         Print (rows, " row(s) selected.");
         
     for (int i=0; i<rows; i++){
     
         if (MySqlCursorFetchRow(cursor)){
         
             string id = MySqlGetFieldAsString(cursor, 0); // 编号
             int orderid = MySqlGetFieldAsInt(cursor, 1); // 订单编号
             string accountid = MySqlGetFieldAsString(cursor, 2); // 账号ID
             string overnight = MySqlGetFieldAsString(cursor, 3); // 是否需要手隔夜费
             //string comment = MySqlGetFieldAsString(cursor, 3); // 是否反向跟单
             
             //判断订单是否平仓 ，如果订单平仓了，更新相关数据
             
             if(OrderSelect(orderid, SELECT_BY_TICKET)==true){
                 //Print("order #"+orderid+"  处理");
                 
                 datetime isopen = OrderCloseTime();
                 
                 if(isopen=="1970.01.01 00:00:00"){
                   // Print("order #"+orderid+" open price is ", OrderOpenPrice());
                   // Print("order #"+orderid+" close time is ", OrderCloseTime());
                 
                 
                   // Print("未平仓记录");
                     string openTimeString = TimeToString(OrderOpenTime(), TIME_DATE);
                     string currentTimeString = TimeToString(TimeCurrent(), TIME_DATE);
   
                     datetime openTime =  StringToTime(openTimeString);
                     datetime currentTime = StringToTime(currentTimeString);
                     int day = TimeDay(currentTime - openTime);
                     
                     if(day > 7 && overnight != "Y"){
                        string updateOvernight = "update t_tlb_trade set c_overnight='Y' where c_id='"+id+"' and TO_DAYS('" + TimeCurrent() +"') - TO_DAYS(d_open_time) > 6" ;
                        Print("upsql = " + updateOvernight);
                        bool result=MySqlExecute(DB, updateOvernight);
                        Print("updateOvernight result : ===================" + result);
                     }
                   
                 
                 }else{
                    Print("===============平仓记录==============");
                    double money= OrderProfit();
                    
                    double closeprice = OrderClosePrice();
                    
                    string orderComment = OrderComment();
                    
                    Print("orderComment =============== "+ orderComment);
                    
                    string comment = StringSubstr(orderComment,0,1);
                    
                    double lots = OrderLots();  
                   
                    //是否要计算隔夜费
                    double overnightFee = 0;
                    if(overnight == "Y"){
                        overnightFee = lots * 5;
                    }
                    double freeMargin = lots * 10000;
                    
                    string temp=isopen+"";
                    string closetime =StringSetChar(temp, 4, '-');
                    closetime =StringSetChar(closetime, 7, '-');
                    
                    if(( money>0 && comment != "Y") || (money < 0 && comment == "Y")){
                        Print("=========客户盈利=========");
                        double profitAmount = lots * profit - overnightFee; 
                        double updateFreeMargin = freeMargin + profitAmount - overnightFee;
                        double updateBalance = profitAmount - overnightFee;
                        
                        //更新账号金额
                        string asql="update t_tlb_account set i_free_margin = i_free_margin + " + updateFreeMargin
                           + ", i_margin = i_margin - " + freeMargin 
                           + ", i_profit = ifnull(i_profit,0) + " + profitAmount 
                           + ", i_balance = i_balance + " + updateBalance
                           + " where c_account ='"+accountid+"'";
                        Print("asql = " + asql);
                        bool result=MySqlExecute(DB, asql);
                        
                        string upsql="update t_tlb_trade set c_closed='Y', c_gain = 'Y', i_close_price = " + closeprice 
                        + ", d_close_time = '" + closetime 
                        + "', c_gain_amount =" + profitAmount 
                        + " , i_overnight_fee =" + overnightFee 
                        + " where c_id='"+id+"'";
                        Print("upsql = " + upsql);
                        result=MySqlExecute(DB, upsql);
                        Print("result : ===================" + result);
                        
                    }else{
                        Print("=========客户亏损=========");
      						double loseAmount = 0 ; 
      						if(Symbol() == "XAUUSD"){
      							loseAmount = lots * lose;
      						}
                        double updateFreeMargin = freeMargin - overnightFee - loseAmount;
                        double updateBalance = - (overnightFee + loseAmount);
						
                        
                        //string upsql="update ry_bbb_orderdetail set closeprice='"+closeprice+"',result='0',closetime='"+closetime+"',remark='0'  where id='"+id+"'";
                        string upsql="update t_tlb_trade set c_closed='Y', c_gain = 'N', i_close_price = " + closeprice 
                                 + ", d_close_time ='" + closetime 
                                 + "', c_gain_amount = " + -loseAmount 
                                 + ", i_overnight_fee =" + overnightFee 
                                 + " where c_id='"+id+"'";
                        Print("upsql = " + upsql);
                        bool result=MySqlExecute(DB, upsql);
                        Print("result:"+result);
                        
                        //更新账号金额
                        string asql="update t_tlb_account set i_free_margin = i_free_margin + " + updateFreeMargin
                           + ", i_margin = i_margin - " + freeMargin 
                           + ", i_balance = i_balance + " + updateBalance
                           + ", i_profit = ifnull(i_profit,0) - " + (loseAmount + overnightFee)
                           + " where c_account ='"+accountid+"'";
                          
                        Print("asql = " + asql);
                        result=MySqlExecute(DB, asql);
                        Print("result:"+result);
                        
                        
                        if(!result){
                           Print("=========数据库操作失败，报警=========");
                        }

                    }
                  

                  //}


                 }
   
                 
             }

             
            }
            
           }
     MySqlCursorClose(cursor); // NEVER FORGET TO CLOSE CURSOR !!!
    
    
    }else{
    
     Print ("Cursor opening failed. Error: ", MySqlErrorDescription);
     
     
    }
     
     
     
     
     
     
     
     MySqlDisconnect(DB);
    
   
  
  
  }
  
  }
//+------------------------------------------------------------------+
