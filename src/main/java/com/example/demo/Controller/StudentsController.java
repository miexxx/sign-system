package com.example.demo.Controller;

/**
 * Created by ZHW on 2018/12/26.
 */
import com.example.demo.Model.*;
import com.example.demo.Resource.StudentSignInfo;
import com.example.demo.Resource.Swal;
import com.example.demo.Service.*;
import com.example.demo.Util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class StudentsController {
    private  final StudentsService studentsService;
    private  final ClassesService classesService;
    private  final StudentSignService studentSignService;
    private  final StudyFilesService studyFilesService;
    private final TasksService tasksService;
    private final StudentTasksService studentTasksService;
    @Value("${signtime}")
    private String signTime;
    @Autowired
    public StudentsController(StudentsService studentsService, ClassesService classesService, StudentSignService studentSignService,StudyFilesService studyFilesService,TasksService tasksService,StudentTasksService studentTasksService){
        this.studentsService = studentsService;
        this.classesService = classesService;
        this.studentSignService = studentSignService;
        this.studyFilesService = studyFilesService;
        this.tasksService = tasksService;
        this.studentTasksService =studentTasksService;
    }
    @PostMapping("/upload")
    public ModelAndView Upload(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpSession session){
        String task = request.getParameter("task");
        TasksModel tasksModel = this.tasksService.select(new Integer(task));
        if(task == null || file.isEmpty()){
            ModelAndView mv = new ModelAndView("error");
            return mv;
        }
        String pathName = "src\\main\\java\\com\\example\\demo\\homework\\";//想要存储文件的地址
        StudentsModel studentsModel =(StudentsModel) session.getAttribute("student");
        String pname = studentsModel.getSno()+studentsModel.getName()+tasksModel.getTitle()+".doc";//获取文件名（包括后缀）
        pathName += pname;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathName);
            fos.write(file.getBytes()); // 写入文件
            //System.out.println("文件上传成功");
            StudentTasksModel s = this.studentTasksService.select((int)studentsModel.getId(), new Integer(task));
            if(s ==null) {
                StudentTasksModel studentTasksModel = new StudentTasksModel();
                studentTasksModel.setFile_name(pname);
                studentTasksModel.setPath(pathName);
                studentTasksModel.setTask_id(new Integer(task));
                studentTasksModel.setUser_id((int) studentsModel.getId());
                this.studentTasksService.insert(studentTasksModel);
            }else{
                s.setFile_name(pname);
                s.setPath(pathName);
                this.studentTasksService.updateValue(s);
            }
            ModelAndView mv = new ModelAndView("success");
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView mv = new ModelAndView("error");
            return mv;
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping("/signlist")
    public ModelAndView Signlist(ModelMap map,HttpSession session) {
        if(session.getAttribute("student") == null){
            ModelAndView mb = new ModelAndView("sign");
            return mb;
        }
        ModelAndView mv = new ModelAndView("student/signlist");
        StudentsModel studentsModel =(StudentsModel) session.getAttribute("student");
        int class_id = (int)studentsModel.getClass_id();
        List<StudentSignModel> studentSignModels = this.studentSignService.selectAll(class_id);
        List<StudentSignInfo> studentSignInfos =new ArrayList<StudentSignInfo>();
        for(StudentSignModel Sign :studentSignModels){
            StudentSignInfo data = new StudentSignInfo();
            StudentsModel studentinfo =this.studentsService.selectId((int)Sign.getUser_id());
            data.setStudentSignModel(Sign);
            data.setStudentsModel(studentinfo);
            studentSignInfos.add(data);
        }
        map.addAttribute("studentSignInfos",studentSignInfos);
        return mv;
    }

    @RequestMapping("/")
    public ModelAndView Index() {
        ModelAndView mv = new ModelAndView("sign");
        return mv;
    }

    @RequestMapping("/loginout")
    public ModelAndView Loginout(HttpSession session) {
        session.removeAttribute("student");
        ModelAndView mv = new ModelAndView("sign");
        return mv;
    }

    @RequestMapping("/welcome")
    public ModelAndView Welcome(ModelMap map,  HttpSession session) {
        if(session.getAttribute("student") == null){
            ModelAndView mb = new ModelAndView("sign");
            return mb;
        }
        ModelAndView mv = new ModelAndView("student/welcome");
        StudentsModel studentsModel =(StudentsModel) session.getAttribute("student");
        int class_id = (int)studentsModel.getClass_id();
        ClassesModel classesModel = this.classesService.select(class_id);
        List<StudentsModel>students = this.studentsService.selectAll(class_id);

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String nowTime = df.format(new Date());
        String signTime = this.signTime;
        Integer result=nowTime.compareTo(signTime);
        StudentSignModel SignModel = this.studentSignService.select((int)studentsModel.getId());
        if(SignModel == null) {
            StudentSignModel studentSignModel = new StudentSignModel();
            studentSignModel.setUser_id((int)studentsModel.getId());
            try {
                studentSignModel.setSign_time(df.parse(nowTime));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            if (result > 0) {
                studentSignModel.setStatus(1);
            } else {
                studentSignModel.setStatus(0);
            }
            studentSignModel.setClass_id(class_id);
            this.studentSignService.insert(studentSignModel);
            map.addAttribute("SignModel",studentSignModel);
        }
        else{
            map.addAttribute("SignModel",SignModel);
        }
        List<StudentSignModel>studentSignModels =this.studentSignService.selectAll(class_id);
        map.addAttribute("signnumber", studentSignModels.size());
        map.addAttribute("studentsModel", studentsModel);
        map.addAttribute("students", students);
        map.addAttribute("numbers",students.size());
        map.addAttribute("classesModel",classesModel);
        return mv;
    }

    @PostMapping("/sign")
    public Swal Sign(HttpServletRequest request) {
        Integer sno = new Integer(request.getParameter("sno"));
        String password = request.getParameter("password");
        String ip = request.getRemoteAddr();
        StudentsModel studentsModel =this.studentsService .select(sno);
        if(studentsModel == null ){
            return Message.SwalMessage("该用户不存在",0);
        }
        else if(studentsModel.getIp().equals(ip)) {
            //todo
            HttpSession session = request.getSession();
            session.setAttribute("student", studentsModel);
            return Message.SwalMessage("登录成功",1);
        }
        return Message.SwalMessage("登录失败",0);
    }

    @RequestMapping("/filelist")
    public ModelAndView FileList(ModelMap map, HttpSession session){
        if(session.getAttribute("student") == null){
            ModelAndView mb = new ModelAndView("sign");
            return mb;
        }
        StudentsModel studentsModel =(StudentsModel) session.getAttribute("student");
        int class_id = (int)studentsModel.getClass_id();
        ModelAndView mv = new ModelAndView("student/filelist");
        List<StudyFilesModel>studyFilesModels = this.studyFilesService.selectAll(class_id);
        map.addAttribute("studyFilesModels",studyFilesModels);
        return mv;
    }

    @RequestMapping("/fileupload")
    public ModelAndView Fileupload(ModelMap map, HttpSession session){
        if(session.getAttribute("student") == null){
            ModelAndView mb = new ModelAndView("sign");
            return mb;
        }
        StudentsModel studentsModel =(StudentsModel) session.getAttribute("student");
        int class_id = (int)studentsModel.getClass_id();
        List<StudentTasksModel>studentTasksModels = this.studentTasksService.selectAll((int)studentsModel.getId());
        List<TasksModel>tasksModels = this.tasksService.selectAll(class_id);
        ModelAndView mv = new ModelAndView("student/fileupload");
        map.addAttribute("tasksModels",tasksModels);
        map.addAttribute("studentTasksModels",studentTasksModels);
        return mv;
    }


}
