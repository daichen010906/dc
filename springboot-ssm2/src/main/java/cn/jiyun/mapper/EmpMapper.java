package cn.jiyun.mapper;

import cn.jiyun.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpMapper {
    List<Emp> findAll();
    void addEmp(@Param(value="emp") Emp emp);
    void editEmp(@Param(value="emp") Emp emp);
    void deleteById(@Param(value="eid")Integer eid);
    Emp findEmpByEid(@Param(value="eid")Integer eid);
}
