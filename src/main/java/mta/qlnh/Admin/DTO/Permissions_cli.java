package mta.qlnh.Admin.DTO;
import mta.qlnh.Admin.DO.Permissions;

//@DTO
//@Table(name="permissions")
public class Permissions_cli {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private int id;
    private String name;
    private String description;


    public Permissions_cli() {
    }

    public Permissions_cli(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Permissions_cli(Permissions x){
        this.id = x.getId();
        this.name = x.getName();
        this.description = x.getDescription();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }


}
