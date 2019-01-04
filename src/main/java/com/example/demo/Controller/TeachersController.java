package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Resource.Swal;
import com.example.demo.Service.*;
import com.example.demo.Util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by ZHW on 2018/12/28.
 */
@RestController
public class TeachersController {
    private final TeachersService teachersService;
    private final StudentsService studentsService;
    private  final ClassesService classesService;
    private  final StudentSignService studentSignService;
    private  final StudyFilesService studyFilesService;
    private final TasksService tasksService;
    private final StudentTasksService studentTasksService;
    @Value("${signtime}")
    private String signTime;
    @Value("${status}")
    private String status;
    @Autowired
    public TeachersController(TeachersService teachersService, ClassesService classesService, StudentSignService studentSignService,StudyFilesService studyFilesService,TasksService tasksService,StudentTasksService studentTasksService, StudentsService studentsService){
        this.teachersService = teachersService;
        this.classesService = classesService;
        this.studentSignService = studentSignService;
        this.studyFilesService = studyFilesService;
        this.tasksService = tasksService;
        this.studentTasksService =studentTasksService;
        this.studentsService =studentsService;
    }
    @RequestMapping("/base")
    public ModelAndView Base(HttpSession session, ModelMap mp){
        if(session.getAttribute("teacher") == null){
            ModelAndView mb = new ModelAndView("teacher/login");
            return mb;
        }
        ModelAndView mv = new ModelAndView("teacher/base");
        mp.addAttribute("signtime",this.signTime);
        return mv;
    }

    @PostMapping("/csigntime")
    public Swal Csigntime(HttpServletRequest request){
        String signtime =request.getParameter("signtime");
        this.signTime = signtime;
        this.studentSignService.delete();
        return Message.SwalMessage("启动成功",1);
    }

    @RequestMapping("/teloginout")
    public ModelAndView TeLoginout(HttpSession session) {
        session.removeAttribute("teacher");
        ModelAndView mv = new ModelAndView("teacher/login");
        return mv;
    }

    @RequestMapping("/admin")
    public ModelAndView Login(){
        ModelAndView mv = new ModelAndView("teacher/login");
        return mv;
    }
    @PostMapping("/telogin")
    public Swal Telogin(HttpServletRequest request){
        String name= request.getParameter("name");
        String password = request.getParameter("password");
        TeachersModel teachersModel = this.teachersService.select(name);
        if(teachersModel == null ){
            return  Message.SwalMessage("该用户不存在",0);
        }
        else if(!teachersModel.getPassword().equals(password)){
            return  Message.SwalMessage("密码错误",0);
        }
        HttpSession session = request.getSession();
        session.setAttribute("teacher", teachersModel);
        return  Message.SwalMessage("登录成功",1);
    }

    @PostMapping("/regirst")
    public Swal Regirst(HttpServletRequest request){
        String name= request.getParameter("name");
        String password = request.getParameter("password");
        if(name!=null && password!=null){
            TeachersModel teachersModel = new TeachersModel();
            teachersModel.setName(name);
            teachersModel.setPassword(password);
            this.teachersService.insert(teachersModel);
            return  Message.SwalMessage("注册成功",1);
        }
        return Message.SwalMessage("注册失败",0);
    }

    @RequestMapping("/classes")
    public ModelAndView Classes(HttpSession session, ModelMap mp){
        if(session.getAttribute("teacher") == null){
            ModelAndView mb = new ModelAndView("teacher/login");
            return mb;
        }
        ModelAndView mv = new ModelAndView("teacher/classes");
        List<ClassesModel>classesModels = this.classesService.selectAll();
        mp.addAttribute("classesModels",classesModels);
        return mv;
    }

    @RequestMapping("/studylist")
    public ModelAndView StudyList(HttpSession session, ModelMap mp, HttpServletRequest request) {
        if(session.getAttribute("teacher") == null){
            ModelAndView mb = new ModelAndView("teacher/login");
            return mb;
        }
        String class_id =request.getParameter("class_id");
        if(class_id !=null){
            List<StudyFilesModel>studyFilesModels = this.studyFilesService.selectAll(new Integer(class_id));
            mp.addAttribute("studyFilesModels",studyFilesModels);
        }
        ModelAndView mv = new ModelAndView("teacher/filelist");
        List<ClassesModel>classesModels = this.classesService.selectAll();
        mp.addAttribute("classesModels",classesModels);
        return mv;
    }

