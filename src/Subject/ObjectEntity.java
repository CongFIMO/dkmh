package Subject;

import javax.persistence.*;

@Entity
@Table(name = "object", schema = "registerobject", catalog = "")
public class ObjectEntity {
    private int id;
    private String code;
    private String name;
    private String codeRegister;
    private String teacherName;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Code", nullable = true, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "Name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CodeRegister", nullable = true, length = 100)
    public String getCodeRegister() {
        return codeRegister;
    }

    public void setCodeRegister(String codeRegister) {
        this.codeRegister = codeRegister;
    }

    @Basic
    @Column(name = "TeacherName", nullable = true, length = 200)
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectEntity that = (ObjectEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (codeRegister != null ? !codeRegister.equals(that.codeRegister) : that.codeRegister != null) return false;
        if (teacherName != null ? !teacherName.equals(that.teacherName) : that.teacherName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (codeRegister != null ? codeRegister.hashCode() : 0);
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        return result;
    }
}
