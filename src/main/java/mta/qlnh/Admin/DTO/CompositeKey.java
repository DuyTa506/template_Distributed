package mta.qlnh.Admin.DTO;

public class CompositeKey {
    private int permission_group_id;
    private int permission_id;

    public CompositeKey() {
    }

    public CompositeKey(int permission_group_id, int permission_id) {
        this.permission_group_id = permission_group_id;
        this.permission_id = permission_id;
    }
}
