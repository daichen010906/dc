package cn.jiyun.service;

import cn.jiyun.mapper.EmpMapper;
import cn.jiyun.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    public List<Emp> findAll(){
        return empMapper.findAll();
    }

    public void addEmp(Emp emp){
        empMapper.addEmp(emp);
    }

    public void editEmp(Emp emp){
        empMapper.editEmp(emp);
    }

    public void deleteById(Integer eid){
        empMapper.deleteById(eid);
    }

    public Emp findEmpByEid(Integer eid){
        Emp emp = empMapper.findEmpByEid(eid);
        return emp;
    }
}
