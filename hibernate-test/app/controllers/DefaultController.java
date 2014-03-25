package controllers;

import java.util.List;
import java.util.Map;

import models.Student;
import models.Teacher;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.start;
import views.html.newStudent;
import play.mvc.Security;

public class DefaultController extends Controller {

	@Transactional
	public static Result index(){
		session().clear();
		
		List<Student> students = JPA.em().createQuery("SELECT a from Student AS a", Student.class).getResultList();
		
		return ok(start.render(students));
	}
	
	@Transactional
	@Security.Authenticated
	public static Result newStudentForm(){
		List<Teacher> teachers = JPA.em().createQuery("SELECT a from Teacher AS a", Teacher.class).getResultList();
		return ok(newStudent.render(teachers));
	}
	
	@Transactional
	public static Result newStudent(){
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		String name = form.get("name")[0];
		Integer teacherId = Integer.parseInt(form.get("teacher-id")[0]);
		
		Teacher teacher = JPA.em().find(Teacher.class, teacherId);
		
		Student student = new Student();
		student.name = name;
		student.teacher = teacher;
		JPA.em().persist(student);
		
		return redirect(routes.DefaultController.index());
	}
}
