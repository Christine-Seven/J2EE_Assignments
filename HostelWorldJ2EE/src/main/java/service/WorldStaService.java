package service;

import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/06/2017.
 */
public interface WorldStaService {

    /**
     * 按季度获得平台平均房价
     * Integer-->季度，Double[0]-->adr，Double[1]-->实际客房收入
     * @return
     */
    public Map<Integer,Double[]> getAdrBySeason();

    /**
     * 按城市获得平台平均房价
     * String-->城市，Double[0]-->adr，Double[1]-->实际客房收入
     * @return
     */
    public Map<String,Double[]> getAdrByCity();

    /**
     *  按照季度获得平台入住率
     *  Integer-->季度，Double[0]-->occ，Double[1]-->实际入住订单数
     * @return
     */
    public Map<Integer,Double[]> getOccBySeason();

    /**
     *  按照城市获得平台入住率
     *  String-->城市，Double[0]-->occ，Double[1]-->实际入住订单数
     * @return
     */
    public Map<String,Double[]> getOccByCity();

    /**
     * 按照季度获得平台平均每间可售房收入
     * Integer-->季度，Double[0]-->revpar，Double[1]-->可售房数量
     * @return
     */
    public Map<Integer,Double[]> getRevparBySeason();

    /**
     * 按照城市获得平台平均每间可售房收入
     * String-->城市，Double[0]-->revpar，Double[1]-->可售房数量
     * @return
     */
    public Map<String,Double[]> getRevparByCity();

    /**
     * 按照加盟时间获得客栈营业额分布情况
     * @return
     */
    public Map<String,Double> getMoneyByTime();

    /**
     * 按照城市获得营业额分布情况
     * @return
     */
    public Map<String,Double> getMoneyByCity();

    /**
     * 按照等级获得营业额分布情况
     * @return
     */
    public Map<String,Double> getMoneyByLevel();

    /**
     * 按照月份获得营业额走势
     * @return
     */
    public Map<Integer,Double> getMoneyByMonth();

    /**
     * 按照季度获得营业额走势
     * Integer -> 季度，Double -> 营业额
     * @return
     */
    public Map<Integer,Double> getMoneyBySeason();

    /**
     * 根据订单数目，按月份统计会员活跃程度
     * Integer -> 月份，Integer -> 活跃会员数目
     * @return
     */
    public Map<Integer,Map<Integer,Integer>> getActiveByMonth();

    /**
     * 查看不同地域的活跃会员占比，近一个季度有消费记录即为活跃会员
     * String ->城市,List ->活跃会员列表
     * @return
     */
    public Map<String,List<String>> getActiveByCity();

    /**
     * 获得平台热门城市，近一个季度
     * String -> 城市，Integer -> 每个城市的订单数目
     * @return
     */
    public Map<String,Integer> getCityByTime();

}
