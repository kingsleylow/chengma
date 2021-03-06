package com.chengma.devplatform.service;




import com.chengma.devplatform.service.dto.TlbAccountTradeDTO;

import java.util.HashMap;
import java.util.List;

public interface TlbAccountTradeService {

    TlbAccountTradeDTO save(TlbAccountTradeDTO tlbAccountTradeDTO);

    /**
     * 根据账号结算出入金记录
     * @return
     */
    void countFundInOrOutByAccount(String account);

    /**
     * 结算所有账号出入金记录
     * @return
     */
    void countFundInOrOut();

    /**
     * 根据账号结算手数和总收益率
     * @param account
     */
    void countLotsAndTotalByAccount(String account);

    /**
     * 结算所有账号手数和总收益
     */
    void countLotsAndTotal();

    /**
     * 结算所有账号月收益率
     */
    void countMonthProfit();

    /**
     * 根据账号结算月收益率
     */
    void countMonthProfitByAccount(String account);

    /**
     * 根据账号查询交易历史列表
     * @param account
     * @return
     */
    List<TlbAccountTradeDTO> findTradeByAccount(String account);

    /**
     * 根据账号查询交易历史列表(最近三条)
     * @param account
     * @return
     */
    List<TlbAccountTradeDTO> findTradeByAccountLimit(String account);

    /**
     * 类别比率(app饼图数据)
     * @param account
     * @return
     */
    List<TlbAccountTradeDTO> findTradeByRatio(String account);

    HashMap<String,Object> pageList(HashMap<String, Object> params);

}
