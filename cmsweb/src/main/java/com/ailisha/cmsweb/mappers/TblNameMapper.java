package com.ailisha.cmsweb.mappers;

import com.ailisha.cmsweb.domain.TblName;

public interface TblNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TblName record);

    int insertSelective(TblName record);

    TblName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblName record);

    int updateByPrimaryKey(TblName record);
}