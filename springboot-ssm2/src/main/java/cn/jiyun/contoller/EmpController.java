package cn.jiyun.contoller;

import cn.jiyun.pojo.Dept;
import cn.jiyun.pojo.Emp;
import cn.jiyun.service.DeptService;
import cn.jiyun.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpController {
    @Value("${file.upload.path}")
    private String filePath;

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmpService empService;

    @GetMapping("toAddEmp")
    public String toAddEmp(Model model){
        List<Dept> depts = deptService.findAll();
        model.addAttribute("depts",depts);
        return "addEmp";
    }
    @GetMapping("toEditEmp")
    public String toEditEmp(@RequestParam(value="eid")Integer eid,Model model){
        List<Dept> depts = deptService.findAll();
        Emp emp = empService.findEmpByEid(eid);
        model.addAttribute("depts",depts);
        model.addAttribute("emp",emp);
        return "editEmp";
    }
    @PostMapping("addEmp")
    public String addEmp(@ModelAttribute(value="emp") Emp emp,
                         @RequestParam(value="file") MultipartFile file) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        File photoFile = new File(filePath, fileName);
        //判断当前系统是否存在上传路径，如果不存在则新建
        if(!photoFile.getParentFile().exists()){
            photoFile.getParentFile().mkdirs();
        }
        file.transferTo(new File(filePath+File.separator+fileName));

        emp.setPhoto("/img/"+fileName);
        empService.addEmp(emp);
        System.out.println("ddd:"+emp);

        //保存完员工数据之后，跳转到展示页面
        return "redirect:/emp/findAll";

    }

    @PostMapping("editEmp")
    public String editEmp(@ModelAttribute(value="emp") Emp emp,
                         @RequestParam(value="file") MultipartFile file) throws IOException {
        System.out.println("edit----------------->"+emp);
       if(!file.isEmpty()){
           String fileName = file.getOriginalFilename();
           File photoFile = new File(filePath, fileName);
           file.transferTo(new File(filePath+File.separator+fileName));
           emp.setPhoto("/img/"+fileName);
       }

        empService.editEmp(emp);
        return "redirect:/emp/findAll";

    }

    @GetMapping("findAll")
    public String findAll(Model model){
        List<Emp> emps = empService.findAll();
        model.addAttribute("emps",emps);
        return "emp";
    }
    @GetMapping("delEmpById")
    public String deleteEmp(@RequestParam(value="eid")Integer eid){
        empService.deleteById(eid);
        return "redirect:/emp/findAll";
    }
    @GetMapping("test")
    @ResponseBody
    public String test(){
        return "hello emp";
    }
}
