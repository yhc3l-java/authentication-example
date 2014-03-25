package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Teacher {
	@Id
	@GeneratedValue
	public int id;
	public String name;
	
	@OneToMany(mappedBy="teacher")
	public List<Student> students;
}
