package DailyRoutineApp.app.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
public class D_Routine {

	public D_Routine() {}

	public D_Routine(Account account,String title,Integer NicePnt) {
		setRoutineid(0);
		setTitle(title);
		setNicepnt(NicePnt);
		setAccount(account);
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Integer routineid;

//	@NotNull
//	@NotEmpty(message="Content Titleは必須項目です")
	@Length(min=1,message="Content Titleは必須項目です")
	@Column(nullable=false)
	private String title;

	@Column
	@Length(max=50,message="Content Descriptionは50文字未満です。")
	private String description;

	@Column(nullable = false)
	private Timestamp currenttime;


	@Column(nullable=false)
	private Integer nicepnt;

	@ManyToOne
	private Account account;


}
