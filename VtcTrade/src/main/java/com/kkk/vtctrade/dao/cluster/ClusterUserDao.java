package com.kkk.vtctrade.dao.cluster;

import org.apache.ibatis.annotations.Mapper;
import com.kkk.vtctrade.common.dao.IBaseDao;
import com.kkk.vtctrade.vo.cluster.ClusterUser;

@Mapper
public interface ClusterUserDao extends IBaseDao<ClusterUser>
{
}