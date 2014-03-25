package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "std")
public class Student {
	@Id
	@GeneratedValue
	public int id;
	public String name;
	
	@ManyToOne
	public Teacher teacher;
}