    @PostMapping("/studyupload")
    public ModelAndView StudyUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String class_id = request.getParameter("classes");
        if( file.isEmpty()){
            ModelAndView mv = new ModelAndView("teacher/error");
            return mv;
        }
        String pathName = "src\\main\\resources\\static\\file\\";//想要存储文件的地址
        String pname = file.getOriginalFilename();//获取文件名// （包括后缀）
        pathName += pname;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathName);
            fos.write(file.getBytes()); // 写入文件
            //System.out.println("文件上传成功");
            StudyFilesModel s =new StudyFilesModel();
            s.setFile_name(pname);
            s.setPath("file/"+pname);
            s.setClass_id(new Integer(class_id));
            this.studyFilesService.insert(s);
            ModelAndView mv = new ModelAndView("teacher/success");
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView mv = new ModelAndView("teacher/error");
            return mv;
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/filedelete")
    public Swal FileDelete(HttpServletRequest request){
        String id = request.getParameter("id");
        this.studyFilesService.delete(new Integer(id));
        return Message.SwalMessage("删除成功",1);
    }


    @RequestMapping("/tesignlist")
    public ModelAndView TeSignList(HttpSession session, ModelMap mp, HttpServletRequest request) {
        if(session.getAttribute("teacher") == null){
            ModelAndView mb = new ModelAndView("teacher/login");
            return mb;
        }
        String class_id =request.getParameter("class_id");
        if(class_id !=null){
            List<StudentsModel>studentsModels = this.studentsService.selectAll(new Integer(class_id));
            List<StudentSignModel>studentSignModels = this.studentSignService.selectAll(new Integer(class_id));
            mp.addAttribute("studentsModels",studentsModels);
            mp.addAttribute("studentSignModels",studentSignModels);
        }
        ModelAndView mv = new ModelAndView("teacher/signlist");
        List<ClassesModel>classesModels = this.classesService.selectAll();
        mp.addAttribute("classesModels",classesModels);
        return mv;
    }

    @PostMapping("/classesadd")
    public Swal ClassesAdd(HttpServletRequest request){
        String name = request.getParameter("name");
        if(name == ""){
            return Message.SwalMessage("添加失败",0);
        }
        ClassesModel classesModel = new ClassesModel();
        classesModel.setName(name);
        this.classesService.insert(classesModel);
        return Message.SwalMessage("添加成功",1);
    }

    @PostMapping("classesdelete")
    public Swal ClassesDelete(HttpServletRequest request){
        String id = request.getParameter("id");
        if(id == null)
            return Message.SwalMessage("删除失败",0);
        this.classesService.delete(new Integer(id));
        return Message.SwalMessage("删除成功",1);
    }

    @RequestMapping("tetask")
    public ModelAndView Tetask(HttpSession session, ModelMap mp, HttpServletRequest request){
        if(session.getAttribute("teacher") == null){
            ModelAndView mb = new ModelAndView("teacher/login");
            return mb;
        }
        String class_id =request.getParameter("class_id");
        if(class_id !=null){
            List<TasksModel>tasksModels = this.tasksService.selectAll(new Integer(class_id));
            mp.addAttribute("tasksModels",tasksModels);
        }
        ModelAndView mv = new ModelAndView("teacher/tetask");
        List<ClassesModel>classesModels = this.classesService.selectAll();
        mp.addAttribute("classesModels",classesModels);
        return mv;
    }

    @PostMapping("/taskdelete")
    public Swal TaskDelete(HttpServletRequest request){
        String id= request.getParameter("id");
        if(id == null)
            return Message.SwalMessage("删除失败",0);
        this.tasksService.delete(new Integer(id));
        return Message.SwalMessage("删除成功",1);
    }

    @PostMapping("/taskadd")
    public Swal TaskAdd(HttpServletRequest request,HttpSession session){
        String title = request.getParameter("title");
        String class_id = request.getParameter("id");
        TeachersModel teacher = (TeachersModel)session.getAttribute("teacher");
        String publicer = teacher.getName();
        if(title == "" || class_id ==null)
            return Message.SwalMessage("添加失败",0);
        TasksModel tasksModel = new TasksModel();
        tasksModel.setTitle(title);
        tasksModel.setClass_id(new Integer(class_id));
        tasksModel.setPublisher(publicer);
        this.tasksService.insert(tasksModel);
        return Message.SwalMessage("添加成功",1);
    }

    @RequestMapping("/testudent")
    public ModelAndView TeStudent(HttpSession session, ModelMap mp, HttpServletRequest request){
        if(session.getAttribute("teacher") == null){
            ModelAndView mb = new ModelAndView("teacher/login");
            return mb;
        }
        String class_id =request.getParameter("class_id");
        if(class_id !=null){
            List<StudentsModel>studentsModels = this.studentsService.selectAll(new Integer(class_id));
            mp.addAttribute("studentsModels",studentsModels);
        }
        ModelAndView mv = new ModelAndView("teacher/testudent");
        List<ClassesModel>classesModels = this.classesService.selectAll();
        mp.addAttribute("classesModels",classesModels);
        return mv;
    }

    @PostMapping("/studentdelete")
    public Swal StudentDelete(HttpServletRequest request){
        String id= request.getParameter("id");
        if(id == null)
            return Message.SwalMessage("删除失败",0);
        this.studentsService.delete(new Integer(id));
        return Message.SwalMessage("删除成功",1);
    }

    @PostMapping("/studentadd")
    public Swal StudentAdd(HttpServletRequest request,HttpSession session){
        String name = request.getParameter("name");
        String sno = request.getParameter("sno");
        String ip = request.getParameter("ip");
        String class_id = request.getParameter("id");
        if(name == "" || sno=="" ||ip=="" ||class_id ==null)
            return Message.SwalMessage("添加失败",0);
        StudentsModel studentsModel = new StudentsModel();
        studentsModel.setClass_id(new Integer(class_id));
        studentsModel.setName(name);
        studentsModel.setSno(sno);
        studentsModel.setIp(ip);
        this.studentsService.insert(studentsModel);
        return Message.SwalMessage("添加成功",1);
    }
}
