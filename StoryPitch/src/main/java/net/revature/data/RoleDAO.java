package net.revature.data;

public interface RoleDAO {
	// Converting string of role name into an integer value
	public int  getByRoleName( String role_name);
	//Converting int of role name into string value
	public String getByRoleId(int role_id);

}
