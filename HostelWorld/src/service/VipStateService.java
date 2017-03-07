package service;

/**
 * Created by Seven on 2017/2/22.
 */
public interface VipStateService {
    /**
     * 更改会员卡状态
     * 有效期一年，到期若卡上费用<1000则暂停会员卡
     * 暂停时，可通过充值卡金额恢复为激活状态
     * 暂停一年未使用，则删除该会员卡
     * 由系统定时查询卡到期时限，自动完成该操作
     * @return
     */
    public boolean changeState();
}
