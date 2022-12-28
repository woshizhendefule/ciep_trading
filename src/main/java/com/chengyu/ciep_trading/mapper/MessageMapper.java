package com.chengyu.ciep_trading.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengyu.ciep_trading.domain.Message;
import com.chengyu.ciep_trading.domain.vo.MessageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author CL
 * @description 针对表【message(留言表)】的数据库操作Mapper
 * @createDate 2022-11-17 23:20:33
 * @Entity com.chengyu.ciep_trading.domain.Message
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 父级留言用户信息列表
     *
     * @param goodsId 商品id
     * @return 留言用户信息列表
     */
    @Select("select m.*, u.name userName\n" +
            "from message m\n" +
            "         join user u on u.id = m.user_id\n" +
            "where goods_id = #{goods_id}\n" +
            "  and father_message_id IS NULL\n" +
            "order by create_time desc;")
    List<MessageInfo> getParentMessagesUserJoinUser(@Param("goods_id") Integer goodsId);

    /**
     * 子级留言用户信息列表
     *
     * @param goodsId         商品id
     * @param fatherMessageId 父留言编号
     * @return 留言用户信息列表
     */
    @Select("select m.*, u3.name userName, u.name fatherMessageName, u2.name atUserName\n" +
            "from message m\n" +
            "         join user u on u.id = m.user_id\n" +
            "         join user u3 on u3.id = m.user_id\n" +
            "         left join user u2 on u2.id = m.at_user_id\n" +
            "where goods_id = #{goods_id}\n" +
            "  and father_message_id = #{father_message_id};")
    List<MessageInfo> getChildMessagesUserJoinUser(@Param("goods_id") Integer goodsId,
                                                   @Param("father_message_id") Integer fatherMessageId);

    /**
     * 父级 / 子级留言用户信息列表
     *
     * @return 留言用户信息列表
     */
    @Select("select m.*, u.name userName, g.name goodsName, u2.name fatherMessageName, u3.name atUserName\n" +
            "from message m\n" +
            "         join user u on u.id = m.user_id\n" +
            "         join goods g on g.id = m.goods_id\n" +
            "         left join message m2 on m.father_message_id = m2.id\n" +
            "         left join user u2 on u2.id = m2.user_id\n" +
            "         left join user u3 on u3.id = m.at_user_id;\n")
    List<MessageInfo> getParentChildMessagesUserJoinUser();
}




